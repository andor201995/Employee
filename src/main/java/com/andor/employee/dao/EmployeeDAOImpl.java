package com.andor.employee.dao;

import java.util.ArrayList;
import java.util.List;

import com.andor.employee.bean.Employee;
import com.andor.employee.exception.DAOException;
import com.andor.employee.util.DBUtil;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static final String EMPLOYEE_PROFILE = "employeeProfile";
	private static final Gson gson = new Gson();

	@Override
	public boolean addEmployee(Employee newEmp) throws DAOException {
		DBObject empDoc = (DBObject) JSON.parse(gson.toJson(newEmp));
		DBCollection empCollection = DBUtil.getDBCollection(EMPLOYEE_PROFILE);
		empDoc.put("_id", getNextSequence("userid"));
		empCollection.insert(empDoc);
		return true;

	}

	@Override
	public List<Employee> getListOfEmployee() throws DAOException {
		List<Employee> empList = new ArrayList<>();
		DBCollection empCollection = DBUtil.getDBCollection(EMPLOYEE_PROFILE);
		DBCursor cursor = empCollection.find();
		while (cursor.hasNext()) {
			empList.add(gson.fromJson(gson.toJson(cursor.next()),
					Employee.class));
		}

		return empList;

	}

	@Override
	public boolean deleteEmployee(Integer empId) throws DAOException {
		DBCollection empCollection = DBUtil.getDBCollection(EMPLOYEE_PROFILE);
		BasicDBObject query = new BasicDBObject();
		query.put("_id", empId);
		empCollection.remove(query);
		return true;

	}

	@Override
	public boolean updateEmployee(Employee empUpdate) throws DAOException {
		DBCollection empCollection = DBUtil.getDBCollection(EMPLOYEE_PROFILE);
		BasicDBObject query = new BasicDBObject();
		query.put("_id", empUpdate.get_Id());
		return empCollection.update(query,
				(DBObject) JSON.parse(gson.toJson(empUpdate)))
				.isUpdateOfExisting();

	}

	@Override
	public Employee getEmployeeProfile(Integer empId) throws DAOException {
		DBCollection empCollection = DBUtil.getDBCollection(EMPLOYEE_PROFILE);
		BasicDBObject query = new BasicDBObject();
		query.put("_id", empId);
		DBCursor cursor = empCollection.find(query);
		return gson.fromJson(gson.toJson(cursor.next()), Employee.class);

	}

	public static Object getNextSequence(String name) throws DAOException {
		DBCollection counters = DBUtil.getDBCollection("counters");
		BasicDBObject searchQuery = new BasicDBObject("_id", name);
		BasicDBObject increase = new BasicDBObject("seq", 1);
		BasicDBObject updateQuery = new BasicDBObject("$inc", increase);
		DBObject result = counters.findAndModify(searchQuery, null, null,
				false, updateQuery, true, false);

		return result.get("seq");
	}

}
