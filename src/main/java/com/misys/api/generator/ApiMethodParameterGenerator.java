package com.misys.api.generator;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Providers;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ParameterSpec;

import javax.lang.model.element.Modifier;

@Singleton
public class ApiMethodParameterGenerator {

	public static ParameterSpec generateParameter(ApiMethodParameterInfo parameterInfo) {
		ParameterSpec.Builder builder = ParameterSpec.builder(parameterInfo.getType(), parameterInfo.getName());
		if (parameterInfo.getAnnotationClass() != null) {
			builder.addAnnotation(ApiMethodParameterGenerator.generateAnnotation(parameterInfo));
		}
		if (parameterInfo.isFinal()) {
		    builder.addModifiers(Modifier.FINAL);
		}
		return builder.build();
	}
	
	private static AnnotationSpec generateAnnotation(ApiMethodParameterInfo parameterInfo) {
		if (parameterInfo.getAnnotationClass() != null) {
			AnnotationSpec.Builder builder = AnnotationSpec.builder(parameterInfo.getAnnotationClass());
			if (parameterInfo.getAnnotationValue() != null) {
				builder.addMember("value", "$S", parameterInfo.getAnnotationValue());
			}
			return builder.build();
		}
		return null;
	}
	
}
