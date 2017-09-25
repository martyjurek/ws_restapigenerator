package com.misys.api.exception.http;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents a 409 Conflict HTTP error.
 */
public class ConflictError extends HTTPError {
	private static final long serialVersionUID = 1L;
	private static final String message = "The server cannot process the request because it contains a conflict.";
	private static Logger logger = LogManager.getLogger(ConflictError.class);
	
	/**
	 * Constructs a new ConflictError with no cause.
	 */
	public ConflictError() {
		super(message);
		logger.throwing(Level.DEBUG, this);
	}
	
	/**
	 * Construct a new ConflictError with the given cause.
	 * @param cause The Throwable that caused the ConflictError.
	 */
	public ConflictError(Throwable cause) {
		super(message, cause);
		logger.throwing(Level.DEBUG, this);
	}
	
	/**
	 * Constructs a new ConflictError with the given List of causes.
	 * @param causes List of Throwables that caused the ConflictError.
	 */
	public ConflictError(List<Throwable> causes) {
		super(message, causes);
		logger.throwing(Level.DEBUG, this);
	}
	
	/**
	 * Gets the HTTP status code for this.
	 * @return The HTTP status code for this.
	 */
	@Override
	public Status getStatus() {
		return Response.Status.CONFLICT;
	}
}
