package de.university.reutlingen.mobile.computing.fitnessappserver.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import reactor.core.publisher.Mono;

public class SimpleServerAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {

    @Override
    public Mono<Void> onAuthenticationFailure ( WebFilterExchange webFilterExchange, AuthenticationException exception ) {
        webFilterExchange.getExchange ().getResponse ().setStatusCode ( HttpStatus.UNAUTHORIZED );
        return Mono.empty ();
    }
}
