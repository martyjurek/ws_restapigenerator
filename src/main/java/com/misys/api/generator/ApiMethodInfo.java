package com.misys.api.generator;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.OPTIONS;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ApiMethodInfo {

	private String urlPath;
	private String methodName;
	private ApiMethodType apiMethodType;
	private List<ApiMethodParameterInfo> apiMethodParameters;
	private String consumesType;
	private String producesType;
	private String successMessage;
	private String errorMessage;
	private ControllerMethodInfo controllerMethod;
	
	public ApiMethodInfo() {
		this.apiMethodParameters = new ArrayList<ApiMethodParameterInfo>();
	}

	public String getUrlPath() {
		return this.urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	@JsonIgnore
	public Class getApiMethodTypeClass() {
		switch(this.apiMethodType) {
			case GET:
				return GET.class;
			case PUT:
				return PUT.class;
			case POST:
				return POST.class;
			case DELETE:
				return DELETE.class;
			case OPTIONS:
			    return OPTIONS.class;
			default:
				return null;
		}
	}
	
	public ApiMethodType getApiMethodType() {
		return this.apiMethodType;
	}

	public void setApiMethodType(ApiMethodType apiMethodType) {
		this.apiMethodType = apiMethodType;
	}

	public List<ApiMethodParameterInfo> getApiMethodParameters() {
		return this.apiMethodParameters;
	}

	public void setApiMethodParameters(List<ApiMethodParameterInfo> apiMethodParameters) {
		this.apiMethodParameters = apiMethodParameters;
	}
	
	public void addApiMethodParameter(ApiMethodParameterInfo apiMethodParameter) {
		this.apiMethodParameters.add(apiMethodParameter);
	}

	public String getConsumesType() {
		return this.consumesType;
	}

	public void setConsumesType(String consumesType) {
		this.consumesType = consumesType;
	}

	public String getProducesType() {
		return this.producesType;
	}

	public void setProducesType(String producesType) {
		this.producesType = producesType;
	}

	public String getSuccessMessage() {
		return this.successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ControllerMethodInfo getControllerMethod() {
		return this.controllerMethod;
	}

	public void setControllerMethod(ControllerMethodInfo controllerMethod) {
		this.controllerMethod = controllerMethod;
	}

}
