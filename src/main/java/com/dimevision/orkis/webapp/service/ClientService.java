package com.dimevision.orkis.webapp.service;

import com.dimevision.orkis.webapp.entity.Client;
import com.dimevision.orkis.webapp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dimevision
 * @version 0.1
 */

@Service
public class ClientService {

    private final ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Client findClientById(Long id) {
        return repository.getById(id);
    }

    public List<Client> findAllClients() {
        return repository.findAll();
    }

    public void saveClient(Client client) {
        repository.save(client);
    }

    public void deleteClientById(Long id) {
        repository.deleteById(id);
    }

    public Client findClientByName(String name) {
        return repository.findClientByEmail(name)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username $s is not exists", name)));
    }
}
