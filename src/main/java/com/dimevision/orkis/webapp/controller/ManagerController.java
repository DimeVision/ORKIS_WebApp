package com.dimevision.orkis.webapp.controller;

import com.dimevision.orkis.webapp.service.EmployeeDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Dimevision
 * @version 0.1
 */

@Controller
public class ManagerController {

    private final EmployeeDetailsServiceImplementation employeeService;

    @Autowired
    public ManagerController(EmployeeDetailsServiceImplementation employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/manager")
    @PreAuthorize("hasAnyAuthority('admin:read', 'superadmin:read', 'manager:read')")
    public String showManagerPanel() {
        return "manager-panel";
    }
}
