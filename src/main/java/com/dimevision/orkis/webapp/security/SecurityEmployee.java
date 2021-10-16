package com.dimevision.orkis.webapp.security;

import com.dimevision.orkis.webapp.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @author Dimevision
 * @version 0.1
 */

@Data
@AllArgsConstructor
public class SecurityEmployee {

    private String email;
    private String password;
    private List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

//    public static UserDetails fromEmployee(Employee employee) {
//        return new User(() -> new SimpleGrantedAuthority(
//                // TODO: 10/14/2021
//                employee.getEmail(), employee.getPassword(),
//
//        ));
//    }


}
