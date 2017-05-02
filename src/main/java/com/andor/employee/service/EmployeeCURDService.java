package com.andor.employee.service;

import java.util.List;

import com.andor.employee.bean.Employee;
import com.andor.employee.exception.DAOException;

public interface EmployeeCURDService {

	List<Employee> getAllEmployeeDetails() throws DAOException;

	Employee getEmployee(String empId) throws NumberFormatException,
			DAOException;

	boolean addEmployee(Employee newEmp) throws DAOException;

	boolean deleteEmployee(String empId) throws NumberFormatException,
			DAOException;

	boolean updateEmployee(Employee emp) throws DAOException;

}