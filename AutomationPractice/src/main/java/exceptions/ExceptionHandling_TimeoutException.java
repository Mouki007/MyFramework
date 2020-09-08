package exceptions;

import objectRepository.DataMap;
import objectRepository.ObjectFactory;
import objectRepository.UIControlObject;

import org.openqa.selenium.TimeoutException;


@SuppressWarnings("serial")
public class ExceptionHandling_TimeoutException extends TimeoutException {

	private String message = null;

	public ExceptionHandling_TimeoutException(String controlName) {

		super("");
		ObjectFactory factory = new ObjectFactory();
		factory.createObjectMap();
		DataMap<String, UIControlObject> map = factory.getObjectMap();
		UIControlObject obj = map.get(controlName);

		message = " TimeoutException :Unable to find the Element :"
				+ obj.getControlName() + "  with in the Time Limit of 60 sec";

	}

	@Override
	public String getMessage() {
		return message;
	}
}