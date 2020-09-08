package utilities;

import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import objectRepository.ObjectFactory;
import objectRepository.UIControlObject;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import exceptions.ExceptionHandling_InvalidElementStateException;
import exceptions.ExceptionHandling_NoSuchWindowException;
import testData.TestDataFactory;
import testData.TestDataMap;
import utilities.DynamicWait;
import utilities.WebElementFactory;

public class UserActions {

	protected WebElementFactory elementFactory;
	private String parentWindow = null;
	protected DynamicWait dynamicWait;
	private Alert alert = null;
	private WebDriver driver;
	private int timer = 1;
	public TestDataMap<String, String> dataMap;
	protected ScreenShots screenshots;

	public UserActions(WebDriver driver) {
		this.driver = driver;
		elementFactory = new WebElementFactory(driver);
		dynamicWait = new DynamicWait(driver);
		TestDataFactory dataFactory = new TestDataFactory();
		dataMap = dataFactory.createTestDataMap();
		screenshots = new ScreenShots(driver);
	}

	public void hoverOn(WebElement controlName) {
		Actions actions = new Actions(driver);
		Action action;
		actions.moveToElement(controlName);
		action = actions.build();
		action.perform();
	}

	public void clearTextBox(String controlName) {
		elementFactory.doMouseAction(controlName, "clear");
	}

	public void enterText(String controlName, String text) {

		elementFactory.enterText(controlName, text);
	}

	public void enterText(WebElement controlName, String text) {

		elementFactory.enterText(controlName, text);
	}

	public void enterTestData(String controlname, String dataControlName) {
		String inputText = dataMap.get(dataControlName);
		elementFactory.enterText(controlname, inputText);
	}

	public void enterTestData(WebElement controlname, String dataControlName) {
		String inputText = dataMap.get(dataControlName);
		elementFactory.enterText(controlname, inputText);
	}

	public void enterTextOnActiveElement(String controlName, String dataControlName) {
		String inputText = dataMap.get(dataControlName);
		elementFactory.enterTextOnActivatedElement(controlName, inputText);
	}

	public void clickAlertOk() {
		alert = elementFactory.switchToAlert();
		alert.accept();
	}

	public void clickOn(String controlName) {
		try {
			elementFactory.doMouseAction(controlName, "click");
		} catch (Exception exception) {
			screenshots.takeScreenShots();
			Reporter.log(ExceptionUtils.getStackTrace(exception));
			throw new ExceptionHandling_InvalidElementStateException(controlName);
		}
	}

	public void clickOn(WebElement element) {
		try {
			element.click();
		} catch (NoSuchElementException exception) {
			Reporter.log(ExceptionUtils.getStackTrace(exception));
		}
	}

	public void clickAndHold(String controlName) {
		elementFactory.doMouseAction(controlName, "clickandhold");
	}

	public void doubleClick(String controlName) {
		elementFactory.doMouseAction(controlName, "doubleclick");
	}

	public void contextClick(String controlName) {
		elementFactory.doMouseAction(controlName, "contextclick");
	}

	public void release(String controlName) {
		elementFactory.doMouseAction(controlName, "release");
	}

	public void selectFromDropdown(String ControlName, String dataName) {
		String dataElement = dataMap.get(dataName);
		WebElement element = elementFactory.getElement(ControlName);
		List<WebElement> list = element.findElements(By.tagName("li"));
		for (int i = 0; i < list.size(); i++) {
			if (dataElement.trim().equals(list.get(i).getText().trim())) {
				list.get(i).click();
				break;
			}
		}
	}

	public void selectFromDropdown(WebElement element, String text) {
		List<WebElement> list = element.findElements(By.tagName("option"));
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().trim().contains(text.trim())) {
				list.get(i).click();
				break;
			}
		}
	}

	public void switchToChildWindow() {
		try {
			dynamicWait.waitTime(8);
		} catch (Exception exception) {
			Reporter.log(ExceptionUtils.getStackTrace(exception));
		}
		Set<String> windows = driver.getWindowHandles();
		if (windows.size() != 1) {
			parentWindow = driver.getWindowHandle();
			windows.remove(parentWindow);
			driver.switchTo().window(windows.iterator().next());
			driver.manage().window().maximize();
		} else {
			if (timer == 1) {
				try {
					dynamicWait.waitTime(8);
				} catch (Exception exception) {
					Reporter.log(ExceptionUtils.getStackTrace(exception));
				}
				timer++;
				switchToChildWindow();
			} else {
				screenshots.takeScreenShots();
				throw new ExceptionHandling_NoSuchWindowException();
			}
		}

	}

	public void switchToParentWindow() {
		driver.switchTo().window(parentWindow);
	}

	public boolean isChildWindowOpen() {

		Set<String> windows = driver.getWindowHandles();
		if (windows.size() == 1) {
			return false;
		}
		return true;
	}

	public void closeChildWindow() {
		if (parentWindow == null) {
			switchToChildWindow();
			driver.close();
			switchToParentWindow();
		} else {
			driver.close();
			switchToParentWindow();
		}
	}

	public List<WebElement> getElements(String controlName) {
		return elementFactory.getElements(controlName);
	}

	public WebElement getElement(String controlName) {
		return elementFactory.getElement(controlName);
	}

	public String getText(String controlName) {
		return elementFactory.getElement(controlName).getText();
	}

	public String getCssProperty(String controlName, String cssAttribute) {
		return elementFactory.getElement(controlName).getCssValue(cssAttribute);
	}

	public String getCssProperty(WebElement uiControl, String cssAttribute) {
		return uiControl.getCssValue(cssAttribute);
	}

	public String getHtmlAttribute(String controlName, String htmlAttribute) {
		return elementFactory.getElement(controlName).getAttribute(htmlAttribute);
	}

	public String getHtmlAttribute(WebElement element, String htmlAttribute) {
		return element.getAttribute(htmlAttribute);
	}

	public UIControlObject getUIControlObject(String controlName) {
		ObjectFactory factory = new ObjectFactory();
		factory.createObjectMap();
		return factory.getObjectMap().get(controlName);
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void switchToFrame(String frameName) {
		WebElement frame = elementFactory.getElement(frameName);
		driver.switchTo().frame(frame);
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public void selectByText(String controlName, String text) {
		new Select(elementFactory.getElement(controlName)).selectByVisibleText("text");
	}

	public void forceClickOn(String controlName) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement element = elementFactory.getElement(controlName);
		executor.executeScript("arguments[0].click();", element);
	}

	public void forceClickOn(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void selectFutureDateFromDatePicker(String selectDate, String dates, String noOfDays, String next)
			throws ParseException {
		String status = "null";
		WebElement date = elementFactory.getElement(selectDate);
		clickOn(date);
		dynamicWait.waitForElementToBeClickable(dates);
		String referenceDate = BCCReferenceDate(Integer.parseInt(noOfDays));
		for (int index = 0; index < Integer.parseInt(noOfDays) / 3; index++) {
			List<WebElement> elements = elementFactory.getElements(dates);
			for (WebElement element1 : elements) {
				String date_value = element1.getAttribute("aria-label").trim();
				if (referenceDate.contains(date_value)) {
					clickOn(element1);
					status = "true";
					break;
				}
			}
			if (status == "true") {
				break;
			} else {
				clickOn(next);
			}
		}
	}

	public void selectPastDateFromDatePicker(String selectDate, String dates, String noOfDays, String past)
			throws ParseException {
		String status = "null";
		WebElement date = elementFactory.getElement(selectDate);
		clickOn(date);
		dynamicWait.waitForElementToBeClickable(dates);
		String referenceDate = BCCReferenceDate(Integer.parseInt(noOfDays));
		for (int index = 0; index > Integer.parseInt(noOfDays) / 3; index--) {
			List<WebElement> elements = elementFactory.getElements(dates);
			for (WebElement element1 : elements) {
				String date_value = element1.getAttribute("aria-label").trim();
				if (referenceDate.contains(date_value)) {
					clickOn(element1);
					status = "true";
					dynamicWait.waitTime(1);
					break;
				}
			}
			if (status == "true") {
				break;
			} else {
				clickOn(past);
			}
		}
	}

	public static String BCCReferenceDate(int dateNum) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.DATE, dateNum);
		Date modifiedDate = calendar.getTime();
		String returnDate = dateFormat.format(modifiedDate);
		return returnDate;
	}

	public void cleanDownloadDirectory(String downloadPath) {

		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (File file : dir_contents) {
			if (!file.isDirectory()) {
				file.delete();
			}
		}
	}

	public String getNumaricValue(String controlName) {
		if (elementFactory.getElementPresent(controlName).size() != 0) {
			String textValue = getText(controlName);
			if (!textValue.isEmpty()) {
				String value = textValue.split(" ")[0];
				StringBuilder stringBuilder = new StringBuilder();
				for (int i = 0; i < value.length(); i++) {
					char ch = value.charAt(i);

					if ((ch >= '0' && ch <= '9') || ch == ',') {
						stringBuilder.append(ch);
						continue;
					} else {
						break;
					}
				}
				System.out.println(stringBuilder.toString());
				return stringBuilder.toString();
			} else {
				return "1";
			}
		} else {
			return "1";
		}
	}
}
