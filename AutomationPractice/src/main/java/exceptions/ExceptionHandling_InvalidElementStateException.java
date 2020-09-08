package exceptions;

import objectRepository.ObjectFactory;
import objectRepository.DataMap;
import objectRepository.UIControlObject;

import org.openqa.selenium.InvalidElementStateException;

@SuppressWarnings("serial")
public class ExceptionHandling_InvalidElementStateException extends
		InvalidElementStateException {

	private static String message = null;

	public ExceptionHandling_InvalidElementStateException(String controlname) {
		super(message);
		ObjectFactory factory = new ObjectFactory();
		factory.createObjectMap();
		DataMap<String, UIControlObject> map = factory.getObjectMap();
		UIControlObject obj = map.get(controlname);

		message = " InvalidElementStateException:Action on Element with ControlProperty :"
				+ obj.getControlProperty()
				+ " and TypeofProperty :"
				+ obj.getTypeOfProperty() + " is not possible";

	}

	@Override
	public String getMessage() {
		return message;
	}
}