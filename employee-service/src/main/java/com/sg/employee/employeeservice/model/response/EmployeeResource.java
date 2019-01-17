package com.sg.employee.employeeservice.model.response;

import com.sg.employee.employeeservice.constant.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmployeeResource {

    @NotNull(message = "can't be null")
    private String firstName;

    private String lastName;

    @NotNull
    private Gender gender;

    @NotNull(message = "can't be null")
    @Past(message = "can't be present or future date")
    private Date dateOfBirth;

    private String departmentName;
}
