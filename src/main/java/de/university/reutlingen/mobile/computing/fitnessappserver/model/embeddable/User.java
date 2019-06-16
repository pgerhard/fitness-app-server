package de.university.reutlingen.mobile.computing.fitnessappserver.model.embeddable;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class User {

    private String username;

    private String firstName;

    private String lastName;

    private List<GrantedAuthority> grantedAuthorities;

    public String getUsername () {
        return username;
    }

    public void setUsername ( String username ) {
        this.username = username;
    }

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName ( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName ( String lastName ) {
        this.lastName = lastName;
    }

    public List<GrantedAuthority> getGrantedAuthorities () {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities ( List<GrantedAuthority> grantedAuthorities ) {
        this.grantedAuthorities = grantedAuthorities;
    }
}
