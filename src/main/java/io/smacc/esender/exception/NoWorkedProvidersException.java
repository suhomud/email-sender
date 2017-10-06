package io.smacc.esender.exception;

public class NoWorkedProvidersException extends RuntimeException {

	public NoWorkedProvidersException() {
		super("The are not worked provider");
	}

}
