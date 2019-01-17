package com.sg.employee.employeeservice.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomError {
    private long errorCode;
    private List<String> errorMessages;
}
