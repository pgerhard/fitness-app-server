package de.university.reutlingen.mobile.computing.fitnessappserver.configuration;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class UserAuditingComponent implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor () {
        final Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
        if (authentication == null) {
            return Optional.of ( "SYSTEM" );
        } else {
            String uname = authentication.getName ();
            return Optional.of ( uname );
        }
    }


}