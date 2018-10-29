package com.project.distance.builders;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * Builds the response with the options 
 * informed (content type, http status and response body)
 * 
 * @author Diego Francklin Martins dos Santos
 *
 * @param <T>
 */
public class ResponseBuilder<T> {

	private MediaType mediaType;

	private HttpStatus httpStatus;

	private T body;

	public ResponseBuilder() {
		httpStatus = HttpStatus.OK;
	}

	/**
	 * Sets content type of the response
	 * 
	 * @param body
	 * 
	 * @return ResponseBuilder<T>
	 */
	public ResponseBuilder<T> setContentType(String mediaType) {
		try {
			this.mediaType = AllowedMediaTypes.valueOf(mediaType.toUpperCase()).getMediaType();
		} catch (Exception e) {
			throw new IllegalArgumentException("MediaType \"" + mediaType + "\" not recognized. "
					+ "The accpted media types are \"JSON\" and \"XML\"");
		}
		
		return this;
	}

	/**
	 * Sets the http status of the response
	 * 
	 * @param body
	 * 
	 * @return ResponseBuilder<T>
	 */
	public ResponseBuilder<T> setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
		
		return this;
	}

	/**
	 * Sets the body of the response
	 * 
	 * @param body
	 * 
	 * @return ResponseBuilder<T>
	 */
	public ResponseBuilder<T> setBody(T body) {
		this.body = body;
		
		return this;
	}

	/**
	 * Builds the response with the options informed
	 * 
	 * @return ResponseEntity<T>
	 */
	public ResponseEntity<T> build() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType);
		
		return new ResponseEntity<T>(body, headers, httpStatus);
	}

	/**
	 * Enum that indicates the allowed media types
	 * 
	 * @author Diego Francklin Martins dos Santos
	 */
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
