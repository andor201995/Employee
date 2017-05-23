package com.andor.employee.dao;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.andor.employee.exception.DAOException;
import com.andor.employee.exception.ObjectParseException;
import com.andor.employee.util.DBUtil;
import com.andor.employee.util.ObjectParser;
import com.google.common.reflect.TypeToken;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

abstract class DAOAbstract<T> {
	public DBCollection collection;
	private final Logger LOGGER = Logger.getLogger(DAOAbstract.class);
	private Type genericType;
	public ObjectParser objectParser = new ObjectParser();
	
	@SuppressWarnings("serial")
	private final TypeToken<T> typeToken = new TypeToken<T>(getClass()) {
	};

	public DAOAbstract(String collectionName) {
		try {
			this.genericType = typeToken.getType();
			collection = DBUtil.getDBCollection(collectionName);
		} catch (DAOException e) {
			LOGGER.error("Error while connecting to DB", e);
		}
	}

	public T findOne(DBObject query) throws ObjectParseException {
		return objectParser.toObject(objectParser.toJson(collection
				.findOne(query)), genericType);
	}

	public List<T> findAll() throws ObjectParseException {
		List<T> list = new ArrayList<>();
		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			list.add(objectParser.toObject(objectParser.toJson(cursor.next()), genericType));
		}
		return list;
	}

	public List<T> findMany(DBObject query) throws ObjectParseException {
		List<T> list = new ArrayList<>();
		DBCursor cursor = collection.find(query);
		while (cursor.hasNext()) {
			list.add(objectParser.toObject(objectParser.toJson(cursor.next()), genericType));
		}
		return list;
	}

	public Integer delete(DBObject query) {
		return collection.remove(query).getN();
	}

	public void insertOne(T obj) throws ObjectParseException {
		collection
				.insert(objectParser.toObject(objectParser.toJson(obj), BasicDBObject.class));
	}

	public Boolean update(DBObject query, T obj) throws ObjectParseException {
		return collection.update(query, objectParser.toObject(objectParser
				.toJson(obj), BasicDBObject.class)).isUpdateOfExisting();

	}

	public Long count(DBObject query) {
		return collection.count(query);
	}

}
