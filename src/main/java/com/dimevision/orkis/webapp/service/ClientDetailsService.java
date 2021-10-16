package com.dimevision.orkis.webapp.service;

import com.dimevision.orkis.webapp.entity.Client;
import com.dimevision.orkis.webapp.entity.ClientStatus;
import com.dimevision.orkis.webapp.repository.ClientRepository;
import com.dimevision.orkis.webapp.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author Dimevision
 * @version 0.1
 */

@Service
public class ClientDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findClientByEmail(String email) {
        return clientRepository.findClientByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User email '%s' not found", email)));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Client client = clientRepository.findClientByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User email '%s' not found", email)));

        return new User(client.getEmail(), client.getPassword(), Collections.singleton(mapStatusToClient(client.getStatus())));
    }

    private GrantedAuthority mapStatusToClient(ClientStatus status) {
        return new SimpleGrantedAuthority(status.getName());
    }
}
