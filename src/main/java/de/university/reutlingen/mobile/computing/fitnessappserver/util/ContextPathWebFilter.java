package de.university.reutlingen.mobile.computing.fitnessappserver.util;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public class ContextPathWebFilter implements WebFilter {
        private final String contextPath;

        public ContextPathWebFilter ( String contextPath ) {
            this.contextPath = contextPath;
        }

        @Override
        public Mono<Void> filter ( ServerWebExchange exchange, WebFilterChain chain ) {
            ServerHttpRequest request = exchange.getRequest ();
            if ( request.getURI ().getPath ().startsWith ( contextPath ) ) {
                return chain.filter (
                        exchange.mutate ()
                                .request ( request.mutate ().contextPath ( contextPath ).build () )
                                .build () );
            }
            return chain.filter ( exchange );
        }
    }