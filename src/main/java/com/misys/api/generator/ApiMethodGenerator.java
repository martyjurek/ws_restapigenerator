package com.misys.api.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.inject.Singleton;
import javax.lang.model.element.Modifier;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.Level;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

@Singleton
public class ApiMethodGenerator {

	public static List<MethodSpec> generateMethods(List<ApiMethodInfo> methodInfoList) {
		List<MethodSpec> methods = new ArrayList<MethodSpec>();
		for (ApiMethodInfo methodInfo : methodInfoList) {
			methods.add(ApiMethodGenerator.generateMethod(methodInfo));
		}
		return methods;
	}
	
	public static MethodSpec generateMethod(ApiMethodInfo methodInfo) {
		MethodSpec.Builder builder = MethodSpec.methodBuilder(methodInfo.getMethodName())
			.returns(Response.class)
			.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
			.addException(Exception.class);
		
		builder.addAnnotations(ApiMethodGenerator.generateAnnotations(methodInfo));
		builder.addParameters(ApiMethodGenerator.generateParameters(methodInfo));
		builder.addCode(ApiMethodGenerator.generateMethodBody(methodInfo));
		
		return builder.build();
	}
	
	private static List<ParameterSpec> generateParameters(ApiMethodInfo methodInfo) {
		List<ParameterSpec> parameters = new ArrayList<ParameterSpec>();
		for (ApiMethodParameterInfo parameterInfo : methodInfo.getApiMethodParameters()) {
			parameters.add(ApiMethodParameterGenerator.generateParameter(parameterInfo));
		}
		return parameters;
	}
	
	private static List<AnnotationSpec> generateAnnotations(ApiMethodInfo methodInfo) {
		List<AnnotationSpec> annotations = new ArrayList<AnnotationSpec>();

		annotations.add(AnnotationSpec.builder(methodInfo.getApiMethodTypeClass()).build());
		if (!methodInfo.getUrlPath().isEmpty() && !methodInfo.getUrlPath().equals("/")) {
			annotations.add(AnnotationSpec.builder(Path.class).addMember("value", "$S", methodInfo.getUrlPath()).build());
		}
		
		if (methodInfo.getConsumesType() != null) {
			annotations.add(ApiMethodGenerator.generateConsumesAnnotation(methodInfo.getConsumesType()));
		}
		
		if (methodInfo.getProducesType() != null) {
			annotations.add(ApiMethodGenerator.generateProducesAnnotation(methodInfo.getProducesType()));
		}
		
		return annotations;
	}
	
	private static CodeBlock generateMethodBody(ApiMethodInfo methodInfo) {
		CodeBlock.Builder builder = CodeBlock.builder();
		builder.addStatement("logger.traceEntry()");
		builder.addStatement("$T rb = Response.status(Response.Status.OK)", ResponseBuilder.class);
		ControllerMethodInfo controllerMethod = methodInfo.getControllerMethod();
		if (controllerMethod != null) {
			addStatementsForControllerMethod(builder, controllerMethod);
			if (methodInfo.getSuccessMessage() != null) {
				builder.addStatement("rb.header(\"X-Messages\", \"" + methodInfo.getSuccessMessage() + "\")");
			}
		}
		builder.addStatement("return logger.traceExit(rb.build())");
		return builder.build();
	}
	
	private static void addStatementsForControllerMethod(CodeBlock.Builder builder, ControllerMethodInfo controllerMethod) {
		ClassName genericHashMapType = ClassName.get("java.util", "HashMap");
		ClassName stringType = ClassName.get(String.class);
		ClassName objectType = ClassName.get(Object.class);
		TypeName hashMapType = ParameterizedTypeName.get(genericHashMapType, stringType, objectType);
		
		for(ApiMethodParameterInfo parameter : controllerMethod.getParameters()) {
			if (parameter.isMap()) {
				builder.addStatement("$T " + parameter.getName() + " = new $T()", hashMapType, hashMapType);
				for (Map.Entry<String, String> entry : parameter.getMapValues().entrySet()) {
					builder.addStatement("$L.put($S, $L)", parameter.getName(), entry.getKey(), entry.getValue());
				}
			}
		}
		
		if (controllerMethod.returnsObject()) {
			builder.addStatement("Object object = $T." + ApiMethodGenerator.getControllerMethodSignature(controllerMethod), controllerMethod.getClazz());
			builder.addStatement("rb.entity(object)");
		} else {
			builder.addStatement("$T." + ApiMethodGenerator.getControllerMethodSignature(controllerMethod), controllerMethod.getClazz());
		}
	}
	
	private static String getControllerMethodSignature(ControllerMethodInfo methodInfo) {
		if (methodInfo != null) {
			StringBuilder sb = new StringBuilder();
			sb.append(methodInfo.getMethodName());
			sb.append("(");
			boolean started = false;
			for(ApiMethodParameterInfo parameter: methodInfo.getParameters()){
				if (started) {
					sb.append(", ");
				}
				else{
					started = true;
				}
				if (parameter.hasTransformation()) {
					sb.append(parameter.getTransformationClass().getName());
					sb.append(".");
					sb.append(parameter.getTransformationMethodName());
					sb.append("(");
					if (parameter.isLiteralValue()) {
					    sb.append(parameter.getLiteralValue());
					} else {
    					sb.append(parameter.getName());
    					if (parameter.getSpecificMethodName() != null) {
    						sb.append(".");
    						sb.append(parameter.getSpecificMethodName());
    						sb.append("()");
    					}
					}
					sb.append(")");
				} else if (parameter.isLiteralValue()) {
				    sb.append("\"");
				    sb.append(parameter.getLiteralValue().replace("\"", "\\\""));
				    sb.append("\"");
				} else {
					sb.append(parameter.getName());
                    if (parameter.getSpecificMethodName() != null) {
                        sb.append(".");
                        sb.append(parameter.getSpecificMethodName());
                        sb.append("()");
                    }
				}
			}
			sb.append(")");
			return sb.toString();
		}
		return null;
	}
	
	private static AnnotationSpec generateConsumesAnnotation(String consumesType) {
		return AnnotationSpec.builder(Consumes.class)
			.addMember("value", "$S", consumesType)
			.build();
	}
	
	private static AnnotationSpec generateProducesAnnotation(String producesType) {
		return AnnotationSpec.builder(Produces.class)
			.addMember("value", "$S", producesType)
			.build();
	}

}
