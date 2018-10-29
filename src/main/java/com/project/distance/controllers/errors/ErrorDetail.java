package com.project.distance.controllers.errors;

import java.util.Calendar;

public class ErrorDetail {

	private Calendar timestamp;

	private int httpStatus;

	private String message;

	private String details;

	public ErrorDetail(Calendar timestamp, int httpStatus, String message, String details) {
		this.timestamp = timestamp;
		this.httpStatus = httpStatus;
		this.message = message;
		this.details = details;
	}

	public Calendar getTimestamp() {
		return timestamp;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

}
