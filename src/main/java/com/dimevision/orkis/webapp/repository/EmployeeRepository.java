package com.dimevision.orkis.webapp.repository;

import com.dimevision.orkis.webapp.entity.Employee;
import com.dimevision.orkis.webapp.entity.management.Role;
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
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeByEmail(String email);

    List<Employee> findAllByOrganizationId(Long organization_id);
}
