package com.Spring_Security.JWT_Authentication_and_Authorization.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRegistrationDto {
    private String name;
    private String position;
    private String department;
    private Double salary;
    private String username;
    private String password;
    private String role = "USER";
}