package de.university.reutlingen.mobile.computing.fitnessappserver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class AuditingConfiguration {

    @Bean
    public AuditorAware<String> userAuditingComponent () {
        return new UserAuditingComponent ();
    }

}
