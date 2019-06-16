package de.university.reutlingen.mobile.computing.fitnessappserver.configuration;

import de.university.reutlingen.mobile.computing.fitnessappserver.control.UserService;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.UserRepository;
import de.university.reutlingen.mobile.computing.fitnessappserver.util.ContextPathWebFilter;
import io.micrometer.core.ipc.http.HttpSender;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain ( ServerHttpSecurity http, ServerProperties serverProperties ) {
        http.authorizeExchange ()
                .anyExchange ()
                .authenticated ()
            .and ()
                .formLogin ()
//                    .loginPage ( serverProperties.getServlet ().getContextPath () + "/login" )
                    .loginPage ( "/login" )
                    .authenticationSuccessHandler ( new RedirectServerAuthenticationSuccessHandler ( serverProperties.getServlet ().getContextPath () + "/api/v1/exercises" ) )
            .and ()
                .csrf ().disable ()
            .addFilterAt ( new ContextPathWebFilter ( "/fitness-app" ), SecurityWebFiltersOrder.FIRST );

        return http.build ();
    }

    @Bean
    public UserService userDetailsService ( UserRepository userRepository, PasswordEncoder encoder ) {
        return new UserService ( userRepository, encoder );
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder ();
    }
}
