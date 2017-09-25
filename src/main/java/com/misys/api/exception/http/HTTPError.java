package com.misys.api.exception.http;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

/**
 * Represents an HTTP error (either client or server).
 */
public abstract class HTTPError extends RuntimeException {
	private static final long serialVersionUID = 1L;
	protected final List<Throwable> causes;
	
	/**
	 * Constructs a new HTTPError with the given message and no cause.
	 * @param message The detail message of this HTTPError.
	 */
	public HTTPError(String message) {
		super(message);
		List<Throwable> causes = new ArrayList<>();
		this.causes = Collections.unmodifiableList(causes);
	}
	
	/**
	 * Construct a new HTTPError with the given cause and message.
	 * @param message The detail message of this HTTPError.
	 * @param cause The Throwable that caused the HTTPError.
	 */
	public HTTPError(String message, Throwable cause) {
		super(message, cause);
		List<Throwable> causes = new ArrayList<>();
		causes.add(cause);
		this.causes = Collections.unmodifiableList(causes);
	}
	
	/**
	 * Constructs a new HTTPError with the given List of causes.
	 * @param message The detail message of this HTTPError.
	 * @param causes List of Throwables that caused the HTTPError.
	 */
	public HTTPError(String message, List<Throwable> causes) {
		super(message);
		this.causes = Collections.unmodifiableList(causes);
	}
	
	/**
	 * Gets the List of causes of this HTTP error.
	 * @return The List of causes of this HTTP error.
	 */
	public List<Throwable> getAllCauses() {
		return causes;
	}
	
	/**
	 * Gets the cause of this HTTPError.
	 * @return The cause of this HTTPError or null if there are 0 or more than 1 causes.
	 */
	@Override
	public Throwable getCause() {
		if (causes.size() == 1) {
			return causes.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Gets a detailed description of the cause(s) of this HTTP error.
	 * @return The detailed description of the cause(s) of this HTTP error if there are any causes; null otherwise.
	 */
	public String getDetails() {
		if (causes.size() != 0) {
			List<String> details =
				causes.stream()
					.map(cause -> cause.getClass().getSimpleName() + ": " + cause.getMessage())
					.collect(Collectors.toList());
			return String.join("\n", details);
		} else {
			return null;
		}
	}
	
	/**
	 * Gets the HTTP status code for this HTTP error.
	 * @return The HTTP status code for this HTTP error.
	 */
	public abstract Response.Status getStatus();
}
