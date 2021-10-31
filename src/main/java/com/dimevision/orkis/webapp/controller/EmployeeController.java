package com.dimevision.orkis.webapp.controller;

import com.dimevision.orkis.webapp.entity.Employee;
import com.dimevision.orkis.webapp.service.EmployeeDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 *
 * @author Dimevision
 * @version 0.1
 */

@Controller
public class EmployeeController {

    private final EmployeeDetailsServiceImplementation employeeService;

    @Autowired
    public EmployeeController(EmployeeDetailsServiceImplementation employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    @PreAuthorize("hasAnyAuthority('admin:read', 'superadmin:read', 'client:read')")
    public String showAllEmployees(Model model) {

        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);

        return "employees-list";
    }

    @GetMapping("/employees/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public String showEmployeeProfile(@PathVariable Long id, Model model) {

        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);

        return "employee-profile";
    }

    @DeleteMapping("/delete-employee/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);

        return "redirect:/employees";
    }

    @GetMapping("/create-employee")
    public String createEmployeeForm() {
        return "employee-create";
    }

    @PostMapping("/create-employee")
    public String createEmployee(Employee employee) {
        employeeService.saveEmployee(employee);

        return "redirect:/employees";
    }

    @PostMapping("/update-employee/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String updateEmployee(@PathVariable Long id, Model model) {

        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);

        return "employee-update";
    }
}
