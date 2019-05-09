package de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1;

import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.ExerciseManagementService;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Exercise;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/fitness-app/api/v1/exercises")
public class ExerciseControllerV1 {

    private final ExerciseManagementService delegate;

    public ExerciseControllerV1 ( ExerciseManagementService delegate ) {
        this.delegate = delegate;
    }

    @GetMapping
    public Flux<Exercise> loadAll(){
        return delegate.loadAll();
    }

    @GetMapping (path = "/{identifier}")
    public Mono<Exercise> loadOneByIdentifier(@PathVariable("identifier") String identifier) {
        return delegate.findOneByIdentifier ( identifier );
    }
}
