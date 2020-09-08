package utilities;

import java.util.List;

import objectRepository.ObjectFactory;
import objectRepository.DataMap;
import objectRepository.UIControlObject;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import exceptions.ExceptionHandling_ElementNotVisible;
import exceptions.ExceptionHandling_NoSuchElementException;

public class WebElementFactory {

	protected WebDriver driver;
	protected ScreenShots screenshots;

	public WebElementFactory(WebDriver driver) {
		this.driver = driver;
		screenshots = new ScreenShots(driver);
	}

	public void doMouseAction(String controlName, String typeOfAction) {
		WebElement element = getElement(controlName);
		Actions actions = new Actions(driver);
		Action action;
		switch (typeOfAction) {
		case "clear":
			element.clear();
			break;
		case "click":
			element.click();
			break;
		case "clickandhold":
			actions.clickAndHold(element);
			action = actions.build();
			action.perform();
			break;
		case "doubleclick":
			actions.doubleClick(element);
			action = actions.build();
			action.perform();
			break;
		case "contextclick":
			actions.contextClick(element);
			action = actions.build();
			action.perform();
			break;
		case "hover":
			actions.moveToElement(element);
			action = actions.build();
			action.perform();
			break;
		case "release":
			actions.release(element);
			action = actions.build();
			action.perform();
			break;
		default:
			break;
		}
	}

	public By getLocator(String controlProperty, String propertyType) {

		switch (propertyType) {
		case "xpath":
			return By.xpath(controlProperty);
		case "id":
			return By.id(controlProperty);
		case "name":
			return By.name(controlProperty);
		case "linktext":
			return By.linkText(controlProperty);
		case "partiallinktext":
			return By.partialLinkText(controlProperty);
		case "classname":
			return By.className(controlProperty);
		case "cssSelector":
			return By.cssSelector(controlProperty);
		case "tagname":
			return By.tagName(controlProperty);
		default:
			return null;
		}
	}

	public By findBy(String controlName) {
		ObjectFactory factory = new ObjectFactory();
		factory.createObjectMap();
		DataMap<String, UIControlObject> map = factory.getObjectMap();
		UIControlObject obj = map.get(controlName);
		By elementLocator = getLocator(obj.getControlProperty(), obj.getTypeOfProperty());
		return elementLocator;
	}

	public WebElement getElement(String controlName) {

		List<WebElement> element = driver.findElements(findBy(controlName));
		if (element.size() == 0) {
			screenshots.takeScreenShots();
			Reporter.log("Element not present in page");
			throw new ExceptionHandling_NoSuchElementException(controlName);
		} else {
			return element.get(0);
		}
	}
	public List<WebElement> getElements(String controlName) {

		List<WebElement> elements = driver.findElements(findBy(controlName));
		if (elements.size() == 0) {
			screenshots.takeScreenShots();
			Reporter.log("Element not present in page");
			throw new ExceptionHandling_NoSuchElementException(controlName);
		} else {
			return elements;
		}
	}
	public List<WebElement> getChildElements(WebElement parent, String controlName) {

		List<WebElement> element = parent.findElements(findBy(controlName));
		if (element.size() == 0) {
			screenshots.takeScreenShots();
			throw new ExceptionHandling_NoSuchElementException(controlName);
		} else {
			return element;
		}
	}
	
	public void enterText(String controlName, String text) {
		Actions actions = new Actions(driver);
		WebElement element = getElement(controlName);
		try {
			element.clear();
			actions.sendKeys(element, text).perform();
		} catch (Exception exception) {
			screenshots.takeScreenShots();
			Reporter.log("Element is not currently visible and so may not be interacted with "
					+ ExceptionUtils.getStackTrace(exception));
			throw new ExceptionHandling_ElementNotVisible(controlName);
		}
	}
	public void enterText(WebElement element, String text) {
		Actions actions = new Actions(driver);
		try {
			element.clear();
			actions.sendKeys(element, text).perform();
		} catch (Exception exception) {
			screenshots.takeScreenShots();
			Reporter.log("Element is not currently visible and so may not be interacted with "
					+ ExceptionUtils.getStackTrace(exception));
		}
	}
	
	public void enterTextOnActivatedElement(String controlName, String text) {
		WebElement element = getElement(controlName);
		element.click();
		driver.switchTo().activeElement().sendKeys(text);
	}
	public String getElementText(String controlName) {
		WebElement element = getElement(controlName);
		String Text = element.getText();
		return Text;
	}

	public List<WebElement> getElementPresent(String controlName) {
		return	driver.findElements(findBy(controlName));
		 
	}

	public Boolean isFramePresent(String frameName) {
		if (driver.findElements(By.tagName(frameName)).size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	public Alert switchToAlert() {
		return driver.switchTo().alert();
	}

	public void dynamicWait(String controlName, long maxSeconds) {
		long maxMilliseconds = maxSeconds * 10;
		long additionalDelay = 2;
		long elapsedMilliseconds = 100;
		while (++elapsedMilliseconds < maxMilliseconds) {
			try {
				WebElement element = driver.findElement(findBy(controlName));
				element.isDisplayed();
				break;
			} catch (NoSuchElementException | NullPointerException | StaleElementReferenceException exception) {
				Reporter.log(ExceptionUtils.getStackTrace(exception));
			}
			try {
				Thread.sleep(elapsedMilliseconds + additionalDelay);
			} catch (Exception exception) {
				Reporter.log(ExceptionUtils.getStackTrace(exception));
			}
			elapsedMilliseconds = elapsedMilliseconds + additionalDelay;
		}
	}

}
