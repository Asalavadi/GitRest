package com.git.client.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;

public class ResponseUtil {
	static public Map convertToMap(ClientResponse response) {
		String stringResp = response.getEntity(String.class);
		ObjectMapper mapper = new ObjectMapper();

		Map<String, Object> mapResp = new HashMap<String, Object>();

		// convert JSON string to Map
		try {
			mapResp = mapper.readValue(stringResp, new TypeReference<Map<String, Object>>(){});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mapResp;
	}
	
	static public List convertToList(ClientResponse response) {
		String stringResp = response.getEntity(String.class);
		ObjectMapper mapper = new ObjectMapper();

		List<Object> mapResp = new ArrayList<Object>();

		// convert JSON string to List
		try {
			mapResp = mapper.readValue(stringResp, new TypeReference<List<Object>>(){});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mapResp;
	}
}
