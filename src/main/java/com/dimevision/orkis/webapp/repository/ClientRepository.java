package com.dimevision.orkis.webapp.repository;

import com.dimevision.orkis.webapp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Dimevision
 * @version 0.1
 */

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByEmail(String email);

    @Override
//    @Query(value = "SELECT " +
//            "* " +
//            "FROM client AS c " +
//            "LEFT JOIN passport AS p " +
//            "ON c.passport_id = p.id", nativeQuery = true)
    List<Client> findAll();
}
