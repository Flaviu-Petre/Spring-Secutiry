package com.Spring_Secutiry.Authentication.service.Interface;

import com.Spring_Secutiry.Authentication.dao.EmployeeRegistrationDto;
import com.Spring_Secutiry.Authentication.entity.Employee;

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
