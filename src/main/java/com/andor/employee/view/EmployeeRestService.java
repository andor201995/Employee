package com.andor.employee.view;

import java.util.NoSuchElementException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.andor.employee.exception.DAOException;
import com.andor.employee.exception.WebResourceNotFoundException;
import com.andor.employee.service.EmployeeCRUDService;
import com.andor.employee.service.EmployeeCRUDServiceImpl;
import com.google.gson.Gson;

@Path("/service")
public class EmployeeRestService {
	private static EmployeeCRUDService employeeCRUDService = new EmployeeCRUDServiceImpl();
	private static Gson gson=new Gson();
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getWelcomeHtml() throws WebResourceNotFoundException

	{
		
		return "<html> " + "<title>" + "Hello Jersey" + "</title>"
				+ "<body><h1>"
				+ "Welcome to Jax-rs Demo program for UserService"
				+ "</body></h1>" + "</html> ";
	}

	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String displayAllEmployee() throws WebResourceNotFoundException {
		try {
			return gson.toJson(employeeCRUDService.getAllEmployeeDetails());
		} catch (DAOException e) {
			throw new WebResourceNotFoundException();
		}
	}
	
	@GET
	@Path("/users/{empid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String displayEmployee(@PathParam("empid") String empId)  {
		try {
				return gson.toJson(employeeCRUDService.getEmployee(empId));
			} catch (NoSuchElementException |NumberFormatException | DAOException e) {
				return "User doest exist  "+e;
		}
	}
	
	

}
