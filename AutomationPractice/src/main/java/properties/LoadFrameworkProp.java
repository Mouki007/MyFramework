package properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.Reporter;

public class LoadFrameworkProp {
	
	private String objectRepository;
	private String testDataFile;
	private String locatorSheetName;
	private String testDataSheetName;
	private String username;
	private String password;
	private String screenshots;

	public String getUserName() {
		return username;
	}
	
	
	public String getPassword() {
		return password;
	}
	
	public String getObjectRepository() {
		return objectRepository;
	}
	public String getTestDataFile() {
		return testDataFile;
	}
	public String getTestDataSheet() {
		return testDataSheetName;
	}
	public String getLocatorSheet() {
		return locatorSheetName;
	}
	public String getScreenshot() {
		return screenshots;
	}
	public LoadFrameworkProp() {
		Properties prop = new Properties();
		InputStream is = null;
		try {
			File config = new File("config.properties");
			is = new FileInputStream(config);
		} catch (Exception exception) {
			Reporter.log(ExceptionUtils.getStackTrace(exception));
			is = null;
		}
		try {
			if (is == null) {
				is = getClass().getResourceAsStream("config.properties");
			}
			prop.load(is);
			
			objectRepository = prop.getProperty("ObjectRepository");
		    testDataFile = prop.getProperty("TestDataFile");
			locatorSheetName =prop.getProperty("LocatorSheetName");
			testDataSheetName=prop.getProperty("TestDataSheetName");
			username = prop.getProperty("Username");
			password = prop.getProperty("Password");
			screenshots=prop.getProperty("Screenshots");
			
		} catch (Exception exception) {
			Reporter.log(ExceptionUtils.getStackTrace(exception));
		}
	}
}