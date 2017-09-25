package com.misys.api.generator;

import java.util.ArrayList;
import java.util.List;

public class ControllerMethodInfo {

	private Class clazz;
	private String methodName;
	private boolean returnsObject = true;
	List<ApiMethodParameterInfo> parameters;
	
	public ControllerMethodInfo() {
		this.parameters = new ArrayList<ApiMethodParameterInfo>();
	}

	public Class getClazz() {
		return this.clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public List<ApiMethodParameterInfo> getParameters() {
		return this.parameters;
	}

	public void setParameters(List<ApiMethodParameterInfo> parameters) {
		this.parameters = parameters;
	}
	
	public void addParameter(ApiMethodParameterInfo parameter) {
		this.parameters.add(parameter);
	}
	
	public boolean hasTransformations() {
		for (ApiMethodParameterInfo parameter : this.getParameters()) {
			if (parameter.getTransformationClass() != null &&  parameter.getTransformationMethodName() != null) {
				return true;
			}
		}
		return false;
	}
	
	public boolean returnsObject() {
	    return this.returnsObject;
	}

	public void setReturnsObject(boolean returnsObject) {
	    this.returnsObject = returnsObject;
	}
}
