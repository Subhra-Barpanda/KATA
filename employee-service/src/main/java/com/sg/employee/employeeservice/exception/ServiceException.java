package com.sg.employee.employeeservice.exception;

import com.sg.employee.employeeservice.exception.constant.ErrorConstant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceException extends RuntimeException {

    private ErrorConstant errorConstant;

    public ServiceException(ErrorConstant errorConstant) {
        super(errorConstant.getErrorMessage());
        this.errorConstant = errorConstant;
    }
}
