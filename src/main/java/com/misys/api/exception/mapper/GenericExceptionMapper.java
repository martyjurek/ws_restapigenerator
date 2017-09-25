package com.misys.api.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.misys.api.exception.http.InternalServerError;

/**
 * Maps an Exception to a Response.
 * The Response will have a 500 status and a JSON body containing details of the error.
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {
	/**
	 * Converts the given Exception to a Response.
	 * @param e The Exception to convert.
	 * @return A new Response.
	 */
	@Override
	public Response toResponse(Exception e) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode responseBody = mapper.valueToTree(new ErrorResponseBody(new InternalServerError(e)));
		
		return Response
			.status(Response.Status.INTERNAL_SERVER_ERROR)
			.entity(responseBody)
			.build();
	}
}
