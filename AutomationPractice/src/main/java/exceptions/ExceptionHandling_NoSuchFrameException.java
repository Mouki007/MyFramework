package exceptions;

import objectRepository.ObjectFactory;
import objectRepository.DataMap;
import objectRepository.UIControlObject;

import org.openqa.selenium.NoSuchFrameException;

@SuppressWarnings("serial")
public class ExceptionHandling_NoSuchFrameException extends
		NoSuchFrameException {

	private static String message = null;

	public ExceptionHandling_NoSuchFrameException(String controlName) {

		super(message);
		ObjectFactory factory = new ObjectFactory();
		factory.createObjectMap();
		DataMap<String, UIControlObject> map = factory.getObjectMap();
		UIControlObject obj = map.get(controlName);

		message = " NoSuchFrameException :Unable to switch to the Frame as no Frame is present  with FrameName:"
				+ obj.getControlName();

	}

	@Override
	public String getMessage() {
		return message;
	}
}