package com.Spring_Security.Authorization.service.Interface;

import com.Spring_Security.Authorization.dao.EmployeeRegistrationDto;
import com.Spring_Security.Authorization.entity.Employee;

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
