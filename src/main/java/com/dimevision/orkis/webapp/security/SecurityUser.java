package com.dimevision.orkis.webapp.security;

import com.dimevision.orkis.webapp.entity.Client;
import com.dimevision.orkis.webapp.entity.ClientStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author Dimevision
 * @version 0.1
 */

@Data
@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

//    public static UserDetails fromClient(Client client) {
//        return new User(
//                client.getEmail(), client.getPassword(),
//                client.getStatus().equals(),
//                client.getStatus().equals(),
//                client.getStatus().equals(),
//                client.getStatus().equals(),
//                client.getStatus().getName()
//        );
//    }
}
