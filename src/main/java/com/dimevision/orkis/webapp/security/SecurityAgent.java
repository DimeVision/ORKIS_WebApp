package com.dimevision.orkis.webapp.security;

import com.dimevision.orkis.webapp.entity.Agent;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @author Dimevision
 * @version 0.1
 */

@Data
public class SecurityAgent implements UserDetails {

    private String username;
    private String password;
    private boolean isActive;
    private Set<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return username;
    }

    @Override
    public String getUsername() {
        return username;
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

    public static UserDetails fromAgent(Agent agent) {
        return new User(
                agent.getEmail(), agent.getPassword(),
                agent.getRole().getAuthorities()
        );
    }
}
