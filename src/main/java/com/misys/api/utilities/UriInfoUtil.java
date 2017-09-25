package com.misys.api.utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Singleton;
import javax.ws.rs.core.MultivaluedMap;

@Singleton
public class UriInfoUtil {

	public static Map<String, Object> normalizeMultivaluedMap(MultivaluedMap<String, String> map) {
		Map<String, Object> normalMap = new HashMap<String, Object>();
		Set<Map.Entry<String, List<String>>> uriSet = map.entrySet();
		for (Map.Entry<String, List<String>> entry: uriSet) {
			String key = entry.getKey();
			List<String> value = entry.getValue();
			if (value != null && value.size() > 0) {
				normalMap.put(key, value.get(0));
			}
		}
		return normalMap;
	}

	public static Map<String, String> normalizeMultivaluedMapCSV(MultivaluedMap<String, String> map) {
	    Map<String, String> normalMap = new HashMap<String, String>();
        Set<Map.Entry<String, List<String>>> uriSet = map.entrySet();
        for (Map.Entry<String, List<String>> entry: uriSet) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            String strValue = value.stream().collect(Collectors.joining(","));
            normalMap.put(key, strValue);
        }
        return normalMap;
	}
}
