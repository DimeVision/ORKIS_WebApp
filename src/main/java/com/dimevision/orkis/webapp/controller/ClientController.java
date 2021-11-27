package com.dimevision.orkis.webapp.controller;

import com.dimevision.orkis.webapp.entity.Client;
import com.dimevision.orkis.webapp.entity.Passport;
import com.dimevision.orkis.webapp.repository.PassportRepository;
import com.dimevision.orkis.webapp.service.ClientDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dimevision
 * @version 0.1
 */

@Controller
public class ClientController {

    private final ClientDetailsServiceImplementation clientService;
    private final PassportRepository passportRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientController(ClientDetailsServiceImplementation clientService, PassportRepository passportRepository, PasswordEncoder passwordEncoder) {
        this.clientService = clientService;
        this.passportRepository = passportRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public String test() {
        return "registration";
    }

    @GetMapping("/clients")
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public String showAllClients(Model model,
                                 @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                 @RequestParam(value = "size", required = false, defaultValue = "25") int size) {

        model.addAttribute("clients", clientService.getPage(pageNumber, size));

        return "clients-list";
    }

    @GetMapping("/create-passport")
    public String showPassportForm(Model model) {

        model.addAttribute("passport", new Passport());
        return "add-client-passport";
    }

    @PostMapping("/create-passport")
    public String createPassport(@ModelAttribute("passportTest") Passport passport,
                                 Model model) {

        passportRepository.save(passport);

        Client client = new Client();
        client.setPassport(passport);

        model.addAttribute("passport", passport);
        model.addAttribute("client", client);

        return "add-client";
    }

    @PostMapping("/create-client")
    public String createClient(@ModelAttribute("client") Client client) {

        clientService.saveClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/delete-client/{id}")
    public String deleteClient(@PathVariable Long id) {

        clientService.deleteClientById(id);
        return "redirect:/clients";
    }

    @GetMapping("/update-client/{id}")
    public String showClientUpdateForm(@PathVariable Long id, Model model) {

        model.addAttribute("client", clientService.findClientById(id));
        return "update-client";
    }

    @PostMapping("/update-client/{id}")
    public String updateClient(@PathVariable("id") Long id) {

        Client client = clientService.findClientById(id);
        client.setPassword(passwordEncoder.encode(client.getPassword()));

        return "redirect:/clients";
    }
}
