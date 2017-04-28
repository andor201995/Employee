package com.payoda.employee.service;

import java.util.List;

import com.payoda.employee.bean.Employee;
import com.payoda.employee.exception.DAOException;

public interface EmployeeCRUDService {

	List<Employee> getAllEmployeeDetails() throws DAOException;

	Employee getEmployee(String empId) throws NumberFormatException,
			DAOException;

	boolean addEmployee(Employee newEmp) throws DAOException;

	boolean deleteEmployee(String empId) throws NumberFormatException,
			DAOException;

	boolean updateEmployee(Employee emp) throws DAOException;

}