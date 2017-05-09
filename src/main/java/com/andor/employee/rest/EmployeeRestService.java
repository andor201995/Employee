package com.andor.employee.rest;

import com.andor.employee.bean.Employee;
import com.andor.employee.bean.RestResponse;
import com.andor.employee.exception.AndorBusinessException;

public interface EmployeeRestService {

	RestResponse<?> getListOfEmployee() throws AndorBusinessException;

	RestResponse<?> getEmployee(String empId) throws AndorBusinessException;

	RestResponse<?> addEmployee(Employee newEmp) throws AndorBusinessException;

	RestResponse<?> deleteEmployee(String empId) throws AndorBusinessException;

	RestResponse<?> updateEmployee(Employee emp) throws AndorBusinessException;

}
