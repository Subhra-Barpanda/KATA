package com.sg.employee.employeeservice.controller;

import com.sg.employee.employeeservice.model.response.CustomError;
import com.sg.employee.employeeservice.model.response.EmployeeResource;
import com.sg.employee.employeeservice.service.EmployeeService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
@Api(value = "Employee Service API - List available Employees and save new ones", produces = "application/json")
public class EmployeeServiceController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeServiceController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GET
    @Path("/employees")
    @ApiOperation(value = "List All Registered Employees")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully Fetched List of Employees",
            response = EmployeeResource.class),
            @ApiResponse(code = 400, message = "Bad Request", response = CustomError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = CustomError.class) })
    public Response fetchEmployees() {
        Response response;
        final List<EmployeeResource> employeeList = employeeService.fetchEmployeeList();

        response = Response.status(Response.Status.OK)
                           .entity(employeeList)
                           .build();

        return response;
    }

    @POST
    @Path("/employee")
    @ApiOperation(value = "Save a new Employee")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Successfully Created a new Employee",
            response = EmployeeResource.class),
            @ApiResponse(code = 400, message = "Bad Request", response = CustomError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = CustomError.class) })
    public Response saveEmployee(@ApiParam(name = "employee", required = true)
                                 @Valid EmployeeResource employeeResource) {
        Response response;

        final EmployeeResource createdEmployee = employeeService.createEmployee(employeeResource);
        response = Response.status(Response.Status.CREATED)
                           .entity(createdEmployee)
                           .build();

        return response;
    }

}
