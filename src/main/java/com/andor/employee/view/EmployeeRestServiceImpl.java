package com.andor.employee.view;

import java.util.NoSuchElementException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.andor.employee.bean.Employee;
import com.andor.employee.exception.DAOException;
import com.andor.employee.service.EmployeeCURDService;
import com.andor.employee.service.EmployeeCURDServiceImpl;
import com.google.gson.Gson;

@Path("/service")
public class EmployeeRestServiceImpl implements EmployeeRestService {
	private static EmployeeCURDService employeeCRUDService = new EmployeeCURDServiceImpl();
	private static Gson gson = new Gson();

	@Override
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getWelcomeHtml() 
	{

		return "<html> " + "<title>" + "Hello Jersey" + "</title>"
				+ "<body><h1>"
				+ "Welcome to Jax-rs Demo program for UserService</h1></br><p>"
				+ "GET		List OF Employee	:/employee</br>"
				+ "GET		Single Employee		:/employee/{id}</br> "
				+ "POST		Add Employee		:/addEmployee</br>"
				+ "DELETE	delete Employee		:/deleteEmployee</br>"
				+ "PUT		update Employee		:/updateEmployee</br><p>"
				+ "EMPLOYEE OBJECT:{ _id, firstName, lastName, designation, gender, dateOB{ex:dd/mm/yyyy}}"
				+ "</body>" + "</html> ";
	}

	@Override
	@GET
	@Path("/employee")
	@Produces(MediaType.APPLICATION_JSON)
	public String displayAllEmployee() throws DAOException {
		return gson.toJson(employeeCRUDService.getAllEmployeeDetails());
	}

	@Override
	@GET
	@Path("/employee/{empid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String displayEmployee(@PathParam("empid") String empId) {
		try {
			return gson.toJson(employeeCRUDService.getEmployee(empId));
		} catch (NoSuchElementException | NumberFormatException | DAOException e) {
			return "User doest exist  " + e;
		}
	}

	@Override
	@POST
	@Path("/addEmployee")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String addEmployee(@FormParam("firstName") String firstName,
			@FormParam("lastName") String lastName,
			@FormParam("designation") String designation,
			@FormParam("gender") String gender )
			throws DAOException {
		if(!firstName.isEmpty()&&!lastName.isEmpty()&&!designation.isEmpty()){
		Employee newEmp = new Employee(null, firstName, lastName, designation,
				gender);

		return newEmp.getFirstName() + " " + newEmp.getLastName()
				+ " isNewEmployeeAdded= "
				+ employeeCRUDService.addEmployee(newEmp);
		}
		else
			throw new DAOException();
	}

	@Override
	@DELETE
	@Path("/deleteEmployee")
	@Produces(MediaType.TEXT_HTML)
	public String deleteEmployee(@QueryParam("_id") String empId)
			throws NumberFormatException, DAOException {
		return "isEmployeeDeleted= " + employeeCRUDService.deleteEmployee(empId);
	}

	@Override
	@PUT
	@Path("/updateEmployee")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateEmployee(@FormParam("_id") String _id,
			@FormParam("firstName") String firstName,
			@FormParam("lastName") String lastName,
			@FormParam("designation") String designation,
			@FormParam("gender") String gender)
			throws DAOException {
		Employee newEmp = new Employee(_id, firstName, lastName, designation,
				gender);
		return "Employee Profile updated= "
				+ employeeCRUDService.updateEmployee(newEmp);
	}

}
