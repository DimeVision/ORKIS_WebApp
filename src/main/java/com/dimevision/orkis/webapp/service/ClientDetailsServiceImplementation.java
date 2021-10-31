package com.dimevision.orkis.webapp.service;

import com.dimevision.orkis.webapp.entity.Client;
import com.dimevision.orkis.webapp.repository.ClientRepository;
import com.dimevision.orkis.webapp.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dimevision
 * @version 0.1
 */

@Service
@RequiredArgsConstructor
public class ClientDetailsServiceImplementation implements UserDetailsService {

    private final ClientRepository clientRepository;

    public Client findClientById(Long id) {
        return clientRepository.getById(id);
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findClientByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Email %s not exists", username)));

        return SecurityUser.fromClient(client);
    }
}
