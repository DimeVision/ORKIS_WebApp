package com.dimevision.orkis.webapp.repository;

import com.dimevision.orkis.webapp.entity.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 *
 * @author Dimevision
 * @version 0.1
 */

public interface EmployeeAuthorityRepository extends JpaRepository<EmployeeRole, Long> {
}
