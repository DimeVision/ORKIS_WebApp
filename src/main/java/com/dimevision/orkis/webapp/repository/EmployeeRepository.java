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

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeByEmail(String email);

//    @Query(value = "SELECT " +
//            "* " +
//            "FROM employee AS emp " +
//            "LEFT JOIN organization AS org " +
//            "ON emp.organization_id = org.id", nativeQuery = true)
    List<Employee> findAllByIdAndFullNameAndRole(Long id, String fullName, Role role);
}
