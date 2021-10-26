package com.dimevision.orkis.webapp.service;

import com.dimevision.orkis.webapp.entity.Employee;
import com.dimevision.orkis.webapp.repository.UserRepository;
import com.dimevision.orkis.webapp.security.SecurityAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dimevision
 * @version 0.1
 */

@Service("employeeDetailsServiceImplementation")
public class EmployeeDetailsServiceImplementation implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public EmployeeDetailsServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Employee getEmployeeById(Long id) {
        return userRepository.getById(id);
    }

    public List<Employee> getAllEmployees() {
        return userRepository.findAll();
    }

    public void saveEmployee(Employee employee) {
        userRepository.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee employee = userRepository.findEmployeesByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Email %s not exists", username)));

        return SecurityAdmin.fromUser(employee);
    }
}
