package com.dimevision.orkis.webapp.controller;

import com.dimevision.orkis.webapp.entity.Agreement;
import com.dimevision.orkis.webapp.repository.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Dimevision
 * @version 0.1
 */

@Controller
public class AgreementController {

    private final AgreementRepository agreementRepository;

    @Autowired
    public AgreementController(AgreementRepository agreementRepository) {
        this.agreementRepository = agreementRepository;
    }

    @GetMapping("/agreements")
    public String showAllAgreements(Model model) {
        model.addAttribute("agreementsList", agreementRepository.findAll());

        return "agreement-list";
    }
}
