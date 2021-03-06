package com.andor.employee.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.andor.employee.bean.Employee;
import com.andor.employee.bean.RestResponse;
import com.andor.employee.dao.EmployeeDAOImpl;
import com.andor.employee.exception.AndorBusinessException;
import com.andor.employee.service.EmployeeCRUDService;
import com.andor.employee.service.EmployeeCRUDServiceImpl;

@Path("/service")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeRestServiceImpl implements EmployeeRestService {
	private EmployeeCRUDService employeeCRUDService = new EmployeeCRUDServiceImpl();
	
	
	private RestResponse response = new RestResponse();
	private final Logger LOGGER = Logger.getLogger(EmployeeDAOImpl.class);
	private static final String ERROR = "Error in rest layer";

	@Override
	@GET
	@Path("/employee")
	public RestResponse getListOfEmployee() throws AndorBusinessException {
		try {
			response.setEmployeeList(employeeCRUDService.getListOfEmployee());
			response.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error(ERROR, e);
			response.setSuccess(false);
			response.setErrorMessage(e.getMessage());
			return response;
		}
		return response;
	}

	@Override
	@GET
	@Path("/employee/{empid}")
	public RestResponse getEmployee(@PathParam("empid") String empId)
			throws AndorBusinessException {
		try {
			response.setEmployee(employeeCRUDService.getEmployee(empId));
			response.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error(ERROR, e);
			response.setSuccess(false);
			response.setErrorMessage(e.getMessage());
			return response;
		}
		return response;
	}

	@Override
	@POST
	@Path("/employee")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestResponse addEmployee(Employee newEmp)
			throws AndorBusinessException {
		try {
			response.setSuccess(employeeCRUDService.addEmployee(newEmp));
		} catch (Exception e) {
			LOGGER.error(ERROR, e);
			response.setSuccess(false);
			response.setErrorMessage(e.getMessage());
			return response;
		}
		return response;

	}

	@Override
	@DELETE
	@Path("/employee/{empId}")
	public RestResponse deleteEmployee(@PathParam("empId") String empId)
			throws AndorBusinessException {
		try {
			response.setSuccess(employeeCRUDService.deleteEmployee(empId));
		} catch (Exception e) {
			LOGGER.error(ERROR, e);
			response.setSuccess(false);
			response.setErrorMessage(e.getMessage());
			return response;
		}
		return response;

	}

	@Override
	@PUT
	@Path("/employee")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestResponse updateEmployee(Employee emp)
			throws AndorBusinessException {
		try {
			response.setSuccess(employeeCRUDService.updateEmployee(emp));
		} catch (Exception e) {
			LOGGER.error(ERROR, e);
			response.setSuccess(false);
			response.setErrorMessage(e.getMessage());
			return response;
		}
		return response;

	}

}
