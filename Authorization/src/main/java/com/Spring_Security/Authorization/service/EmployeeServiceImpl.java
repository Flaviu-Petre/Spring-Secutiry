package com.Spring_Security.Authorization.service;

import com.Spring_Security.Authorization.dao.EmployeeRegistrationDto;
import com.Spring_Security.Authorization.entity.Employee;
import com.Spring_Security.Authorization.exception.InvalidDetailsException;
import com.Spring_Security.Authorization.repository.EmployeeRepository;
import com.Spring_Security.Authorization.service.Interface.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void addEmployee(Employee employee) {
        if (employee.getPassword() != null && !employee.getPassword().startsWith("$2a$")) {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        }
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new InvalidDetailsException("Employee not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Employee updateEmployee(Employee employee) {
        Employee existingEmployee = getEmployeeById(employee.getEmployeeId());

        existingEmployee.setName(employee.getName());
        existingEmployee.setPosition(employee.getPosition());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());

        return employeeRepository.save(existingEmployee);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteEmployee(Integer id) {
        Employee existingEmployee = getEmployeeById(id);
        employeeRepository.delete(existingEmployee);
    }

    @Override
    @PostFilter("hasRole('ADMIN') or (hasRole('USER') and filterObject.role == 'USER')")
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

    private Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private boolean isCurrentUserAdmin() {
        Authentication auth = getCurrentAuthentication();
        return auth.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }
}
