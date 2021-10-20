package com.dimevision.orkis.webapp.controller;

import com.dimevision.orkis.webapp.entity.Client;
import com.dimevision.orkis.webapp.repository.ClientRepository;
import com.dimevision.orkis.webapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Dimevision
 * @version 0.1
 */

@Controller
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/")
    public String showLandingPage() {

        return "landing";
    }

    @GetMapping("/clients")
    public String showAllClients(Model model) {

        List<Client> clients = clientService.findAllClients();
        model.addAttribute("clients", clients);

        return "clients-view";
    }

    @GetMapping("/registration")
    public String test() {

        return "registration";
    }
}
