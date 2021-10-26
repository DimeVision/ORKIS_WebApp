package com.dimevision.orkis.webapp.repository;

import com.dimevision.orkis.webapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Dimevision
 * @version 0.1
 */

public interface UserRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeesByEmail(String email);
}
