package com.andor.employee.view;

import com.andor.employee.exception.DAOException;

public interface EmployeeRestService {

	String getWelcomeHtml();

	String displayAllEmployee() throws DAOException;

	String displayEmployee(String empId);

	String addEmployee(String firstName, String lastName, String designation,
			String gender) throws DAOException;

	String deleteEmployee(String empId) throws NumberFormatException,
			DAOException;

	String updateEmployee(String _id, String firstName, String lastName,
			String designation, String gender) throws DAOException;

}