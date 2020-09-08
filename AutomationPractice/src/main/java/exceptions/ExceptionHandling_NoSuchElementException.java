package exceptions;

import objectRepository.ObjectFactory;
import objectRepository.DataMap;
import objectRepository.UIControlObject;

import org.openqa.selenium.NoSuchElementException;

@SuppressWarnings("serial")
public class ExceptionHandling_NoSuchElementException extends
		NoSuchElementException {

	private String message = null;

	public ExceptionHandling_NoSuchElementException(String controlname) {

		super(null);
		ObjectFactory factory = new ObjectFactory();
		factory.createObjectMap();
		DataMap<String, UIControlObject> map = factory.getObjectMap();
		UIControlObject obj = map.get(controlname);

		message = " NoSuchElementException :Element with ControlName :"
				+ obj.getControlName() + "  is not present on the current page";

	}

	@Override
	public String getMessage() {
		return message;
	}
}
