package com.sg.employee.employeeservice.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="T_EMPLOYEE")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EMPLOYEE_ID")
    private long id;

    @Column(name="EMPLOYEE_FIRST_NAME")
    private String firstName;

    @Column(name="EMPLOYEE_LAST_NAME")
    private String lastName;

    @Column(name="EMPLOYEE_GENDER")
    private String gender;

    @Column(name="EMPLOYEE_DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID",referencedColumnName = "DEPARTMENT_ID")
    private Department department;

}
