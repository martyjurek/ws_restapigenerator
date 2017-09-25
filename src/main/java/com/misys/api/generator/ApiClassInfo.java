package com.misys.api.generator;

import java.util.ArrayList;
import java.util.List;

public class ApiClassInfo {

	private String packageName;
	private String className;
	private String urlPath;
	private List<ApiMethodInfo> apiMethods;
	
	public ApiClassInfo() {
		this.apiMethods = new ArrayList<ApiMethodInfo>();
	}

	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<ApiMethodInfo> getApiMethods() {
		return this.apiMethods;
	}

	public void setApiMethods(List<ApiMethodInfo> apiMethods) {
		this.apiMethods = apiMethods;
	}
	
	public void addApiMethod(ApiMethodInfo apiMethod) {
		this.apiMethods.add(apiMethod);
	}

	public String getUrlPath() {
		return this.urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	
}
