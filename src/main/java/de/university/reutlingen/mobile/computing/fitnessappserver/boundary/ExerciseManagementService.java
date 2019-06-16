package de.university.reutlingen.mobile.computing.fitnessappserver.boundary;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.Exercise;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.ExerciseReferenceDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Boundary service to for uses cases related to Exercises.
 */
public interface ExerciseManagementService {

    /**
     * Load a reference object for all exercises.
     * @return a list of all available exercises
     */
    Flux<ExerciseReferenceDto> loadAll ();

    /**
     * Search for a single exercise via its identifier.
     * @param identifier of the exercise to find
     * @return the exercise
     */
    Mono<Exercise> findOneByIdentifier ( String identifier );
}
