package exceptions;

import org.openqa.selenium.NoAlertPresentException;

@SuppressWarnings("serial")
public class ExceptionHandling_NoAlertPresentException extends
		NoAlertPresentException {

	private static String message = "No Alert or pop-up is displayed";

	public ExceptionHandling_NoAlertPresentException() {
		super();
	}

	
	@Override
	public String getMessage() {
		return message;
	}
}
