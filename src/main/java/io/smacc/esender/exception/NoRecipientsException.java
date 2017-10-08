package io.smacc.esender.exception;

public class NoRecipientsException extends RuntimeException {

	public NoRecipientsException() {
		super("No recipient");
	}

}
