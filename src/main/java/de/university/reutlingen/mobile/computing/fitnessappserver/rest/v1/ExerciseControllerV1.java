package de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1;

import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.ExerciseManagementService;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Exercise;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.ExerciseReferenceDto;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Endpoint for exercises.
 */
@RestController
@RequestMapping(path = "/api/v1/exercises")
public class ExerciseControllerV1 {

    private final ExerciseManagementService delegate;

    /**
     * Constructor for required beans.
     * @param delegate that this controller delegates its actions to
     */
    public ExerciseControllerV1 ( ExerciseManagementService delegate ) {
        this.delegate = delegate;
    }

    /**
     * Get references to all exercises.
     * @return a list of exercise references
     */
    @GetMapping
    public Flux<ExerciseReferenceDto> loadAll(){
        return delegate.loadAll();
    }

    /**
     * Get a single exercise.
     * @param identifier of the exercise to load
     * @return the exercise.
     */
    @GetMapping (path = "/{identifier}")
    public Mono<Exercise> loadOneByIdentifier(@PathVariable("identifier") String identifier) {
        return delegate.findOneByIdentifier ( identifier );
    }
}
