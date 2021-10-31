package com.dimevision.orkis.webapp.service;

import com.dimevision.orkis.webapp.entity.Agent;
import com.dimevision.orkis.webapp.entity.Client;
import com.dimevision.orkis.webapp.repository.AgentRepository;
import com.dimevision.orkis.webapp.security.SecurityAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dimevision
 * @version 0.1
 */

@Service("agentDetailsServiceImplementation")
public class AgentDetailsServiceImplementation implements UserDetailsService {

    private final AgentRepository agentRepository;

    @Autowired
    public AgentDetailsServiceImplementation(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public Agent findAgentById(Long id) {
        return agentRepository.getById(id);
    }

    public List<Agent> findAllAgents() {
        return agentRepository.findAll();
    }

    public void saveAgent(Agent client) {
        agentRepository.save(client);
    }

    public void deleteClientById(Long id) {
        agentRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Agent agent = agentRepository.findAgentByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                                String.format("No such username with registered email %s", username)
                        ));

        return SecurityAgent.fromAgent(agent);
    }
}
