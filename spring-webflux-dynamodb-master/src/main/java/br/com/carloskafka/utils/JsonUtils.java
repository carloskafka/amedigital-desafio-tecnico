package br.com.carloskafka.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.carloskafka.dto.TweetDTO;

public class JsonUtils {

	private static final ObjectMapper mapper = new ObjectMapper();

	public static String toJson(Object object) {
		String json = "";

		try {
			json = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			json = "";
			e.printStackTrace();
		}

		return json;
	}

	public static Object fromJson(String json) {
		Object object = null;

		try {
			object = mapper.readValue(json, TweetDTO.class);
		} catch (Exception e) {
			object = null;
		}

		return object;
	}
}
