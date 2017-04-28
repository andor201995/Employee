package com.payoda.employee.view;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.payoda.employee.bean.Employee;
import com.payoda.employee.exception.DAOException;
import com.payoda.employee.service.EmployeeCRUDService;
import com.payoda.employee.service.EmployeeCRUDServiceImpl;

@Path("/service")
public class EmployeeRestService {
	private static EmployeeCRUDService employeeCRUDService = new EmployeeCRUDServiceImpl();
	private static Gson gson=new Gson();
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getWelcomeHtml()

	{
		return "<html> " + "<title>" + "Hello Jersey" + "</title>"
				+ "<body><h1>"
				+ "Welcome to Jax-rs Demo program for UserService"
				+ "</body></h1>" + "</html> ";
	}

	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String displayAllEmployee() throws DAOException {
		return gson.toJson(employeeCRUDService.getAllEmployeeDetails());
	}
	
	@GET
	@Path("/users/{empid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String displayEmployee(@PathParam("empid") String empId) throws DAOException {
		return gson.toJson(employeeCRUDService.getEmployee(empId));
//		return employeeCRUDService.getEmployee(empId);
	}
	
	

}
