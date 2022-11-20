package com.example.full_stack.fullstackcrud.service;

import java.util.List;
import java.util.Optional;
import com.example.full_stack.fullstackcrud.model.Employee;
import com.example.full_stack.fullstackcrud.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee searchEmployeeById(long searchId) {
        return getEmployeeById(searchId);
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        Employee employee = null;
        if(optionalEmployee.isPresent()) {
            employee = optionalEmployee.get();
        } else {
            throw new RuntimeException("Employee with id:" + id + " not found.");
        }
        return employee;
    }

    @Override
    public void deleteEmployeeById(Long id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.employeeRepository.findAll(pageable);
    }
    
}
