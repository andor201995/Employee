package com.andor.employee.util;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.andor.employee.exception.DAOException;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class DBUtil {
	private static MongoClient client;
	private static final Logger LOGGER=Logger.getLogger(DBUtil.class);

	private DBUtil() {
		
	}

	@SuppressWarnings("deprecation")
	public static DBCollection getDBCollection(String collName)
			throws DAOException {
		if(client==null||!client.getMongoClientOptions().isSocketKeepAlive())
				openDBConnection();
		return client.getDB("employee").getCollection(collName);
		

	}

	private static void openDBConnection() throws DAOException {
	 	try{
	 	Properties dbProperties= ReadPropertiesFile.readDBProperties(System.getProperty("db_properties_file_path"));
	 	final String DB_HOST = dbProperties.getProperty("HOST_NAME","localhost");
		final int DB_PORT = Integer.parseInt(dbProperties.getProperty("DB_PORT","5000"));
		final String DB_ID = dbProperties.getProperty("DB_ID");
		final String DB_PASSWORD = dbProperties.getProperty("DB_PASSWORD");
		final String DB_NAME = dbProperties.getProperty("DB_NAME");
		final MongoCredential credential = MongoCredential.createScramSha1Credential(DB_ID, DB_NAME,DB_PASSWORD.toCharArray());
		client = new MongoClient(new ServerAddress(DB_HOST, DB_PORT),Arrays.asList(credential));
	 	}catch(FileNotFoundException | NumberFormatException | NullPointerException e){
	 		LOGGER.error("error is reading DBproperties file",e);
	 		throw new DAOException();
	 	}
	}

	public static void closeDBConnection(){
		if(client!=null||client.getMongoClientOptions().isSocketKeepAlive())
		client.close();
	}

}
