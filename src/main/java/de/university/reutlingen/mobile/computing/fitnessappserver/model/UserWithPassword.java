package de.university.reutlingen.mobile.computing.fitnessappserver.model;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.embeddable.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Document
public class UserWithPassword extends AbstractDocument<ObjectId> implements UserDetails {

    @NotNull
    private User user;

    @NotNull
    private String password;

    public UserWithPassword () {
        // nothing to do
    }

    public User getUser () {
        return user;
    }

    public void setUser ( User user ) {
        this.user = user;
    }

    public void setPassword ( String password ) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        return this.user.getGrantedAuthorities ();
    }

    @Override
    public String getPassword () {
        return this.password;
    }

    @Override
    public String getUsername () {
        return this.user.getUsername ();
    }

    @Override
    public boolean isAccountNonExpired () {
        return false;
    }

    @Override
    public boolean isAccountNonLocked () {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired () {
        return false;
    }

    @Override
    public boolean isEnabled () {
        return false;
    }
}
