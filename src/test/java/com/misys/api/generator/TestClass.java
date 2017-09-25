package com.misys.api.generator;

import com.fasterxml.jackson.databind.JsonNode;
import java.lang.String;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
@Path("/testing")
public class TestClass {
  private static Logger logger = LogManager.getLogger(TestClass.class.getName());

  /*@GET
  @Path("/get")
  @Produces("application/json")
  public static Response method1(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders, String stuff, String stuff2) {
    logger.entry();
    Response.ResponseBuilder rb = Response.status(Response.Status.OK);
    try {
      Object object = TestController.getStuff2(com.misys.api.utilities.UriInfoUtil.normalizeMultivaluedMap(uriInfo.getQueryParameters()), stuff2);
      rb.entity(object);
      rb.header("X-Messages", "This was successful");
    } catch(Exception e) {
      rb.status(Response.Status.INTERNAL_SERVER_ERROR);
      rb.header("X-Errors", "There was an error");
      logger.log(Level.ERROR, e.getMessage());
    }
    return logger.exit(rb.build());
  }*/

  @POST
  @Path("/stuff")
  @Consumes("application/json")
  @Produces("application/json")
  public static Response method2(JsonNode stuff) {
    logger.entry();
    Response.ResponseBuilder rb = Response.status(Response.Status.OK);
    try {
      Object object = TestController.postStuff(stuff);
      rb.entity(object);
    } catch(Exception e) {
      rb.status(Response.Status.INTERNAL_SERVER_ERROR);
      logger.log(Level.ERROR, e.getMessage());
    }
    return logger.exit(rb.build());
  }
}
