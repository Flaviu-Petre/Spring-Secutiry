package com.Spring_Security.JWT_Authentication_and_Authorization.service.Interface;

import com.Spring_Security.JWT_Authentication_and_Authorization.dao.EmployeeRegistrationDto;
import com.Spring_Security.JWT_Authentication_and_Authorization.entity.Employee;

import java.util.List;

public interface EmployeeService {
    void addEmployee(Employee employee);
    Employee getEmployeeById(Integer id);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(Integer id);
    List<Employee> getAllEmployees();

    // Registration method
    Employee registerEmployee(EmployeeRegistrationDto registrationDto);
}
