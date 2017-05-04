package com.andor.employee.rest;

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
import javax.ws.rs.core.Response;

import com.andor.employee.bean.Employee;
import com.andor.employee.exception.DAOException;
import com.andor.employee.service.EmployeeCURDService;
import com.andor.employee.service.EmployeeCURDServiceImpl;

@Path("/service")
public class EmployeeRestServiceImpl implements EmployeeRestService {
	private static EmployeeCURDService employeeCRUDService = new EmployeeCURDServiceImpl();
	private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(EmployeeRestServiceImpl.class);

	@Override
	@GET
	@Path("/employee")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayAllEmployee() throws DAOException {
		return Response.status(200)
				.entity(employeeCRUDService.getAllEmployeeDetails()).build();
	}

	@Override
	@GET
	@Path("/employee/{empid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayEmployee(@PathParam("empid") String empId) {
		try {
			return Response.status(200)
					.entity(employeeCRUDService.getEmployee(empId)).build();
		} catch (NoSuchElementException | NumberFormatException | DAOException e) {
			LOGGER.error("Error in displaying the employee", e);
			return Response.status(404).entity("Employee not found").build();
		}
	}

	@Override
	@POST
	@Path("/addEmployee")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addEmployee(@FormParam("firstName") String firstName,
			@FormParam("lastName") String lastName,
			@FormParam("designation") String designation,
			@FormParam("gender") String gender) throws DAOException {
		if (!firstName.isEmpty() && !lastName.isEmpty()
				&& !designation.isEmpty()) {
			Employee newEmp = new Employee(null, firstName, lastName,
					designation, gender);

			return Response
					.status(200)
					.entity(newEmp.getFirstName() + " " + newEmp.getLastName()
							+ " isNewEmployeeAdded= "
							+ employeeCRUDService.addEmployee(newEmp)).build();
		} else
			throw new DAOException();
	}

	@Override
	@DELETE
	@Path("/deleteEmployee")
	@Produces(MediaType.TEXT_HTML)
	public Response deleteEmployee(@QueryParam("_id") String empId)
			throws NumberFormatException, DAOException {
		return Response
				.status(200)
				.entity("isEmployeeDeleted= "
						+ employeeCRUDService.deleteEmployee(empId)).build();
	}

	@Override
	@PUT
	@Path("/updateEmployee")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateEmployee(@FormParam("_id") String _id,
			@FormParam("firstName") String firstName,
			@FormParam("lastName") String lastName,
			@FormParam("designation") String designation,
			@FormParam("gender") String gender) throws DAOException {
		Employee newEmp = new Employee(_id, firstName, lastName, designation,
				gender);
		return Response
				.status(200)
				.entity("Employee Profile updated= "
						+ employeeCRUDService.updateEmployee(newEmp)).build();
	}

}
