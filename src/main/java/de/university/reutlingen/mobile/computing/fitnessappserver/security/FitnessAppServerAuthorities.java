package de.university.reutlingen.mobile.computing.fitnessappserver.security;

import org.springframework.security.core.GrantedAuthority;

public enum FitnessAppServerAuthorities implements GrantedAuthority {

    ADMIN,
    USER;

    @Override
    public String getAuthority () {
        return this.name ();
    }
}
