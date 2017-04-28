package com.andor.employee.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import com.andor.employee.exception.DAOException;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class DBUtil {
	private final static String PATH="/home/local/PAYODA/anmol.s/workspace/Employee/src/main/resource/DB.properties";
	private final static String DB_ID = readDBProperties("DB_ID");
	private final static String DB_PASSWORD = readDBProperties("DB_PASSWORD");
	private final static String DB_NAME = readDBProperties("DB_NAME");
	private final static String HOST_NAME = readDBProperties("HOST_NAME");
	private final static Integer DBPORT = Integer.parseInt(readDBProperties("DB_PORT"));
	private final static MongoCredential credential = MongoCredential.createScramSha1Credential(DB_ID, DB_NAME, DB_PASSWORD.toCharArray());
	private final static MongoClient client = new MongoClient(new ServerAddress(HOST_NAME,DBPORT), Arrays.asList(credential));

	private DBUtil() {
	}

	private static String readDBProperties(String read) {
		Properties dBPropFile = new Properties();
		try {
			InputStream inputStream = new FileInputStream(PATH);
			dBPropFile.load(inputStream);
			return dBPropFile.getProperty(read);
		} catch (IOException e) {
			System.out.println("FILE Exception" + e);
			return null;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static DBCollection getDBCollection(String collName)
			throws DAOException {
			
		return client.getDB("employee").getCollection(collName);
		
	}

}
