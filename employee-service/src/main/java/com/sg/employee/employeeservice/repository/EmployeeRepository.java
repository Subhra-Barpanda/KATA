package com.sg.employee.employeeservice.repository;

import com.sg.employee.employeeservice.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findByFirstNameAndDob(String firstName,
                                   Date dob);
}
