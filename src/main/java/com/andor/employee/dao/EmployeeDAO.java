package com.andor.employee.dao;

import java.util.List;

import com.andor.employee.bean.Employee;
import com.andor.employee.exception.DAOException;

public interface EmployeeDAO {

	boolean addEmployee(Employee emp) throws DAOException;

	List<Employee> getListOfEmployee() throws DAOException;

	boolean deleteEmployee(Integer empId) throws DAOException;

	Employee getEmployeeProfile(Integer empId) throws DAOException;

	boolean updateEmployee(Employee emp) throws DAOException;

}