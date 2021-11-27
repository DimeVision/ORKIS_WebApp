package com.dimevision.orkis.webapp.controller;

import com.dimevision.orkis.webapp.entity.Agent;
import com.dimevision.orkis.webapp.entity.Organization;
import com.dimevision.orkis.webapp.repository.OrganizationRepository;
import com.dimevision.orkis.webapp.service.AgentDetailsServiceImplementation;
import com.dimevision.orkis.webapp.service.ClientDetailsServiceImplementation;
import com.dimevision.orkis.webapp.service.EmployeeDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Dimevision
 * @version 0.1
 */

@Controller
public class AdministratorController {

    private final OrganizationRepository organizationRepository;

    private final AgentDetailsServiceImplementation agentService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdministratorController(AgentDetailsServiceImplementation agentService, EmployeeDetailsServiceImplementation employeeService, OrganizationRepository organizationRepository, PasswordEncoder passwordEncoder) {
        this.agentService = agentService;
        this.organizationRepository = organizationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/")
    public String showStarterPage() {
        return "starter-page";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('admin:read', 'superadmin:read')")
    public String showAdminPanel(Model model) {
        model.addAttribute("employeeCount", EmployeeDetailsServiceImplementation.employeesCount);
        model.addAttribute("clientCount", ClientDetailsServiceImplementation.clientsCount);
        return "admin-panel";
    }

    @GetMapping("/agents")
    @PreAuthorize("hasAnyAuthority('admin:read', 'superadmin:read', 'client:read')")
    public String showAllAgents(Model model,
                                @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                @RequestParam(value = "size", required = false, defaultValue = "25") int size) {

        model.addAttribute("posts", agentService.getPage(pageNumber, size));
        model.addAttribute("agents");

        return "agents-list";
    }

    @GetMapping("/agent-create")
    public String addAgentForm(Model model) {

        model.addAttribute("agent", new Agent());
        model.addAttribute("organizations", organizationRepository.findAll());

        return "add-agent";
    }

    @PostMapping("/agent-create")
    public RedirectView addAgent(@ModelAttribute("agent") Agent agent) {

        agent.setPassword(passwordEncoder.encode(agent.getPassword()));

        agentService.saveAgent(agent);
        return new RedirectView("agents", true);
    }

    @GetMapping("/agent-update/{id}")
    public String showUpdateAgentForm(@PathVariable Long id, Model model) {

        model.addAttribute("agent", agentService.findAgentById(id));
        model.addAttribute("organizations", organizationRepository.findAll());

        return "update-agent";
    }

    @PostMapping("/agent-update/{id}")
    public String updateAgent(@PathVariable("id") Long id,
                              @RequestParam(name = "organization") Organization organization) {

        Agent agent = agentService.findAgentById(id);
        agent.setOrganization(organization);
        agent.setPassword(passwordEncoder.encode(agent.getPassword()));
        agentService.saveAgent(agent);

        return "redirect:/agents";
    }

    @GetMapping("/delete-agent/{id}")
    public String deleteClient(@PathVariable Long id) {
        agentService.deleteAgentById(id);
        return "redirect:/clients";
    }
}
