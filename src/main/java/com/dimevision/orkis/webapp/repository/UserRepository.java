package com.dimevision.orkis.webapp.repository;

import com.dimevision.orkis.webapp.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Dimevision
 * @version 0.1
 */

@Repository
public interface UserRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeesByEmail(String email);

    @Override
    Page<Employee> findAll(Pageable pageable);
}
