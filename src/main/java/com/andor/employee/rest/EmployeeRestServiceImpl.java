package com.andor.employee.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.andor.employee.bean.Employee;
import com.andor.employee.exception.AndorBusinessException;
import com.andor.employee.service.EmployeeCRUDService;
import com.andor.employee.service.EmployeeCRUDServiceImpl;

@Path("/service")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeRestServiceImpl implements EmployeeRestService {
	private static EmployeeCRUDService employeeCRUDService = new EmployeeCRUDServiceImpl();

	@Override
	@GET
	@Path("/employee")
	public List<Employee> getListOfEmployee() throws AndorBusinessException {
			return employeeCRUDService.getListOfEmployee();
	}

	@Override
	@GET
	@Path("/employee/{empid}")
	public Employee getEmployee(@PathParam("empid") String empId)
			throws AndorBusinessException {
			return employeeCRUDService.getEmployee(empId);
	}

	@Override
	@POST
	@Path("/addEmployee")
	@Consumes(MediaType.APPLICATION_JSON)
	public String addEmployee(Employee newEmp) throws AndorBusinessException {
		return Boolean.toString(employeeCRUDService.addEmployee(newEmp));
	}

	@Override
	@DELETE
	@Path("/deleteEmployee/{empId}")
	public String deleteEmployee(@PathParam("empId") String empId)
			throws AndorBusinessException {
			return Boolean.toString(employeeCRUDService.deleteEmployee(empId));
		
	}

	@Override
	@PUT
	@Path("/updateEmployee")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateEmployee(Employee emp) throws AndorBusinessException {
	return Boolean.toString(employeeCRUDService.addEmployee(emp));

	}

}
