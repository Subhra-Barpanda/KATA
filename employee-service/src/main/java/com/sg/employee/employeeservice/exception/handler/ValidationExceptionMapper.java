package com.sg.employee.employeeservice.exception.handler;

import com.sg.employee.employeeservice.model.response.CustomError;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

import static com.sg.employee.employeeservice.exception.constant.ErrorConstant.VALIDATION_EXCEPTION;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException e) {
        List<String> errorMessageList = new ArrayList<>();

        for (ConstraintViolation<?> constraintViolation : ((ConstraintViolationException) e).getConstraintViolations()) {
            String errorMessage = constraintViolation.getPropertyPath().toString().substring(constraintViolation.getPropertyPath().toString().lastIndexOf(".")+1) + " " + constraintViolation.getMessage();
            errorMessageList.add(errorMessage);
        }

        CustomError errorInstance = new CustomError(VALIDATION_EXCEPTION.getErrorCode(),
                                                    errorMessageList);

        return Response
                .status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON)
                .entity(errorInstance)
                .build();
    }
}
