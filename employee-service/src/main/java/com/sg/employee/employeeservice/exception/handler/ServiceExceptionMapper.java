package com.sg.employee.employeeservice.exception.handler;


import com.sg.employee.employeeservice.exception.ServiceException;
import com.sg.employee.employeeservice.model.response.CustomError;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Arrays;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {
    @Override
    public Response toResponse(ServiceException e) {
        CustomError error = new CustomError();
        error.setErrorCode(e.getErrorConstant().getErrorCode());
        error.setErrorMessages(Arrays.asList(e.getErrorConstant().getErrorMessage()));

        Response response = Response.serverError()
                                    .entity(error)
                                    .build();
        return response;
    }
}
