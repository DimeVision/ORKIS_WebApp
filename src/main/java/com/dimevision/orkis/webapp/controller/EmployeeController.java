package com.dimevision.orkis.webapp.controller;

import com.dimevision.orkis.webapp.entity.Employee;
import com.dimevision.orkis.webapp.entity.management.Role;
import com.dimevision.orkis.webapp.repository.OrganizationRepository;
import com.dimevision.orkis.webapp.service.EmployeeDetailsServiceImplementation;
import com.dimevision.orkis.webapp.service.cloud.AmazonS3BucketStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Dimevision
 * @version 0.1
 */

@Controller
public class EmployeeController {

    private final EmployeeDetailsServiceImplementation employeeService;
    private final OrganizationRepository organizationRepository;

    private final AmazonS3BucketStorageService amazonClient;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeController(EmployeeDetailsServiceImplementation employeeService, OrganizationRepository organizationRepository, AmazonS3BucketStorageService amazonClient, PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.organizationRepository = organizationRepository;
        this.amazonClient = amazonClient;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/employees")
    @PreAuthorize("hasAnyAuthority('admin:read', 'superadmin:read', 'client:read')")
    public String showAllEmployees(Model model,
                                   @PathVariable(required = false) @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                   @RequestParam(value = "size", required = false, defaultValue = "25") int size
    ) {

        model.addAttribute("employees", employeeService.getEmployeePage(pageNumber, size));

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
    public String createEmployeeForm(@ModelAttribute("employee") Employee employee, Model model) {

        model.addAttribute("organizations", organizationRepository.findAll());
        model.addAttribute("roles", Role.values());
        return "add-employee";
    }

    @PostMapping("/create-employee")
    public RedirectView createEmployee(@ModelAttribute("employee") Employee employee,
                                       @RequestParam(value = "photo", required = false) MultipartFile multipartFile) {

        amazonClient.uploadFile(multipartFile.getOriginalFilename(), multipartFile);
        employee.setPhotoLink("https://orkis-bucket1.s3.amazonaws.com/" + multipartFile.getOriginalFilename());

        System.out.println(employee.getPassword());

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeService.savedEmployee(employee);

        return new RedirectView("/employees", true);
    }

    @GetMapping("/update-employee/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public String showUpdateEmployeeForm(@PathVariable Long id, Model model) {

        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("organizations", organizationRepository.findAll());
        model.addAttribute("roles", Role.values());

        return "employee-update";
    }

    @PostMapping("/update-employee/{id}")
    public RedirectView updateEmployee(@PathVariable Long id,
                                       @ModelAttribute Employee employee,
                                       @RequestParam(value = "photo") MultipartFile multipartFile) {

        amazonClient.uploadFile(multipartFile.getOriginalFilename(), multipartFile);
        
        employee = employeeService.getEmployeeById(id);
        employee.setPhotoLink("https://orkis-bucket1.s3.amazonaws.com/" + multipartFile.getOriginalFilename());

        employee.setOrganization(employee.getOrganization());
        employee.setPassword(employee.getPassword());

        employeeService.saveEmployee(employee);

        return new RedirectView("/employees", true);
    }
}
