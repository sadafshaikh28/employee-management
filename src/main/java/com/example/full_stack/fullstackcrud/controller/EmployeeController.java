package com.example.full_stack.fullstackcrud.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.full_stack.fullstackcrud.model.Employee;
import com.example.full_stack.fullstackcrud.service.EmployeeService;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/", "/home"})
    public String employeesHomePage(Model model) {
        return findPaginated(1, model);
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/", "/home"})
    public String searchEmployeeById(@ModelAttribute("employeeToSearch") Employee employee, Model model) {
        long searchId = employee.getId();
        Employee searchedEmployee = employeeService.searchEmployeeById(searchId);
        if(searchedEmployee == null) return "redirect:/";
        else {
            model.addAttribute("searchedEmployee", searchedEmployee);

            List<Employee> employees = new ArrayList<>();
            employees.add(searchedEmployee);
            model.addAttribute("employeesList", employees);

            return "index";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addEmployee")
    public String addEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "addEmployee";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/updateEmployeee/{id}")
    public String updateEmployee(@PathVariable(value = "id") Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "updateEmployee";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id) {
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

    //PAGINATED

    @RequestMapping(method = RequestMethod.GET, value = "/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {

        Employee employeeToSearch = new Employee();
        int pageSize = 5;

        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize);
        List<Employee> employeesList = page.getContent();
        

        model.addAttribute("employeeToSearch", employeeToSearch);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalEmployees", page.getTotalElements());
        model.addAttribute("employeesList", employeesList);

        return "index";
    }
    
}
