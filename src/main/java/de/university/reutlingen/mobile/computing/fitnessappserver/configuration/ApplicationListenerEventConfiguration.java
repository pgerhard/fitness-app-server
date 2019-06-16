package de.university.reutlingen.mobile.computing.fitnessappserver.configuration;

import de.university.reutlingen.mobile.computing.fitnessappserver.repository.listener.IdGenerationEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.IdGenerator;

@Configuration
public class ApplicationListenerEventConfiguration {

    @Bean
    public IdGenerationEventListener idGenerationEventListener( IdGenerator idGenerator ) {
        return new IdGenerationEventListener ( idGenerator );
    }
}
