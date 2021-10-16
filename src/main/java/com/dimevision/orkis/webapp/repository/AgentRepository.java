package com.dimevision.orkis.webapp.repository;

import com.dimevision.orkis.webapp.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dimevision
 * @version 0.1
 */

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    Agent findAgentByShortName(String name);
}
