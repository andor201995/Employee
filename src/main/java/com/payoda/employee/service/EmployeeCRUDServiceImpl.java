package com.payoda.employee.service;

import java.util.List;

import com.payoda.employee.bean.Employee;
import com.payoda.employee.dao.EmployeeDAO;
import com.payoda.employee.dao.EmployeeDAOImpl;
import com.payoda.employee.exception.DAOException;

public class EmployeeCRUDServiceImpl implements EmployeeCRUDService {
	private static EmployeeDAO empDAO=new EmployeeDAOImpl();

	 @Override
	public List<Employee> getAllEmployeeDetails() throws DAOException{
		 return empDAO.getListOfEmployee();
	 }
	 
	 @Override
	public Employee getEmployee(String empId) throws NumberFormatException, DAOException {
		 return empDAO.getEmployeeProfile(Integer.parseInt(empId));
		 
	 }
	 
	 @Override
	public boolean addEmployee(Employee newEmp) throws DAOException{
		 return empDAO.addEmployee(newEmp);
	 
	 }
	 
	 @Override
	public boolean deleteEmployee(String empId) throws NumberFormatException, DAOException {
		 return empDAO.deleteEmployee(Integer.parseInt(empId));
		
	}
	 
	 @Override
	public boolean updateEmployee(Employee emp) throws DAOException{
		 return empDAO.updateEmployee(emp);
	 }
	 

}
