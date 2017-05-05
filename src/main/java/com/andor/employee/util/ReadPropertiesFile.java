package com.andor.employee.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ReadPropertiesFile {

	private static final Logger LOGGER = Logger
			.getLogger(ReadPropertiesFile.class);

	private ReadPropertiesFile() {
	}

	public static Properties readDBProperties(String path)
			throws FileNotFoundException {
		Properties PropFile = new Properties();
		try {
			InputStream inputStream = new FileInputStream(path);
			PropFile.load(inputStream);
			return PropFile;
		} catch (IOException e) {
			LOGGER.error("Error in Reading Properties File", e);
			throw new FileNotFoundException();
		}
	}

}
