package com.sg.employee.employeeservice.service;

import com.sg.employee.employeeservice.constant.Gender;
import com.sg.employee.employeeservice.exception.ServiceException;
import com.sg.employee.employeeservice.model.response.EmployeeResource;
import com.sg.employee.employeeservice.model.entity.Department;
import com.sg.employee.employeeservice.model.entity.Employee;
import com.sg.employee.employeeservice.repository.DepartmentRepository;
import com.sg.employee.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.sg.employee.employeeservice.exception.constant.ErrorConstant.EMPLOYEE_ALREADY_EXIST;
import static com.sg.employee.employeeservice.exception.constant.ErrorConstant.NO_EMPLOYEES;
import static com.sg.employee.employeeservice.exception.constant.ErrorConstant.NO_SUCH_DEPARTMENT;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public EmployeeResource createEmployee(EmployeeResource employeeResource) {

        final Employee e = mapToEmployee(employeeResource);
        final Employee savedEmployee = employeeRepository.save(e);
        return mapFromEmployee(savedEmployee);
    }

    public List<EmployeeResource> fetchEmployeeList() {
        final List<Employee> employeeList = employeeRepository.findAll();
        if(employeeList == null || employeeList.size() == 0) {
            throw new ServiceException(NO_EMPLOYEES);
        }
        List<EmployeeResource> employeeResourceList = new ArrayList<>();
        employeeList.forEach(employee -> employeeResourceList.add(mapFromEmployee(employee)));
        return employeeResourceList;
    }

    private EmployeeResource mapFromEmployee(Employee employee) {
        EmployeeResource employeeResource = new EmployeeResource();
        employeeResource.setFirstName(employee.getFirstName());
        employeeResource.setLastName(employee.getLastName());
        employeeResource.setDateOfBirth(employee.getDob());
        employeeResource.setGender(Gender.valueOf(employee.getGender()));
        final Department department = employee.getDepartment();
        String departmentName = null;
        if(department != null) {
            departmentName = department.getDepartmentName();
        }
        employeeResource.setDepartmentName(departmentName);
        return employeeResource;
    }

    private Employee mapToEmployee(EmployeeResource employeeResource) {
        Department department = null;
        if(employeeResource.getDepartmentName() != null) {
            department = departmentRepository.findByDepartmentName(employeeResource.getDepartmentName().toUpperCase());
            if(department == null) {
                throw new ServiceException(NO_SUCH_DEPARTMENT);
            }
        }
        final Employee dupEmployee = employeeRepository.findByFirstNameAndDob(employeeResource.getFirstName(),
                                                                              employeeResource.getDateOfBirth());

        if(dupEmployee != null) {
            throw new ServiceException(EMPLOYEE_ALREADY_EXIST);
        }
        Employee employee = new Employee();
        employee.setFirstName(employeeResource.getFirstName());
        employee.setLastName(employeeResource.getLastName());
        employee.setDob(employeeResource.getDateOfBirth());
        employee.setGender(employeeResource.getGender().name());
        employee.setDepartment(department);
        return employee;
    }
}
