package com.misys.api.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import com.fasterxml.jackson.databind.JsonNode;

@Singleton
public class TestController {

	public static List<String> getStuff(String stuff) {
		List<String> list = new ArrayList<String>();
		list.add("Hello");
		list.add(stuff);
		return list;
	}
	
	public static List<String> getStuff2(Map<String, Object> map) {
		List<String> list = new ArrayList<String>();
		list.add("Hello");
		list.add(map.entrySet().toString());
		return list;
	}
	
	public static String postStuff(JsonNode node) {
		return null;
	}
	
}
