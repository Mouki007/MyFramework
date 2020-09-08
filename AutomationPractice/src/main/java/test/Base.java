package test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;
import properties.LoadFrameworkProp;
import testData.TestDataFactory;
import testData.TestDataMap;
import utilities.DynamicWait;
import utilities.ScreenShots;
import utilities.UserActions;
import utilities.WebElementFactory;

public class Base {
	protected InheritableThreadLocal<WebDriver> threadDriver = null;
	protected DesiredCapabilities caps;
	protected WebDriver driver = null;
	LoadFrameworkProp frameworkConfig = new LoadFrameworkProp();
	protected InheritableThreadLocal<UserActions> userActions = new InheritableThreadLocal<UserActions>();
	protected InheritableThreadLocal<DynamicWait> dynamicWait = new InheritableThreadLocal<DynamicWait>();
	protected InheritableThreadLocal<WebElementFactory> elementFactory = new InheritableThreadLocal<WebElementFactory>();
	protected InheritableThreadLocal<ScreenShots> screenshots = new InheritableThreadLocal<ScreenShots>();

	protected TestDataMap<String, String> dataMap;
	protected TestDataFactory dataFactory;

	
	@Parameters({ "browser" })
	@BeforeMethod
	public void setup(String browser, ITestResult result) throws Exception {
		createDirectory("HTMLReports");
		createDirectory("Screenshot");
		try {
			caps = new DesiredCapabilities();
			if (browser.equalsIgnoreCase("iexplorer")) {
				caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				caps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
				caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);

				WebDriverManager.iedriver().arch32().setup();
				InternetExplorerOptions options = new InternetExplorerOptions(caps);

				driver = new InternetExplorerDriver(options);

			} else if (browser.equalsIgnoreCase("Chrome")) {
				
				WebDriverManager.chromedriver().arch64().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-extensions");
				options.addArguments("ignore-certificate-errors");
				driver = new ChromeDriver(options);
			}
			try {
				threadDriver = new InheritableThreadLocal<WebDriver>();
				threadDriver.set(driver);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			} catch (Exception exception) {
				if (result.getStatus() == ITestResult.FAILURE)
					screenshots.get().takeScreenShots(result.getTestName());
				Reporter.log(
						"There is some issue while launching the browser " + ExceptionUtils.getStackTrace(exception));
			}
			dataFactory = new TestDataFactory();
			dataMap = dataFactory.createTestDataMap();
			userActions.set(new UserActions(driver));
			dynamicWait.set(new DynamicWait(driver));
			screenshots.set(new ScreenShots(driver));
			elementFactory.set(new WebElementFactory(driver));

		} catch (Exception exception) {
			Reporter.log("Browser Setup Failure " + ExceptionUtils.getStackTrace(exception));
		}
	}

	public static void createDirectory(String directoryName) {
		File projectDirectory = new File(System.getProperty("user.dir"));
		File newDirectory = new File(projectDirectory, directoryName);
		if (!newDirectory.exists()) {
			newDirectory.mkdir();
		}
	}

	@AfterMethod
	public void teardown(ITestResult result) throws Exception {
		try {
			driver.close();
		} catch (Exception exception) {
			Reporter.log("Error occurred while quitting the browser " + ExceptionUtils.getStackTrace(exception));
		}
	}

}