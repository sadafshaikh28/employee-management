package com.example.full_stack.fullstackcrud.service;

import java.util.List;
import com.example.full_stack.fullstackcrud.model.Employee;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    void deleteEmployeeById(Long id);
    Page<Employee> findPaginated(int pageNo, int pageSize);
}
