package com.Spring_Security.Authorization.repository;

import com.Spring_Security.Authorization.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByUsername(String username);
    boolean existsByUsername(String username);
}
