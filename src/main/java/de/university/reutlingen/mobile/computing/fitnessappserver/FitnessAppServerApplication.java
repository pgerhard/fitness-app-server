package de.university.reutlingen.mobile.computing.fitnessappserver;

import de.university.reutlingen.mobile.computing.fitnessappserver.configuration.properties.FitnessAppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Fitness app server main class.
 *
 * TODO Implement endpoint to load all exercise plans
 * TODO Implement endpoint to load an exercise plan
 */
@SpringBootApplication
@EnableConfigurationProperties ( value = {FitnessAppProperties.class} )
public class FitnessAppServerApplication {

    public static void main ( String[] args ) {
        SpringApplication.run ( FitnessAppServerApplication.class, args );
    }

}
