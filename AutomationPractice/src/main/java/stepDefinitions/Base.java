package stepDefinitions;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import moduleFunctions.LoginPage;
import properties.LoadFrameworkProp;
import testData.TestDataFactory;
import testData.TestDataMap;
import utilities.DynamicWait;
import utilities.ScreenShots;
import utilities.UserActions;
import utilities.WebElementFactory;

public class Base {

	    public WebDriver driver;
	    public static Logger logger;
	    Properties configProp;
	    protected InheritableThreadLocal<WebDriver> threadDriver = null;
		protected DesiredCapabilities caps;
		
		LoadFrameworkProp frameworkConfig = new LoadFrameworkProp();
		
		protected InheritableThreadLocal<UserActions> userActions = new InheritableThreadLocal<UserActions>();
		protected InheritableThreadLocal<DynamicWait> dynamicWait = new InheritableThreadLocal<DynamicWait>();
		protected InheritableThreadLocal<WebElementFactory> elementFactory = new InheritableThreadLocal<WebElementFactory>();
		protected InheritableThreadLocal<ScreenShots> screenshots = new InheritableThreadLocal<ScreenShots>();

		protected TestDataMap<String, String> dataMap;
		protected TestDataFactory dataFactory;
		
		protected InheritableThreadLocal<LoginPage> lp = new InheritableThreadLocal<LoginPage>();
		

}
