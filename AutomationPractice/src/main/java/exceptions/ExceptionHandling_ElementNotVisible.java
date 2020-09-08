package exceptions;

import objectRepository.ObjectFactory;

import org.openqa.selenium.ElementNotVisibleException;

import objectRepository.DataMap;
import objectRepository.UIControlObject;


@SuppressWarnings("serial")
public class ExceptionHandling_ElementNotVisible extends
		ElementNotVisibleException {

	private static String message = null;

	public ExceptionHandling_ElementNotVisible(String controlName) {
		super(null);
		ObjectFactory factory = new ObjectFactory();
		factory.createObjectMap();
		DataMap<String, UIControlObject> map = factory.getObjectMap();
		UIControlObject obj = map.get(controlName);

		message = " ElementNotVisibleException:Element with ControlProperty :"
				+ obj.getControlProperty() + " and TypeofProperty :"
				+ obj.getTypeOfProperty()
				+ " is not visible on the current page";

	}
	@Override
	public String getMessage() {
		return message;
	}
}