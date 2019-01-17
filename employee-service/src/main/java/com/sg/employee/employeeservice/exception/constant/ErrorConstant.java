package com.sg.employee.employeeservice.exception.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorConstant {

    VALIDATION_EXCEPTION(1001,"Validation Exception"),
    UNPARSABLE_DATE(1005,"Supported Date Format is yyyy-MM-dd"),
    NO_EMPLOYEES(1002,"No Employee data to display"),
    NO_SUCH_DEPARTMENT(1003,"No such department exists to link the employee"),
    EMPLOYEE_ALREADY_EXIST(1004,"Employee with the same name and DOB already exist");

    private long errorCode;
    private String errorMessage;
}
