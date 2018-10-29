package com.project.distance.controllers.errors;

import java.util.Calendar;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.distance.builders.ResponseBuilder;

/**
 * This controller receive the errors and responds with a message
 * 
 * @author Diego Francklin Martins dos Santos
 */
@RestControllerAdvice
public class ErrorHandlerController extends ResponseEntityExceptionHandler {

	/**
	 * Returns a response when an IllegalArgumentException happens
	 * 
	 * @param ex
	 * @param request
	 * 
	 * @return ResponseEntity<ErrorDetail>
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public final ResponseEntity<ErrorDetail> handleIllegalArgument(
		IllegalArgumentException ex,
		WebRequest request
	) {
		return response(HttpStatus.BAD_REQUEST, "Bad Request", ex.getMessage());
	}

	/**
	 * Returns a response when an Exception happens
	 * 
	 * @param ex
	 * @param request
	 * 
	 * @return ResponseEntity<ErrorDetail>
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetail> handleExcception(
		Exception ex,
		WebRequest request
	) {
		return response(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage());
	}

	/**
	 * Creates a response to an error with the given parameters
	 * 
	 * @param httpStatus
	 * @param message
	 * @param detail
	 * 
	 * @return ResponseEntity<ErrorDetail>
	 */
	private ResponseEntity<ErrorDetail> response(
		HttpStatus httpStatus,
		String message,
		String detail
	) {
		ErrorDetail errorDetail = new ErrorDetail(
			Calendar.getInstance(),
			httpStatus.value(),
			message,
			detail
		);
		
		return new ResponseBuilder<ErrorDetail>()
			.setHttpStatus(httpStatus)
			.setBody(errorDetail)
			.build()
		;
	}

}
