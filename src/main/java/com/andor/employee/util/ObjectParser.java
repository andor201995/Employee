package com.andor.employee.util;

import java.io.IOException;
import java.lang.reflect.Type;

import org.apache.log4j.Logger;

import com.andor.employee.exception.ObjectParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class ObjectParser {
	private ObjectMapper objectMapper = new ObjectMapper();
	private static final Logger LOGGER = Logger.getLogger(DBUtil.class);

	public <T> T toObject(String json, Type type) throws ObjectParseException {
		try {
			final JavaType javaType = TypeFactory.defaultInstance()
					.constructType(type);
			return objectMapper.readValue(json, javaType);
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
