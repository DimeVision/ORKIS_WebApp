package com.dimevision.orkis.webapp.repository;

import com.dimevision.orkis.webapp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dimevision
 * @version 0.1
 */

public interface ClientRepository extends JpaRepository<Client, Long> {

    void findClientByName(String name);
}
