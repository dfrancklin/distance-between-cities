package com.project.distance.builders;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder<T> {

	private MediaType mediaType;

	private HttpStatus httpStatus;

	private T body;

	public ResponseBuilder() {
		httpStatus = HttpStatus.OK;
	}

	public ResponseBuilder<T> setContentType(String mediaType) {
		try {
			this.mediaType = AllowedMediaTypes.valueOf(mediaType.toUpperCase()).getMediaType();
		} catch (Exception e) {
			throw new IllegalArgumentException("MediaType \"" + mediaType + "\" not recognized. "
					+ "The accpted media types are \"JSON\" and \"XML\"");
		}
		
		return this;
	}

	public ResponseBuilder<T> setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
		
		return this;
	}

	public ResponseBuilder<T> setBody(T body) {
		this.body = body;
		
		return this;
	}

	public ResponseEntity<T> build() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType);
		
		return new ResponseEntity<T>(body, headers, httpStatus);
	}

	public enum AllowedMediaTypes {
		
		JSON(MediaType.APPLICATION_JSON_UTF8),
		XML(MediaType.APPLICATION_XML);
		
		private MediaType mediaType;
		
		private AllowedMediaTypes(MediaType mediaType) {
			this.mediaType = mediaType;
		}
		
		public MediaType getMediaType() {
			return mediaType;
		}
	}

}
