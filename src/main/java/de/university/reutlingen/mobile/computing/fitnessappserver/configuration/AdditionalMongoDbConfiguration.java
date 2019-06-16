package de.university.reutlingen.mobile.computing.fitnessappserver.configuration;

import de.university.reutlingen.mobile.computing.fitnessappserver.util.UuidGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.util.IdGenerator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class AdditionalMongoDbConfiguration {

    @Bean
    public IdGenerator idGenerator () {
        return new UuidGenerator ();
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener () {
        return new ValidatingMongoEventListener ( validator () );
    }

    @Bean
    public LocalValidatorFactoryBean validator () {
        return new LocalValidatorFactoryBean ();
    }
}
