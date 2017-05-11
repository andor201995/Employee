package com.andor.employee.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.andor.employee.exception.DAOException;
import com.andor.employee.exception.ObjectParseException;
import com.andor.employee.util.DBUtil;
import com.andor.employee.util.ObjectParser;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

abstract class DAOAbstract<T> {
	public DBCollection collection;
	private final Logger LOGGER = Logger.getLogger(DAOAbstract.class);
    private Class<T> genericType;
	public ObjectParser objectParser = new ObjectParser();

	public DAOAbstract(String collectionName,Class<T> genericType ) {
		try {
			this.genericType=genericType;
			collection = DBUtil.getDBCollection(collectionName);
		} catch (DAOException e) {
			LOGGER.error("Error while connecting to DB", e);
		}
	}
	@SuppressWarnings("unchecked")
	public T findOne(DBObject query) throws ObjectParseException{
		return (T) objectParser.toObject(objectParser.toJson(collection.findOne(query)),genericType);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> find() throws ObjectParseException{
		List<T> list = new ArrayList<>();
		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			list.add((T) objectParser.toObject(objectParser.toJson(cursor.next()),genericType));
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findMany(DBObject query) throws ObjectParseException{
		List<T> list = new ArrayList<>();
		DBCursor cursor = collection.find(query);
		while (cursor.hasNext()) {
			list.add((T) objectParser.toObject(objectParser.toJson(cursor.next()),genericType));
		}
		return list;
	}
	
	public Integer delete(DBObject query) {
		return collection.remove(query).getN();
	}
	

}
