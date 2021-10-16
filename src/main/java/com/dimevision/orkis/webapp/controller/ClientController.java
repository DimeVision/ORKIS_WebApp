package com.dimevision.orkis.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Dimevision
 * @version 0.1
 */

@Controller
public class ClientController {

    @GetMapping("/")
    public String showLandingPage() {

        return "landing";
    }

    @GetMapping("/registration")
    public String test() {

        return "registration";
    }
}
