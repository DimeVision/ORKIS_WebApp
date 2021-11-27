package com.dimevision.orkis.webapp.controller;

import com.dimevision.orkis.webapp.entity.*;
import com.dimevision.orkis.webapp.repository.*;
import com.dimevision.orkis.webapp.service.EmployeeDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * @author Dimevision
 * @version 0.1
 */

@Controller
public class ManagerController {

    private final EmployeeDetailsServiceImplementation employeeService;
    private final AgreementRepository agreementRepository;
    private final OrganizationRepository organizationRepository;
    private final AgentRepository agentRepository;
    private final CountryRepository countryRepository;
    private final ClientRepository clientRepository;
    private final CityRepository cityRepository;
    private final ContractRepository contractRepository;
    private final RouteRepository routeRepository;

    @Autowired
    public ManagerController(EmployeeDetailsServiceImplementation employeeService,
                             AgreementRepository agreementRepository,
                             OrganizationRepository organizationRepository,
                             AgentRepository agentRepository,
                             CountryRepository countryRepository,
                             ClientRepository clientRepository,
                             CityRepository cityRepository,
                             ContractRepository contractRepository, RouteRepository routeRepository) {
        this.employeeService = employeeService;
        this.agreementRepository = agreementRepository;
        this.organizationRepository = organizationRepository;
        this.agentRepository = agentRepository;
        this.countryRepository = countryRepository;
        this.clientRepository = clientRepository;
        this.cityRepository = cityRepository;
        this.contractRepository = contractRepository;
        this.routeRepository = routeRepository;
    }

    @GetMapping("/manager")
    @PreAuthorize("hasAnyAuthority('superadmin:read', 'manager:read')")
    public String showManagerPanel() {
        return "manager-panel";
    }

    @GetMapping("/agreements")
    @PreAuthorize("hasAnyAuthority('superadmin:read', 'manager:read')")
    public String showAllAgreements(
            Model model,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "25") int size) {

        model.addAttribute("agreementsPage", employeeService.getAgreementPage(pageNumber, size));

        return "agreements-list";
    }

    @GetMapping("/create-route")
    public String createRouteForm(@ModelAttribute Route route) {

        return "add-route";
    }

    @GetMapping("/organizations")
    public @ResponseBody
    List<Agent> findAllAgents(@RequestParam(value = "organization_id") Long organization_id) {
        Organization organization = organizationRepository.findById(organization_id)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Organization %d not exists", organization_id)));
        return agentRepository.findAllByOrganizationId(organization.getId());
    }

    @GetMapping("/cities")
    public @ResponseBody
    List<City> findAllCities(@RequestParam(value = "country_id") Long country_id) {
        Country country = countryRepository.findById(country_id)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Organization %d not exists", country_id)));
        return cityRepository.findAllByCountryId(country.getId());
    }

    @GetMapping("/create-agreement")
    public String createAgreementForm(
            @ModelAttribute(name = "agreement") Agreement agreement,
            @ModelAttribute(name = "organization") Organization organization,
            @ModelAttribute(name = "route") Route route,
            @RequestParam(value = "city_id", required = false) Long city_id,
            @RequestParam(value = "country_id", required = false) Long country_id,
            Model model
    ) {

        model.addAttribute("organizations", organizationRepository.findAll());
        model.addAttribute("agents", agentRepository.findAllByOrganizationId(organization.getId()));
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("cities", cityRepository.findAllByCountryId(country_id));

        return "add-agreement";
    }

    @PostMapping("/create-agreement")
    public String addAgreement(
            Route route,
            @ModelAttribute("emptyAgreement") Agreement agreement,
            @ModelAttribute("contract") Contract contract) {

        route.setCountries(agreement.getCountry());
        routeRepository.save(route);

        System.out.println("\t" + route);

        agreement.setTitle("Договор " + agreement.getClient().getFullName() + " от " + agreement.getIssueDate());
        agreementRepository.save(agreement);

        contract.setTitle(agreement.getTitle());

        return "add-contract";
    }

//    @GetMapping("/create-contract")
//    public String createContractForm(
//            Contract contract,
//            @ModelAttribute("agreementData") Agreement agreement,
//            @ModelAttribute("agent") Agent agent,
//            Model model) {
//
//        model.addAttribute("agent", agent);
//        model.addAttribute("contract", contract);
//
//        return "add-contract";
//    }

    @PostMapping("/create-contract")
    public RedirectView addContract(@ModelAttribute("contract") Contract contract) {

        contractRepository.save(contract);
        return new RedirectView("/contracts", true);
    }
}
