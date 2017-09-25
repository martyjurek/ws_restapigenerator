package com.misys.api.generator;

import java.io.File;

import javax.inject.Singleton;
import javax.lang.model.element.Modifier;
import javax.ws.rs.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

@Singleton
public class ApiClassGenerator {

	public static void generateClass(ApiClassInfo classInfo) {
		
		try {
			
			TypeSpec genClass = TypeSpec.classBuilder(classInfo.getClassName())
				.addAnnotation(Singleton.class)
				.addAnnotation(AnnotationSpec.builder(Path.class).addMember("value", "$S", classInfo.getUrlPath()).build())
			    .addModifiers(Modifier.PUBLIC)
			    .addField(FieldSpec.builder(Logger.class, "logger", Modifier.PRIVATE, Modifier.STATIC)
					.initializer("$T.getLogger(" + classInfo.getClassName() + ".class.getName())", LogManager.class)
					.build())
			    .addMethods(ApiMethodGenerator.generateMethods(classInfo.getApiMethods()))
			    .build();
			
			
			
			JavaFile javaFile = JavaFile.builder(classInfo.getPackageName(), genClass)
				.indent("\t")
				.build();
			
			javaFile.writeTo(System.out);
			//javaFile.writeTo(new File("D:/workspace - mars R/CodeGeneratorTest/src/main/java/com/misys/generator/classes"));
			
			File currentDir = new File(".");
			String basePath = currentDir.getCanonicalPath();
			String fullPath = basePath + "/src/main/java/";
			javaFile.writeTo(new File(fullPath));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
