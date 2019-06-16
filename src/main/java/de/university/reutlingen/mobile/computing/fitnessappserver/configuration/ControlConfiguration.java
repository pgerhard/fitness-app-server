package de.university.reutlingen.mobile.computing.fitnessappserver.configuration;

import de.university.reutlingen.mobile.computing.fitnessappserver.control.ExerciseService;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.PlanService;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.SessionService;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.UserService;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.impl.ExerciseServiceImpl;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.impl.PlanServiceImpl;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.impl.SessionServiceImpl;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.ExerciseRepository;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.PlanRepository;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.SessionRepository;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

/**
 * {@link Configuration} for control services.
 */
@Configuration
public class ControlConfiguration {

    /**
     * Create the {@link ExerciseService} bean.
     *
     * @param exerciseRepository to be used by the bean
     *
     * @return the bean
     */
    @Bean
    public ExerciseService exerciseService ( ExerciseRepository exerciseRepository ) {
        return new ExerciseServiceImpl ( exerciseRepository );
    }

    /**
     * Create the {@link PlanService} bean.
     *
     * @param planRepository to be used by the bean
     *
     * @return the bean
     */
    @Bean
    public PlanService planService ( PlanRepository planRepository ) {
        return new PlanServiceImpl ( planRepository );
    }

    /**
     * Create the {@link SessionService} bean.
     *
     * @param sessionRepository to use
     *
     * @return the bean
     */
    @Bean
    public SessionService sessionService ( SessionRepository sessionRepository ) {
        return new SessionServiceImpl ( sessionRepository );
    }
}
