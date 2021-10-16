package com.dimevision.orkis.webapp.service;

import com.dimevision.orkis.webapp.entity.Employee;
import com.dimevision.orkis.webapp.entity.EmployeeRole;
import com.dimevision.orkis.webapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Dimevision
 * @version 0.1
 */

@Service
public class EmployeeDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Employee employee = employeeRepository.findEmployeeByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Username with email not found"));

        return new User(employee.getEmail(), employee.getPassword(), mapRolesToEmployee(employee.getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToEmployee(Collection<EmployeeRole> employeeRoles) {
        return employeeRoles.stream()
                .map(employee -> new SimpleGrantedAuthority(employee.getName()))
                .collect(Collectors.toList());
    }
}
