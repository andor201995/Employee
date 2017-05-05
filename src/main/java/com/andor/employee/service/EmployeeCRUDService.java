package com.andor.employee.service;

import java.util.List;

import com.andor.employee.bean.Employee;
import com.andor.employee.exception.AndorBusinessException;

public interface EmployeeCRUDService {

	List<Employee> getListOfEmployee() throws  AndorBusinessException;

	Employee getEmployee(String empId) throws AndorBusinessException;

	boolean addEmployee(Employee newEmp) throws AndorBusinessException;

	boolean deleteEmployee(String empId) throws AndorBusinessException;

	boolean updateEmployee(Employee emp) throws AndorBusinessException;

}