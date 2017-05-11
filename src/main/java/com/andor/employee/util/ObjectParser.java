package com.andor.employee.util;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.andor.employee.exception.ObjectParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectParser {
	private ObjectMapper objectMapper = new ObjectMapper();
	private static final Logger LOGGER = Logger.getLogger(DBUtil.class);

	public Object toObject(String json,Class<?> genericType) throws ObjectParseException {
		try {
			return objectMapper.readValue(json, genericType);
		} catch (IOException e) {
			LOGGER.error("error while parsing JSON to Object ", e);
			throw new ObjectParseException();
		}
	}

	public String toJson(Object object) throws ObjectParseException {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			LOGGER.error("error while parsing Object to JSON ", e);
			throw new ObjectParseException();

		}
	}

}
