package com.andor.employee.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andor.employee.bean.Employee;
import com.andor.employee.dao.EmployeeDAO;
import com.andor.employee.exception.AndorBusinessException;
import com.andor.employee.exception.DAOException;
@Service
public class EmployeeCRUDServiceImpl implements EmployeeCRUDService {
	@Autowired
	private EmployeeDAO empDAO;
	
	private final Logger LOGGER = Logger.getLogger(EmployeeCRUDServiceImpl.class);
	private static final String ERROR = "Error in EmployeeCRUDServiceImpl";

	@Override
	public List<Employee> getListOfEmployee() throws AndorBusinessException {
		try {
			return empDAO.getListOfEmployee();
		} catch (DAOException e) {
			LOGGER.error(ERROR, e);
			throw new AndorBusinessException();
		}
	}

	@Override
	public Employee getEmployee(String empId) throws AndorBusinessException {
		try {
			return empDAO.getEmployee(Integer.parseInt(empId));
		} catch (DAOException e) {
			LOGGER.error(ERROR, e);
			throw new AndorBusinessException();
		}

	}

	@Override
	public boolean addEmployee(Employee newEmp) throws AndorBusinessException {
		try {
			return empDAO.addEmployee(newEmp);
		} catch (DAOException e) {
			LOGGER.error(ERROR, e);
			throw new AndorBusinessException();
		}

	}

	@Override
	public boolean deleteEmployee(String empId) throws AndorBusinessException {
		try {
			return empDAO.deleteEmployee(Integer.parseInt(empId));
		} catch (DAOException e) {
			LOGGER.error(ERROR, e);
			throw new AndorBusinessException();
		}

	}

	@Override
	public boolean updateEmployee(Employee emp) throws AndorBusinessException {
		try {
			return empDAO.updateEmployee(emp);
		} catch (DAOException e) {
			LOGGER.error(ERROR, e);
			throw new AndorBusinessException();

		}
	}

}
