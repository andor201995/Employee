package com.andor.employee.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.andor.employee.bean.Employee;
import com.andor.employee.exception.DAOException;
import com.mongodb.BasicDBObject;

@Repository
public class EmployeeDAOImpl extends DAOAbstract<Employee> implements
		EmployeeDAO {
	private static final String ERROR = "Error in connecting to Employee DB";
	private static final String EMPID = "empId";
	private final Logger LOGGER = Logger.getLogger(EmployeeDAOImpl.class);

	public EmployeeDAOImpl() {
		super("employeeProfile");
	}

	@Override
	public List<Employee> getListOfEmployee() throws DAOException {
		try {
			return findAll();
		} catch (Exception e) {
			LOGGER.error(ERROR, e);
			throw new DAOException();
		}

	}

	@Override
	public Employee getEmployee(Integer empId) throws DAOException {
		try {
			BasicDBObject query = new BasicDBObject();
			query.put(EMPID, empId);
			return findOne(query);
		} catch (Exception e) {
			LOGGER.error(ERROR, e);
			throw new DAOException();
		}

	}

	@Override
	public boolean deleteEmployee(Integer empId) throws DAOException {
		try {
			BasicDBObject query = new BasicDBObject();
			query.put(EMPID, empId);
			return delete(query) == 0 ? false : true;
		} catch (Exception e) {
			LOGGER.error(ERROR, e);
			throw new DAOException();
		}
	}

	@Override
	public boolean addEmployee(Employee newEmp) throws DAOException {
		try {
			BasicDBObject query = new BasicDBObject();
			query.put(EMPID, newEmp.getEmpId());
			if( count(query) == 0 ) {
				insertOne(newEmp);
				return true;
			}
		} catch (Exception e) {
			LOGGER.error(ERROR, e);
			throw new DAOException();
		}
		return false;
	}

	@Override
	public boolean updateEmployee(Employee empUpdate) throws DAOException {
		try {
			BasicDBObject query = new BasicDBObject();
			query.put(EMPID, empUpdate.getEmpId());
			return update(query, empUpdate);
		} catch (Exception e) {
			LOGGER.error(ERROR, e);
			throw new DAOException();
		}
	}

}
