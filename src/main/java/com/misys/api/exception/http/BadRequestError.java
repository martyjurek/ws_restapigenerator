package com.misys.api.exception.http;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represent a 400 Bad Request HTTP error.
 */
public class BadRequestError extends HTTPError {
	private static final long serialVersionUID = 1L;
	private static final String message = "The server cannot process the request because it is invalid.";
	private static Logger logger = LogManager.getLogger(BadRequestError.class);
	
	/**
	 * Constructs a new BadRequestError with no cause.
	 */
	public BadRequestError() {
		super(message);
		logger.throwing(Level.DEBUG, this);
	}
	
	/**
	 * Construct a new BadRequestError with the given cause.
	 * @param cause The Throwable that caused the BadRequestError.
	 */
	public BadRequestError(Throwable cause) {
		super(message, cause);
		logger.throwing(Level.DEBUG, this);
	}
	
	/**
	 * Constructs a new BadRequestError with the given List of causes.
	 * @param causes List of Throwables that caused the BadRequestError.
	 */
	public BadRequestError(List<Throwable> causes) {
		super(message, causes);
		logger.throwing(Level.DEBUG, this);
	}
	
	/**
	 * Gets the HTTP status code for this.
	 * @return The HTTP status code for this.
	 */
	@Override
	public Status getStatus() {
		return Response.Status.BAD_REQUEST;
	}
}
