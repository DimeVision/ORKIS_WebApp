package com.dimevision.orkis.webapp.service;

import com.dimevision.orkis.webapp.entity.Client;
import com.dimevision.orkis.webapp.entity.management.Role;
import com.dimevision.orkis.webapp.repository.ClientRepository;
import com.dimevision.orkis.webapp.security.SecurityUser;
import com.dimevision.orkis.webapp.service.paging.Paged;
import com.dimevision.orkis.webapp.service.paging.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Dimevision
 * @version 0.1
 */

@Service
public class ClientDetailsServiceImplementation implements UserDetailsService {

    private final ClientRepository clientRepository;

    public static long clientsCount;

    @Autowired
    public ClientDetailsServiceImplementation(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        clientsCount = clientRepository.count();
    }

    public Client findClientById(Long id) {
        return clientRepository.getById(id);
    }

    public void saveClient(Client client) {
        client.setRole(Role.CLIENT);
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

    public Paged<Client> getPage(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<Client> clientPage = clientRepository.findAll(request);
        return new Paged<>(clientPage, Paging.of(clientPage.getTotalPages(), pageNumber, size));
    }
}
