package com.dimevision.orkis.webapp.repository;

import com.dimevision.orkis.webapp.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Dimevision
 * @version 0.1
 */

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    Agent findAgentByShortName(String name);

    @Override
    @Query(value = "SELECT * " +
            "FROM agent " +
            "LEFT JOIN organization o " +
            "on agent.organization_id = o.id",
            nativeQuery = true)
    List<Agent> findAll();
}
