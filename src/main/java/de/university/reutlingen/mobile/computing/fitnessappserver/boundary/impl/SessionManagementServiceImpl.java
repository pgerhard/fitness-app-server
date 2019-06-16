package de.university.reutlingen.mobile.computing.fitnessappserver.boundary.impl;

import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.SessionManagementService;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.ExerciseService;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.PlanService;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.SessionService;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.UserService;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Plan;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Session;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.UserWithPassword;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.embeddable.CompletedExercise;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.embeddable.PlannedExercise;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.parameter.ExerciseSearchParameter;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.parameter.PlanSearchParameter;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.parameter.SessionSearchParameter;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.CompletedExerciseDto;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.SessionDto;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.SessionReferenceDto;
import de.university.reutlingen.mobile.computing.fitnessappserver.session.CreateSessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Default implementation of {@link SessionManagementService}.
 */
public class SessionManagementServiceImpl implements SessionManagementService {

    private final SessionService sessionService;

    @Autowired
    private PlanService planService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExerciseService exerciseService;

    public SessionManagementServiceImpl ( SessionService sessionService ) {
        this.sessionService = sessionService;
    }

    @Override
    public Flux<SessionReferenceDto> loadAll () {
        return sessionService.findAll ().map ( SessionManagementServiceImpl::mapToDto );
    }

    @Override
    public Mono<Session> loadOneByIdentifier ( String identifier ) {
        final SessionSearchParameter searchParameter = new SessionSearchParameter ();
        searchParameter.identifier = identifier;
        return sessionService.findOneByIdentifier ( searchParameter );
    }

    @Override
    public Mono<SessionReferenceDto> storeSession ( SessionDto sessionDto ) {
        return Mono.just ( CreateSessionHandler.instance () )
                // Load the user and store it in the session
                .flatMap ( createSessionHandler ->
                        Mono.defer ( ReactiveSecurityContextHolder::getContext ).map ( SecurityContext::getAuthentication )
                                .flatMap ( authentication -> userService.loadUserByUsername ( authentication.getName () ) )
                                .switchIfEmpty ( Mono.defer ( () -> Mono.error ( new IllegalStateException ( "User not found" ) ) ) )
                                .map ( createSessionHandler::storeUserInSession )
                )
                // Load the plan and store it in the session
                .flatMap ( createSessionHandler ->
                        Mono.defer (
                                () -> {
                                    final PlanSearchParameter planSearchParameter = new PlanSearchParameter ();
                                    planSearchParameter.identifier = sessionDto.plan.identifier;
                                    return planService.findOneBySearchParameter ( planSearchParameter );
                                } )
                                .switchIfEmpty ( Mono.defer ( () -> Mono.error ( new IllegalStateException ( "Plan not found" ) ) ) )
                                .map ( createSessionHandler::storePlanInSession ) )
                // Map the completed exercises
                .map ( createSessionHandler -> {
                    final List<CompletedExercise> completedExercises = sessionDto.completedExercises.stream ()
                            // Retrieve the planned exercise identifiers
                            .map ( completedExerciseDto -> completedExerciseDto.plannedExercise )
                            .map ( plannedExerciseDto -> {
                                PlannedExercise plannedExercise = createSessionHandler.retrievePlannedExercise ( plannedExerciseDto.exercise.identifier );
                                final CompletedExercise completedExercise = new CompletedExercise ();
                                completedExercise.setPlannedExercise ( plannedExercise );
                                completedExercise.setBreakDurationInSeconds ( plannedExerciseDto.breakDurationInSeconds );
                                completedExercise.setIntensityLevel ( plannedExerciseDto.intensityLevel );
                                completedExercise.setNumOfRepetitions ( plannedExerciseDto.numOfRepetitions );
                                completedExercise.setNumOfSets ( plannedExerciseDto.numOfSets );
                                return completedExercise;
                            } )
                            .collect ( Collectors.toList () );
                    return createSessionHandler.storeCompletedExercisesInSession ( completedExercises );
                } )
                .map ( CreateSessionHandler::build )
                // Store new completed exercises
//                .map ( session ->  )
                .flatMap ( this.sessionService::save )
                .map ( SessionManagementServiceImpl::mapToDto )
                .doOnError ( Throwable::printStackTrace );
    }

    private CompletedExercise convertToCompletedExercise ( CompletedExerciseDto completedExerciseDto ) {

        Mono.fromSupplier ( () -> {
            final ExerciseSearchParameter searchParameter = new ExerciseSearchParameter ();
            searchParameter.identifier = completedExerciseDto.plannedExercise.exercise.identifier;
            return searchParameter;
        } )
                .flatMap ( searchParameter -> exerciseService.findOneByIdentifier ( searchParameter ) )
                .map ( exercise -> {
                    final CompletedExercise completedExercise = new CompletedExercise ();


                    return completedExercise;
                } );

        final ExerciseSearchParameter searchParameter = new ExerciseSearchParameter ();
        searchParameter.identifier = completedExerciseDto.plannedExercise.exercise.identifier;
        exerciseService.findOneByIdentifier ( searchParameter );
        return new CompletedExercise ();
    }

    private static SessionReferenceDto mapToDto ( Session session ) {
        final SessionReferenceDto sessionReferenceDto = new SessionReferenceDto ();
        sessionReferenceDto.identifier = session.getIdentifier ().toString ();
        sessionReferenceDto.name = String.format ( "%s - %s", session.getPlan ().getName (), session.getCreatedDate () );
        return sessionReferenceDto;
    }
}
