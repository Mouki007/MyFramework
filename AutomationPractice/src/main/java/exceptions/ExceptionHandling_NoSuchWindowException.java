package exceptions;

import org.openqa.selenium.NoSuchWindowException;

@SuppressWarnings("serial")
public class ExceptionHandling_NoSuchWindowException extends
		NoSuchWindowException {

	private static String message = "NoSuchWindowException :Unable to switch to the Window as no new Window is present";;

	public ExceptionHandling_NoSuchWindowException() {
		super(message);

	}

	@Override
	public String getMessage() {
		return message;
	}
}
