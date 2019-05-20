package de.university.reutlingen.mobile.computing.fitnessappserver.configuration;

import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.ExerciseManagementService;
import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.PlanManagementService;
import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.impl.ExerciseManagementServiceImpl;
import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.impl.PlanManagementServiceImpl;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.ExerciseService;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.PlanService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BoundaryConfiguration {

    @Bean
    public ExerciseManagementService exerciseManagementService ( ExerciseService exerciseService ) {
        return new ExerciseManagementServiceImpl ( exerciseService );
    }

    @Bean
    public PlanManagementService planManagementService ( PlanService planService ){
        return new PlanManagementServiceImpl ( planService );
    }
}
