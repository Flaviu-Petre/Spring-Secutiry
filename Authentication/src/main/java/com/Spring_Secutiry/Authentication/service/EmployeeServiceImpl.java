package com.Spring_Secutiry.Authentication.service;

import com.Spring_Secutiry.Authentication.dao.EmployeeRegistrationDto;
import com.Spring_Secutiry.Authentication.entity.Employee;
import com.Spring_Secutiry.Authentication.exception.InvalidDetailsException;
import com.Spring_Secutiry.Authentication.repository.EmployeeRepository;
import com.Spring_Secutiry.Authentication.service.Interface.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new InvalidDetailsException("Employee not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee existingEmployee = getEmployeeById(employee.getEmployeeId());

        existingEmployee.setName(employee.getName());
        existingEmployee.setPosition(employee.getPosition());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        Employee existingEmployee = getEmployeeById(id);
        employeeRepository.delete(existingEmployee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee registerEmployee(EmployeeRegistrationDto registrationDto) {
        if (employeeRepository.existsByUsername(registrationDto.getUsername())) {
            throw new InvalidDetailsException("Username already exists: " + registrationDto.getUsername());
        }

        Employee employee = new Employee();
        employee.setName(registrationDto.getName());
        employee.setPosition(registrationDto.getPosition());
        employee.setDepartment(registrationDto.getDepartment());
        employee.setSalary(registrationDto.getSalary());
        employee.setUsername(registrationDto.getUsername());
        employee.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        employee.setRole(registrationDto.getRole());

        return employeeRepository.save(employee);
    }
}
