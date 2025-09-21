package com.Spring_Security.JWT_Authentication_and_Authorization.repository;

import com.Spring_Security.JWT_Authentication_and_Authorization.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByUsername(String username);
    boolean existsByUsername(String username);
}
