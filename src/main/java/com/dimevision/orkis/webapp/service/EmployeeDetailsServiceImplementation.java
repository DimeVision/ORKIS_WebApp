package com.dimevision.orkis.webapp.service;

import com.dimevision.orkis.webapp.entity.Agreement;
import com.dimevision.orkis.webapp.entity.Employee;
import com.dimevision.orkis.webapp.repository.AgreementRepository;
import com.dimevision.orkis.webapp.repository.UserRepository;
import com.dimevision.orkis.webapp.security.SecurityAdmin;
import com.dimevision.orkis.webapp.service.paging.Paged;
import com.dimevision.orkis.webapp.service.paging.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final AgreementRepository agreementRepository;

    public static long employeesCount;

    @Autowired
    public EmployeeDetailsServiceImplementation(UserRepository userRepository, AgreementRepository agreementRepository) {
        this.userRepository = userRepository;
        this.agreementRepository = agreementRepository;
        employeesCount = userRepository.count();
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

    public Paged<Employee> getEmployeePage(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1,
                size);
        Page<Employee> postPage = userRepository.findAll(request);
        return new Paged<>(postPage,
                Paging.of(postPage.getTotalPages(), pageNumber, size));
    }

    public Paged<Agreement> getAgreementPage(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<Agreement> agreementPage = agreementRepository.findAll(request);
        return new Paged<>(agreementPage,
                Paging.of(agreementPage.getTotalPages(), pageNumber, size));
    }
}
