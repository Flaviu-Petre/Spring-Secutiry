package com.Spring_Security.Employee_Management_System.service;

import com.Spring_Security.Employee_Management_System.entity.Employee;

import java.util.List;

public interface EmployeeService {
    void addEmployee(Employee employee);
    Employee getEmployeeById(Integer id);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(Integer id);
    List<Employee> getAllEmployees();
}
