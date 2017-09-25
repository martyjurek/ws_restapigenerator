package com.misys.api.exception.mapper;

import java.util.ArrayList;
import java.util.List;

import com.misys.api.exception.http.HTTPError;

/**
 * Represents the JSON body of Responses with an error status.
 */
public class ErrorResponseBody {
	/**
	 * Represents the error info in the JSON body of a Response with an error status.
	 */
	public class Error {
		private String status;
		private String code;
		private String message;
		private String detail;
		
		/**
		 * Constructs a new Error with the given HTTPError.
		 * @param e The HTTPError the Error will represent.
		 */
		public Error(HTTPError e) {
			status = Integer.toString(e.getStatus().getStatusCode());
			code = e.getStatus().getReasonPhrase();
			message = e.getMessage();
			detail = e.getDetails();
		}
		
		/**
		 * Gets the error code of this Error.
		 * @return The error code of this Error as a String.
		 */
		public String getCode() {
			return code;
		}
		
		/**
		 * Gets the details of the cause of this Error.
		 * @return The details of the cause of this Error.
		 */
		public String getDetail() {
			return detail;
		}
		
		/**
		 * Gets the description of this Error.
		 * @return The description of this Error.
		 */
		public String getMessage() {
			return message;
		}
		
		/**
		 * Gets the status this Error represents.
		 * @note The status is equivalent to the reason-phrase of HTTP status codes.
		 * @return The status this Error represents.
		 */
		public String getStatus() {
			return status;
		}
	}
	
	private List<Error> errors;
	
	/**
	 * Constructs a new ErrorResponseBody with the given HTTPError.
	 * @param e The HTTPError to construct the ErrorResponseBody with.
	 */
	public ErrorResponseBody(HTTPError e) {
		errors = new ArrayList<>();
		errors.add(new Error(e));
	}
	
	/**
	 * Gets the List of Errors that are in this.
	 * @return
	 */
	public List<Error> getErrors() {
		return errors;
	}
}
