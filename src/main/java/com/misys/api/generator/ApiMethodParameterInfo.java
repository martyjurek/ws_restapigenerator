package com.misys.api.generator;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Providers;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ApiMethodParameterInfo {

	public static ApiMethodParameterInfo getApplicationContextParameter() {
		ApiMethodParameterInfo parameter = new ApiMethodParameterInfo();
		parameter.setAnnotationClass(Context.class);
		parameter.setType(Application.class);
		parameter.setName("application");
		return parameter;
	}
	
	public static ApiMethodParameterInfo getHttpHeadersContextParameter() {
		ApiMethodParameterInfo parameter = new ApiMethodParameterInfo();
		parameter.setAnnotationClass(Context.class);
		parameter.setType(HttpHeaders.class);
		parameter.setName("httpHeaders");
		return parameter;
	}
	
	public static ApiMethodParameterInfo getPathParamParameter(String paramName) {
		ApiMethodParameterInfo parameter = new ApiMethodParameterInfo();
		parameter.setAnnotationClass(PathParam.class);
		parameter.setAnnotationValue(paramName);
		parameter.setType(String.class);
		parameter.setName(paramName);
		return parameter;
	}
	
	public static ApiMethodParameterInfo getProvidersContextParameter() {
		ApiMethodParameterInfo parameter = new ApiMethodParameterInfo();
		parameter.setAnnotationClass(Context.class);
		parameter.setType(Providers.class);
		parameter.setName("providers");
		return parameter;
	}
	
	public static ApiMethodParameterInfo getSecurityContextParameter() {
		ApiMethodParameterInfo parameter = new ApiMethodParameterInfo();
		parameter.setAnnotationClass(Context.class);
		parameter.setType(SecurityContext.class);
		parameter.setName("securityContext");
		return parameter;
	}
	
	public static ApiMethodParameterInfo getRequestContextParameter() {
		ApiMethodParameterInfo parameter = new ApiMethodParameterInfo();
		parameter.setAnnotationClass(Context.class);
		parameter.setType(Request.class);
		parameter.setName("request");
		return parameter;
	}
	
	public static ApiMethodParameterInfo getUriInfoContextParameter() {
		ApiMethodParameterInfo parameter = new ApiMethodParameterInfo();
		parameter.setAnnotationClass(Context.class);
		parameter.setType(UriInfo.class);
		parameter.setName("uriInfo");
		return parameter;
	}
	
	private Class annotationClass;
	private String annotationValue;
	private Class type;
	private String name;
	private String specificMethodName;
	private Class transformationClass;
	private String transformationMethodName;
	private String literalValue;
	private boolean isFinal;
	private Map<String, String> mapValues;
	
    public Class getAnnotationClass() {
		return annotationClass;
	}
	
	public void setAnnotationClass(Class annotationClass) {
		this.annotationClass = annotationClass;
	}
	
	public String getAnnotationValue() {
		return annotationValue;
	}
	
	public void setAnnotationValue(String annotationValue) {
		this.annotationValue = annotationValue;
	}
	
	public Class getType() {
		return type;
	}
	
	public void setType(Class type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getSpecificMethodName() {
		return this.specificMethodName;
	}

	public void setSpecificMethodName(String specificMethodName) {
		this.specificMethodName = specificMethodName;
	}

	public Class getTransformationClass() {
		return this.transformationClass;
	}

	public void setTransformationClass(Class transformationClass) {
		this.transformationClass = transformationClass;
	}

	public String getTransformationMethodName() {
		return this.transformationMethodName;
	}

	public void setTransformationMethodName(String transformationMethodName) {
		this.transformationMethodName = transformationMethodName;
	}
	
	public boolean hasTransformation() {
		return this.transformationClass != null && this.transformationMethodName != null;
	}

	public String getLiteralValue() {
	    return literalValue;
	}

	public void setLiteralValue(String literalValue) {
	    this.literalValue = literalValue;
	}
	
	public boolean isLiteralValue() {
	    return this.literalValue != null;
	}

	public boolean isFinal() {
	    return isFinal;
	}

	public void setFinal(boolean isFinal) {
	    this.isFinal = isFinal;
	}

	@JsonIgnore
	public boolean isMap() {
	    return this.mapValues != null;
	}

	public Map<String, String> getMapValues() {
	    return mapValues;
	}

	public void setMapValues(Map<String, String> mapValues) {
	    this.mapValues = mapValues;
	}
}
