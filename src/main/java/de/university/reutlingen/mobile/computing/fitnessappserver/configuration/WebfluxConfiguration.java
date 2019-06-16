package de.university.reutlingen.mobile.computing.fitnessappserver.configuration;

import de.university.reutlingen.mobile.computing.fitnessappserver.configuration.properties.FitnessAppProperties;
import de.university.reutlingen.mobile.computing.fitnessappserver.util.ContextPathWebFilter;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
public class WebfluxConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter ( FitnessAppProperties properties ) {
        final CorsConfiguration corsConfiguration = new CorsConfiguration ();
        corsConfiguration.setAllowCredentials ( true );

        corsConfiguration.setAllowedHeaders ( properties.getAllowedHeaders () );
        corsConfiguration.setAllowedMethods ( properties.getAllowedMethods () );
        corsConfiguration.setAllowedOrigins ( properties.getAllowedOrigins () );

        final UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource ();
        configSource.registerCorsConfiguration ( "/**", corsConfiguration );
        return new CorsWebFilter ( configSource );
    }

    /**
     * Register a web filter to properly handle the context path set in application.properties. Workaround for {@link
     * https://github.com/spring-projects/spring-boot/issues/10129}
     *
     * @param serverProperties to use
     *
     * @return the web filter
     */
//    @Bean
//    public WebFilter contextPathWebFilter ( ServerProperties serverProperties ) {
//        String contextPath = serverProperties.getServlet ().getContextPath ();
//        return new ContextPathWebFilter ( contextPath );
//    }
}
