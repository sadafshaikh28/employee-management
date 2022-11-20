package com.example.full_stack.fullstackcrud.repository;

import com.example.full_stack.fullstackcrud.model.Employee;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findById(Long id);
    
}
