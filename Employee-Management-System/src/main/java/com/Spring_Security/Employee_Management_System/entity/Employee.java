package com.Spring_Security.Employee_Management_System.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    //region fields
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "employee_id")
    private Integer EmployeeId;

    @Column (name = "name", nullable = false)
    private String Name;

    @Column (name = "position", nullable = false)
    private String Position;

    @Column (name = "department", nullable = false)
    private String Department;

    @Column (name = "salary", nullable = false)
    private Double Salary;
    //endregion

}
