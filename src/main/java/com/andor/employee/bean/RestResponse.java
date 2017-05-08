package com.andor.employee.bean;

import java.util.List;

public class RestResponse {
	private boolean success;
	private List<Employee> EmployeeList;
	private Employee Employee;
	private String errorMessage;

	public List<Employee> getEmployeeList() {
		return EmployeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		EmployeeList = employeeList;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Employee getEmployee() {
		return Employee;
	}

	public void setEmployee(Employee employee) {
		Employee = employee;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
