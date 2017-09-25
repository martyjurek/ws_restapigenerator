package com.misys.api.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.misys.api.exception.http.HTTPError;

/**
 * Maps an HTTPError to a Response.
 * The Response will have an appropriate status code for the error and a JSON body containing details of the error.
 */
@Provider
public class HTTPErrorMapper implements ExceptionMapper<HTTPError> {
	/**
	 * Converts the given HTTPError to a Response.
	 * @param e The HTTPError to convert.
	 * @return A new Response.
	 */
	@Override
	public Response toResponse(HTTPError e) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode responseBody = mapper.valueToTree(new ErrorResponseBody(e));
		
		return Response
			.status(e.getStatus())
			.entity(responseBody)
			.build();
	}
}
