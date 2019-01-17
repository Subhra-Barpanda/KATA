package com.sg.employee.employeeservice;

import com.sg.employee.employeeservice.constant.Gender;
import com.sg.employee.employeeservice.model.entity.Department;
import com.sg.employee.employeeservice.model.entity.Employee;
import com.sg.employee.employeeservice.repository.DepartmentRepository;
import com.sg.employee.employeeservice.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

    private Set<Employee> seededEmployeeSet;
	private Department department;

    private void populateSeededData(){

        department = new Department();
        department.setDepartmentName("IT");
        seededEmployeeSet = new HashSet<>();

        Employee employee1 = new Employee();
        employee1.setFirstName("Subhra");
        employee1.setLastName("Barpanda");
        employee1.setGender(Gender.M.name());
        employee1.setDob(new Date());
        employee1.setDepartment(department);
        seededEmployeeSet.add(employee1);

        Employee employee2 = new Employee();
        employee2.setFirstName("Manaswini");
        employee2.setLastName("Panda");
        employee2.setGender(Gender.F.name());
        employee2.setDob(new Date());
        employee2.setDepartment(department);
        seededEmployeeSet.add(employee2);

        department.setEmployees(Arrays.asList(employee1,employee2));

    }

	@Bean
    CommandLineRunner init(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository) {
	    return (args) -> {
            populateSeededData();
            departmentRepository.save(department);
            seededEmployeeSet.forEach(employee -> {
                employeeRepository.save(employee);
            });
            employeeRepository.findAll().forEach(System.out::println);
        };
    }
}

