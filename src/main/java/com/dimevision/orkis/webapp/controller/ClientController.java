package com.dimevision.orkis.webapp.controller;

import com.dimevision.orkis.webapp.entity.Client;
import com.dimevision.orkis.webapp.entity.Passport;
import com.dimevision.orkis.webapp.repository.PassportRepository;
import com.dimevision.orkis.webapp.service.ClientDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dimevision
 * @version 0.1
 */

@Controller
public class ClientController {

    private final ClientDetailsServiceImplementation clientService;
    private final PassportRepository passportRepository;

    @Autowired
    public ClientController(ClientDetailsServiceImplementation clientService, PassportRepository passportRepository) {
        this.clientService = clientService;
        this.passportRepository = passportRepository;
    }

    @GetMapping("/registration")
    public String test() {
        return "registration";
    }

    @GetMapping("/clients")
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public String showAllClients(Model model) {

        List<Client> clients = clientService.findAllClients();
        model.addAttribute("clients", clients);

        return "clients-list";
    }

    @GetMapping("/create-client")
    public String showClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "add-client";
    }

    @PostMapping("/create-client")
    public String createClient(@ModelAttribute Passport passport, @ModelAttribute Client client, Model model) {
        clientService.saveClient(client);
        model.addAttribute("client", client);
        return "redirect:/create";
    }

    @GetMapping("/create-passport")
    public String showPassportForm(Model model) {
        model.addAttribute("passport", new Passport());
        return "add-client-passport";
    }

    @PostMapping("/create-passport")
    public String createPassport(@ModelAttribute Passport passport, Model model) {

        passportRepository.save(passport);
        model.addAttribute("passport", passport);

        return "redirect:/create-client";
    }

    @GetMapping("delete-client/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return "redirect:/clients";
    }
}
