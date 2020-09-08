package exceptions;

import org.openqa.selenium.UnhandledAlertException;

@SuppressWarnings("serial")
class ExceptionHandling_UnhandledAlertException extends UnhandledAlertException {

	private static String message = null;

	public ExceptionHandling_UnhandledAlertException() {
		super(message);
		message = "Unhandled Alert has popped up";

	}

	@Override
	public String getMessage() {
		return message;
	}
}
