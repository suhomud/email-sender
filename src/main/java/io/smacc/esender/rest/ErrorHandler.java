package io.smacc.esender.rest;

import io.smacc.esender.domain.ErrorInfo;
import io.smacc.esender.exception.NoRecipientsException;
import io.smacc.esender.exception.NoWorkedProvidersException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoRecipientsException.class)
	@ResponseBody
	public ErrorInfo handleNoRecipientsException(NoRecipientsException ex, WebRequest request) {
		log.info("ResourceNotFoundException handler:" + ex.getMessage());

		return new ErrorInfo(ex, "Please, add recipient before sending the message");
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(NoWorkedProvidersException.class)
	@ResponseBody
	public ErrorInfo handleNoRecipientsException(NoWorkedProvidersException ex, WebRequest request) {
		log.info("NoWorkedProvidersException() handler:" + ex.getMessage());

		return new ErrorInfo(ex, "Looks like there is no working provider");
	}

}
