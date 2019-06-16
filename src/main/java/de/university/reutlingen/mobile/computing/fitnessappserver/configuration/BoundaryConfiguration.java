package de.university.reutlingen.mobile.computing.fitnessappserver.configuration;

import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.ExerciseManagementService;
import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.PlanManagementService;
import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.SessionManagementService;
import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.impl.ExerciseManagementServiceImpl;
import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.impl.PlanManagementServiceImpl;
import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.impl.SessionManagementServiceImpl;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.ExerciseService;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.PlanService;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.SessionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link Configuration} for boundary services.
 */
@Configuration
public class BoundaryConfiguration {

    /**
     * Create the {@link ExerciseManagementService} bean.
     *
     * @param exerciseService to be used by the bean
     *
     * @return the bean
     */
    @Bean
    public ExerciseManagementService exerciseManagementService ( ExerciseService exerciseService ) {
        return new ExerciseManagementServiceImpl ( exerciseService );
    }

    /**
     * Create the {@link PlanManagementService} bean.
     *
     * @param planService to be used by the bean
     *
     * @return the bean
     */
    @Bean
    public PlanManagementService planManagementService ( PlanService planService ) {
        return new PlanManagementServiceImpl ( planService );
    }

    /**
     * Create the {@link SessionManagementService} bean.
     *
     * @param sessionService to use
     *
     * @return the bean
     */
    @Bean
    public SessionManagementService sessionManagementService ( SessionService sessionService ) {
        return new SessionManagementServiceImpl ( sessionService );
    }
}
