package com.misys.api.exception.http;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents a 401 Unauthorized HTTP error.
 */
public class UnauthorizedError extends HTTPError {
	private static final long serialVersionUID = 1L;
	private static final String message = "The server cannot process the request because it is missing the necessary credentials.";
	private static Logger logger = LogManager.getLogger(UnauthorizedError.class);
	
	/**
	 * Constructs a new UnauthorizedError with no cause.
	 */
	public UnauthorizedError() {
		super(message);
		logger.throwing(Level.DEBUG, this);
	}
	
	/**
	 * Construct a new UnauthorizedError with the given cause.
	 * @param cause The Throwable that caused the UnauthorizedError.
	 */
	public UnauthorizedError(Throwable cause) {
		super(message, cause);
		logger.throwing(Level.DEBUG, this);
	}
	
	/**
	 * Construct a new UnauthorizedError with the given List of causes.
	 * @param causes List of Throwables that caused the UnauthorizedError.
	 */
	public UnauthorizedError(List<Throwable> causes) {
		super(message, causes);
		logger.throwing(Level.DEBUG, this);
	}
	
	/**
	 * Gets the HTTP status code for this.
	 * @return The HTTP status code for this.
	 */
	@Override
	public Status getStatus() {
		return Response.Status.UNAUTHORIZED;
	}
}
