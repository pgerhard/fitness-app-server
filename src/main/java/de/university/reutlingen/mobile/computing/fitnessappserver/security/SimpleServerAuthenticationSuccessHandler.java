package de.university.reutlingen.mobile.computing.fitnessappserver.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import reactor.core.publisher.Mono;

public class SimpleServerAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    @Override
    public Mono<Void> onAuthenticationSuccess ( WebFilterExchange webFilterExchange, Authentication authentication ) {
        webFilterExchange.getExchange ().getResponse ().setStatusCode ( HttpStatus.OK );
        return Mono.empty ();
    }
}
