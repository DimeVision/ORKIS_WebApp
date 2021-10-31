package com.dimevision.orkis.webapp.entity.management;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Dimevision
 * @version 0.1
 */

@AllArgsConstructor
@Getter
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),

    CLIENT_READ("client:read"),
    CLIENT_WRITE("client:write"),

    AGENT_READ("agent:read"),
    AGENT_WRITE("agent:write"),

    MANAGER_READ("manager:read"),
    MANAGER_WRITE("manager:write")
    ;

    private final String permission;
}
