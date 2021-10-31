package com.dimevision.orkis.webapp.controller;

import com.dimevision.orkis.webapp.entity.Agent;
import com.dimevision.orkis.webapp.service.AgentDetailsServiceImplementation;
import com.dimevision.orkis.webapp.service.EmployeeDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Dimevision
 * @version 0.1
 */

@Controller
public class AdministratorController {

    private final AgentDetailsServiceImplementation agentService;
    private final EmployeeDetailsServiceImplementation employeeService;

    @Autowired
    public AdministratorController(AgentDetailsServiceImplementation agentService, EmployeeDetailsServiceImplementation employeeService) {
        this.agentService = agentService;
        this.employeeService = employeeService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('admin:read', 'superadmin:read')")
    public String showAdminPanel(Model model) {
        model.addAttribute("employeeCount", (long) employeeService.getAllEmployees().size());
        return "admin-panel";
    }

    @GetMapping("/agents")
    @PreAuthorize("hasAnyAuthority('admin:read', 'superadmin:read', 'client:read')")
    public String showAllAgents(Model model) {

        List<Agent> agents = agentService.findAllAgents();
        model.addAttribute("agents", agents);
        return "agents-list";
    }
}
