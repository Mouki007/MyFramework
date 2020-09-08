package exceptions;

import org.openqa.selenium.SessionNotCreatedException;

@SuppressWarnings("serial")
public class ExceptionHandling_SessionNotCreatedException extends
		SessionNotCreatedException {

	private static String message = "Unable to Create Session";

	public ExceptionHandling_SessionNotCreatedException() {
		super(message);

	}

	@Override
	public String getMessage() {
		return message;
	}
}