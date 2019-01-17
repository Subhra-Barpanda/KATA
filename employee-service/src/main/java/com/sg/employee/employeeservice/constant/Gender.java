package com.sg.employee.employeeservice.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {

    M("Male"),
    F("Female");

    private String value;
}
