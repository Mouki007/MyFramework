package exceptions;

import objectRepository.DataMap;
import objectRepository.ObjectFactory;
import objectRepository.UIControlObject;

import org.openqa.selenium.InvalidSelectorException;

@SuppressWarnings("serial")
public class ExceptionHandling_InvalidSelectorException extends
		InvalidSelectorException {

	private static String message = null;

	public ExceptionHandling_InvalidSelectorException(String controlname) {

		super(null);
		ObjectFactory factory = new ObjectFactory();
		factory.createObjectMap();
		DataMap<String, UIControlObject> map = factory.getObjectMap();
		UIControlObject obj = map.get(controlname);

		message = "InvalidSelectorException:Unable to find Element with given ControlProperty :"
				+ obj.getControlProperty()
				+ " and TypeofProperty :"
				+ obj.getTypeOfProperty() + " is not possible";

	}

	@Override
	public String getMessage() {
		return message;
	}
}