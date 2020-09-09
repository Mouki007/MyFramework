package stepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import moduleFunctions.LoginPage;
import testData.TestDataFactory;
import utilities.DynamicWait;
import utilities.ScreenShots;
import utilities.UserActions;
import utilities.WebElementFactory;

public class LoginpageSteps extends Base {
	@Before
	public void setup() throws Exception {
		createDirectory("HTMLReports");
		createDirectory("Screenshot");
		// Logger steps
		logger = Logger.getLogger("My Application");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
		// Logger steps done

		// Loading Config.properties file steps
		configProp = new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		configProp.load(configPropfile);
		// Loading Config.properties file is done
		String browser = configProp.getProperty("Browser");
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

				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-extensions");
				options.addArguments("ignore-certificate-errors");
				driver = new ChromeDriver(options);
				
			}

			threadDriver = new InheritableThreadLocal<WebDriver>();
			threadDriver.set(driver);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			dataFactory = new TestDataFactory();
			dataMap = dataFactory.createTestDataMap();
			lp.set(new LoginPage(driver));
			System.out.println("base "+driver);
			System.out.println("base2 "+threadDriver);
			userActions.set(new UserActions(driver));
			dynamicWait.set(new DynamicWait(driver));
			screenshots.set(new ScreenShots(driver));
			elementFactory.set(new WebElementFactory(driver));
			
		} catch (Exception exception) {
			logger.info("Browser Setup Failure " + ExceptionUtils.getStackTrace(exception));
		}
	}

	public static void createDirectory(String directoryName) {
		File projectDirectory = new File(System.getProperty("user.dir"));
		File newDirectory = new File(projectDirectory, directoryName);
		if (!newDirectory.exists()) {
			newDirectory.mkdir();
		}
	}

	@After
	public void teardown() throws Exception {
		try {
			driver.close();
		} catch (Exception exception) {
			logger.info("Error occurred while quitting the browser " + ExceptionUtils.getStackTrace(exception));
		}

	}
	@Given("User Launch Chrome browser")
	public void user_Launch_Chrome_browser() {

		logger.info("********* Launching browser***************");
	}

	@When("User opens URL {string}")
	public void user_opens_URL(String url) {
		logger.info("********* Opening URL***************");
		System.out.println(driver);
		driver.get(url);
		driver.manage().window().maximize();
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String email, String password) {

		logger.info("********* Proving user info ***************");
		lp.get().setUserName(email);
		lp.get().setPassword(password);
	}

	@When("Click on Login")
	public void click_on_Login() throws InterruptedException {
		lp.get().clickLogin();
		Thread.sleep(5000);
	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String exptitle) throws InterruptedException {
		logger.info("*********Login Validation starts ***************");
		if (driver.getPageSource().contains("Login was unsuccessful")) {
			logger.info("*********Login Failed ***************");
			driver.close();
			Assert.assertTrue(false);
		} else {
			logger.info("*********Login successfull ***************");
			Assert.assertEquals(exptitle, driver.getTitle());
		}
		Thread.sleep(5000);
	}

	@When("User click on Log out link")
	public void user_click_on_Log_out_link() {
		logger.info("*********Logout from application***************");
		lp.get().clickLogout();
	}
}
