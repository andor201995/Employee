package com.andor.employee.rest;

import java.util.List;

import com.andor.employee.bean.Employee;
import com.andor.employee.exception.AndorBusinessException;

public interface EmployeeRestService {

	List<Employee> getListOfEmployee() throws AndorBusinessException;

	Employee getEmployee(String empId) throws AndorBusinessException;

	String addEmployee(Employee newEmp) throws AndorBusinessException;

	String deleteEmployee(String empId) throws AndorBusinessException;

	String updateEmployee(Employee emp) throws AndorBusinessException;
	
}
