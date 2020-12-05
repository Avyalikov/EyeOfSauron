package org.example.EyeOfSauron.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, REGISTRY;

    @Override
    public String getAuthority() {
        return name();
    }
}
