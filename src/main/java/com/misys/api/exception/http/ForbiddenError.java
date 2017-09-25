package com.misys.api.exception.http;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents a 403 Forbidden HTTP error.
 */
public class ForbiddenError extends HTTPError {
	private static final long serialVersionUID = 1L;
	private static final String message = "The server cannot process the request because it is forbidden.";
	private static Logger logger = LogManager.getLogger(ForbiddenError.class);
	
	/**
	 * Constructs a new ForbiddenError with no cause.
	 */
	public ForbiddenError() {
		super(message);
		logger.throwing(Level.DEBUG, this);
	}
	
	/**
	 * Construct a new ForbiddenError with the given cause.
	 * @param cause The Throwable that caused the ForbiddenError.
	 */
	public ForbiddenError(Throwable cause) {
		super(message, cause);
		logger.throwing(Level.DEBUG, this);
	}
	
	/**
	 * Constructs a new ForbiddenError with the given List of causes.
	 * @param causes List of Throwables that caused the ForbiddenError.
	 */
	public ForbiddenError(List<Throwable> causes) {
		super(message, causes);
		logger.throwing(Level.DEBUG, this);
	}
	
	/**
	 * Gets the HTTP status code for this.
	 * @return The HTTP status code for this.
	 */
	@Override
	public Status getStatus() {
		return Response.Status.FORBIDDEN;
	}
}
