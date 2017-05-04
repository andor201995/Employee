package com.andor.employee.rest;

import javax.ws.rs.core.Response;

import com.andor.employee.exception.DAOException;

public interface EmployeeRestService {

	Response displayAllEmployee() throws DAOException;

	Response displayEmployee(String empId);

	Response addEmployee(String firstName, String lastName, String designation,
			String gender) throws DAOException;

	Response deleteEmployee(String empId) throws NumberFormatException,
			DAOException;

	Response updateEmployee(String _id, String firstName, String lastName,
			String designation, String gender) throws DAOException;
}
