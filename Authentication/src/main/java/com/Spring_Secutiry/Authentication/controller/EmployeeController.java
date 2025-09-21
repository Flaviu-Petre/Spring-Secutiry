package com.Spring_Secutiry.Authentication.controller;


import com.Spring_Secutiry.Authentication.dao.EmployeeRegistrationDto;
import com.Spring_Secutiry.Authentication.entity.Employee;
import com.Spring_Secutiry.Authentication.service.Interface.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<String> registerEmployee(@RequestBody EmployeeRegistrationDto registrationDto) {
        try {
            Employee registeredEmployee = employeeService.registerEmployee(registrationDto);
            return ResponseEntity.status(201).body("Employee registered successfully with ID: " + registeredEmployee.getEmployeeId());
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Registration failed: " + e.getMessage());
        }
    }

    @GetMapping("/allEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        try {
            employeeService.addEmployee(employee);
            return ResponseEntity.ok("Employee added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding employee: " + e.getMessage());
        }
    }

    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
        try {
            employeeService.updateEmployee(employee);
            return ResponseEntity.ok("Employee updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating employee: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Employee deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting employee: " + e.getMessage());
        }
    }
}