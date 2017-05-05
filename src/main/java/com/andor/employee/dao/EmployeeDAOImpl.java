package com.andor.employee.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.andor.employee.bean.Employee;
import com.andor.employee.exception.DAOException;
import com.andor.employee.util.DBUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class EmployeeDAOImpl implements EmployeeDAO {
	private static final String EMPLOYEE_PROFILE = "employeeProfile";
	private static ObjectMapper objectMapper = new ObjectMapper();
	private static final String ERROR="Error in connecting to Employee DB";
	private static DBCollection empCollection;
	private static final Logger LOGGER = Logger.getLogger(EmployeeDAOImpl.class);

	public EmployeeDAOImpl() {
		try {
			empCollection = DBUtil.getDBCollection(EMPLOYEE_PROFILE);
		} catch (DAOException e) {
			LOGGER.error(ERROR, e);
		}
	}
	
	@Override
	public boolean addEmployee(Employee newEmp) throws DAOException {
		try{
		DBObject empDoc = (DBObject) JSON.parse( objectMapper.writeValueAsString(newEmp)); 
		empDoc.removeField("_id");
		empDoc.put("_id", getNextSequence("userid"));
		empCollection.insert(empDoc);
		return true;
		}catch(Exception e){
			LOGGER.error(ERROR, e);
			throw new DAOException();
		}
	}

	@Override
	public List<Employee> getListOfEmployee() throws DAOException {
		try{
		List<Employee> empList = new ArrayList<>();
		DBCursor cursor = empCollection.find();
		while (cursor.hasNext()) {
			empList.add(objectMapper.readValue(objectMapper.writeValueAsString(cursor.next()),
					Employee.class));
		}
		return empList;
		}catch(Exception e){
			LOGGER.error(ERROR, e);
			throw new DAOException();
		}

	}

	@Override
	public boolean deleteEmployee(Integer empId) throws DAOException {
		try{
		BasicDBObject query = new BasicDBObject();
		query.put("_id", empId);
		empCollection.remove(query);
		return true;
		}catch(Exception e){
			LOGGER.error(ERROR, e);
			throw new DAOException();
		}
	}

	@Override
	public boolean updateEmployee(Employee empUpdate) throws DAOException {
		try{
		BasicDBObject query = new BasicDBObject();
		query.put("_id", empUpdate.get_Id());
		return empCollection.update(query,
				(DBObject) JSON.parse( objectMapper.writeValueAsString(empUpdate)))
				.isUpdateOfExisting();
		}catch(Exception e){
			LOGGER.error(ERROR, e);
			throw new DAOException();
		}
	}

	@Override
	public Employee getEmployee(Integer empId) throws DAOException {
		try{
		BasicDBObject query = new BasicDBObject();
		query.put("_id", empId);
		DBCursor cursor = empCollection.find(query);
		return objectMapper.readValue(objectMapper.writeValueAsString(cursor.next()), Employee.class);
		}catch(Exception e){
			LOGGER.error(ERROR, e);
			throw new DAOException();
		}

	}

	public static Object getNextSequence(String name) throws DAOException {
		try{
		DBCollection counters = DBUtil.getDBCollection("counters");
		BasicDBObject searchQuery = new BasicDBObject("_id", name);
		BasicDBObject increase = new BasicDBObject("seq", 1);
		BasicDBObject updateQuery = new BasicDBObject("$inc", increase);
		DBObject result = counters.findAndModify(searchQuery, null, null,
				false, updateQuery, true, false);
		return result.get("seq");
		}catch(Exception e){
			LOGGER.error(ERROR, e);
			throw new DAOException();
		}
	}

}
