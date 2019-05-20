package de.university.reutlingen.mobile.computing.fitnessappserver.configuration;

import de.university.reutlingen.mobile.computing.fitnessappserver.control.ExerciseService;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.PlanService;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.impl.ExerciseServiceImpl;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.impl.PlanServiceImpl;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.ExerciseRepository;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.PlanRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControlConfiguration {

    @Bean
    public ExerciseService exerciseService( ExerciseRepository exerciseRepository ){
        return new ExerciseServiceImpl ( exerciseRepository );
    }

    @Bean
    public PlanService planService( PlanRepository planRepository ){
        return new PlanServiceImpl ( planRepository );
    }
}
