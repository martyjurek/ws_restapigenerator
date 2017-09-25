package com.misys.api.generator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.misys.api.generator.ApiClassGenerator;
import com.misys.api.generator.ApiClassInfo;
import com.misys.api.generator.ApiMethodInfo;
import com.misys.api.generator.ApiMethodParameterInfo;
import com.misys.api.generator.ApiMethodType;
import com.misys.api.generator.ControllerMethodInfo;
import com.misys.api.utilities.UriInfoUtil;

public class QuickTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApiClassInfo classInfo = new ApiClassInfo();
		classInfo.setPackageName("com.misys.api.test");
		classInfo.setClassName("TestClass");
		classInfo.setUrlPath("/testing");
		
		ApiMethodInfo method1 = new ApiMethodInfo();
		method1.setMethodName("method1");
		method1.setUrlPath("/get/{ent_bank_code}/{ent_bsys_id}/{ent_id}");
		method1.setProducesType(MediaType.APPLICATION_JSON);
		method1.setApiMethodType(ApiMethodType.GET);
		method1.setSuccessMessage("This was successful");
		method1.setErrorMessage("There was an error");
		
		ApiMethodParameterInfo uriInfo = ApiMethodParameterInfo.getUriInfoContextParameter();
		uriInfo.setSpecificMethodName("getQueryParameters");
		uriInfo.setTransformationClass(UriInfoUtil.class);
		uriInfo.setTransformationMethodName("normalizeMultivaluedMap");
		method1.addApiMethodParameter(uriInfo);
		
		ApiMethodParameterInfo httpHeaders = ApiMethodParameterInfo.getHttpHeadersContextParameter();
		method1.addApiMethodParameter(httpHeaders);
		
		ApiMethodParameterInfo param1 = new ApiMethodParameterInfo();
		param1.setType(String.class);
		param1.setName("stuff");
		method1.addApiMethodParameter(param1);
		
		ApiMethodParameterInfo param3 = new ApiMethodParameterInfo();
		param3.setType(String.class);
		param3.setName("stuff2");
		method1.addApiMethodParameter(param3);
		
		ControllerMethodInfo controllerMethod = new ControllerMethodInfo();
		controllerMethod.setClazz(TestController.class);
		//controllerMethod.setMethodName("getStuff");
		//controllerMethod.addParameter(param1);
		controllerMethod.setMethodName("getStuff2");
		controllerMethod.addParameter(uriInfo);
		controllerMethod.addParameter(param3);
		method1.setControllerMethod(controllerMethod);
		
		classInfo.addApiMethod(method1);
		
		ApiMethodInfo method2 = new ApiMethodInfo();
		method2.setApiMethodType(ApiMethodType.POST);
		method2.setUrlPath("/{ent_bank_code}/{ent_bsys_id}/{ent_id}");
		method2.setConsumesType(MediaType.APPLICATION_JSON);
		method2.setProducesType(MediaType.APPLICATION_JSON);
		method2.setMethodName("method2");
		
		ApiMethodParameterInfo param2 = new ApiMethodParameterInfo();
		param2.setType(JsonNode.class);
		param2.setName("stuff");
		param2.setFinal(true);
		param2.setAnnotationClass(PathParam.class);
		param2.setAnnotationValue("ent_bank_code");
		method2.addApiMethodParameter(param2);
		
		ApiMethodParameterInfo paramLiteral = new ApiMethodParameterInfo();
		paramLiteral.setLiteralValue("\"quux\"");
		paramLiteral.setName("bar");
		paramLiteral.setType(String.class);
		
		Map<String, String> testMap = new HashMap<>();
		testMap.put("foo", "\"bar\"");
		testMap.put("baz", "true");
		ApiMethodParameterInfo paramMap = new ApiMethodParameterInfo();
		paramMap.setName("testMap");
		paramMap.setType(Map.class);
		paramMap.setMapValues(testMap);

		ControllerMethodInfo controllerMethod2 = new ControllerMethodInfo();
		controllerMethod2.addParameter(param2);
		controllerMethod2.addParameter(paramLiteral);
		controllerMethod2.addParameter(paramMap);
		controllerMethod2.setClazz(TestController.class);
		controllerMethod2.setMethodName("postStuff");
		controllerMethod2.setReturnsObject(false);
		method2.setControllerMethod(controllerMethod2);
		
		classInfo.addApiMethod(method2);
		
		ApiClassGenerator.generateClass(classInfo);
		
		JsonNode json = new ObjectMapper().valueToTree(classInfo);
		System.out.println(new ObjectMapper().valueToTree(classInfo));
		
		try {
			ApiClassInfo marshallTest = new ObjectMapper().readValue(json.toString(), ApiClassInfo.class);
			System.out.println(new ObjectMapper().valueToTree(marshallTest));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
