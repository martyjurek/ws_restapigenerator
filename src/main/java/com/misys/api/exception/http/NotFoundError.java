package com.misys.api.exception.http;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents a 404 Not Found HTTP error.
 */
public class NotFoundError extends HTTPError {
	private static final long serialVersionUID = 1L;
	private static final String message = "The server could not find the resource at ";
	private static Logger logger = LogManager.getLogger(NotFoundError.class);
	
	/**
	 * Constructs a new NotFoundError with given URI that caused it.
	 * @param causingURI The URI that caused the NotFoundError.
	 */
	public NotFoundError(URI causingURI) {
		super(message + causingURI.toString());
		logger.throwing(Level.DEBUG, this);
	}
	
	/**
	 * Constructs a new NotFoundError with the given URI that caused it and Throwable that was thrown.
	 * @param causingURI The URI that caused the NotFoundError.
	 * @param cause The Throwable that caused the NotFoundError.
	 */
	public NotFoundError(URI causingURI, Throwable cause) {
		super(message + causingURI.toString(), cause);
		logger.throwing(Level.DEBUG, this);
	}
	
	/**
	 * Constructs a new NotFoundError with the given URI that caused it and Throwables that were thrown.
	 * @param causingURI The URI that caused the NotFoundError.
	 * @param causes The List of Throwables that caused the NotFoundError.
	 */
	public NotFoundError(URI causingURI, List<Throwable> causes) {
		super(message + causingURI.toString(), causes);
		logger.throwing(Level.DEBUG, this);
	}
	
	/**
	 * Gets a detailed description of the cause(s) of this NotFoundError.
	 * @note This always returns null because the basic message is all that should be reported to users.
	 * @return The detailed description of the cause(s) of this NotFoundError if there are any causes; null otherwise.
	 */
	@Override
	public String getDetails() {
		return null;
	}
	
	/**
	 * Gets the HTTP status code for this.
	 * @return The HTTP status code for this.
	 */
	@Override
	public Status getStatus() {
		return Response.Status.NOT_FOUND;
	}
}
