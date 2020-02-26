package com.lilas.taskmanager.domain;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    EMPLOYEE,
    MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}
