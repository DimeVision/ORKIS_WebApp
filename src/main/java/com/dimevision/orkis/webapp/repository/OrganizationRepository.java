package com.dimevision.orkis.webapp.repository;

import com.dimevision.orkis.webapp.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Dimevision
 * @version 0.1
 */

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

//    List<Organization> findById(Long id);

    Optional<Organization> findById(Long id);
}
