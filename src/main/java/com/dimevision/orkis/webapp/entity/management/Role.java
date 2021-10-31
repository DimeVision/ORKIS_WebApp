package com.dimevision.orkis.webapp.entity.management;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.dimevision.orkis.webapp.entity.management.Permission.*;

/**
 * @author Dimevision
 * @version 0.1
 */

@AllArgsConstructor
@Getter
public enum Role {

    SUPERADMIN(Set.of(ADMIN_READ, ADMIN_WRITE)),
    ADMIN(Set.of(ADMIN_READ, ADMIN_WRITE, CLIENT_WRITE)),
    AGENT(Set.of(AGENT_READ, AGENT_WRITE)),
    CLIENT(Set.of(CLIENT_READ, CLIENT_WRITE)),
    MANAGER(Set.of(MANAGER_READ, MANAGER_WRITE)),
    ACCOUNTANT(Set.of());

    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
