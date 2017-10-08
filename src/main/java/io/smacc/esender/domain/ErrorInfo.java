package io.smacc.esender.domain;

public class ErrorInfo {
	public final String detail;
	public final String message;

	public ErrorInfo(Exception ex, String detail) {
		this.message = ex.getLocalizedMessage();
		this.detail = detail;
	}

}
