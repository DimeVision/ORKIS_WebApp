package com.dimevision.orkis.webapp.repository;

import com.dimevision.orkis.webapp.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Dimevision
 * @version 0.1
 */

public interface AgentRepository extends JpaRepository<Agent, Long> {

    Optional<Agent> findAgentByEmail(String email);

    @Override
    @Query(value = "SELECT * " +
            "FROM agent " +
            "LEFT JOIN organization o " +
            "on agent.organization_id = o.id",
            nativeQuery = true)
    List<Agent> findAll();
}
