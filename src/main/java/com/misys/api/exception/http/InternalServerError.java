package com.misys.api.exception.http;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents a 500 Internal Server Error HTTP error.
 */
public class InternalServerError extends HTTPError {
	private static final long serialVersionUID = 1L;
	private static final String message = "The server encountered an error.";
	private static Logger logger = LogManager.getLogger(InternalServerError.class);
	
	/**
	 * Constructs a new InternalServerError with no cause.
	 */
	public InternalServerError() {
		super(message);
		logger.throwing(Level.ERROR, this);
	}
	
	/**
	 * Construct a new InternalServerError with the given cause.
	 * @param cause The Throwable that caused the InternalServerError.
	 */
	public InternalServerError(Throwable cause) {
		super(message, cause);
		logger.throwing(Level.ERROR, this);
	}
	
	/**
	 * Constructs a new InternalServerError with the given List of causes.
	 * @param causes List of Throwables that caused the InternalServerError.
	 */
	public InternalServerError(List<Throwable> causes) {
		super(message, causes);
		logger.throwing(Level.ERROR, this);
	}
	
	/**
	 * Gets a detailed description of the cause(s) of this InternalServerError.
	 * @note This always returns null because users should never see the details of an InternalServerError.
	 * @return The detailed description of the cause(s) of this InternalServerError if there are any causes; null otherwise.
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
		return Response.Status.INTERNAL_SERVER_ERROR;
	}
}
