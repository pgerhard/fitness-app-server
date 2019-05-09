package de.university.reutlingen.mobile.computing.fitnessappserver.boundary;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.Exercise;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExerciseManagementService {

    Flux<Exercise> loadAll ();

    Mono<Exercise> findOneByIdentifier ( String identifier );
}
