package com.andor.employee.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.andor.employee.bean.Employee;
import com.andor.employee.exception.AndorBusinessException;
import com.andor.employee.exception.ObjectParseException;
import com.andor.employee.service.EmployeeCRUDService;

public class ObjectParserTest {
	@Autowired
	private static EmployeeCRUDService employeeCRUDService;

	public static void main(String[] args) throws ObjectParseException, AndorBusinessException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ObjectParser obj = new ObjectParser();
		Employee emp=ctx.getBean(Employee.class);
		emp.setDesignation("gender");
		emp.setEmpId(12);
		emp.setFirstName("anmol");
		emp.setLastName("andor");
		emp.setGender(null);
		Employee newemp=ctx.getBean(Employee.class);
		String json = obj.toJson(emp);
		newemp = (Employee) obj.toObject(json, Employee.class);

	}

}
