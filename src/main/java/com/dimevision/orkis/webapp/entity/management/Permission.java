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

    CLIENT_READ("client_read"),
    CLIENT_WRITE("client_write");

    private final String permission;
}
