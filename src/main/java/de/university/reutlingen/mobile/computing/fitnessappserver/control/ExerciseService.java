package de.university.reutlingen.mobile.computing.fitnessappserver.control;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.Exercise;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.ExerciseType;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.ExerciseRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExerciseService extends AbstractDocumentService<Exercise, String, ExerciseRepository> {

    Mono<Exercise> findOneByNameAndType ( String name, ExerciseType exerciseType );

    Flux<Exercise> findAll ();

    Mono<Exercise> findOneByIdentifier ( String identifier );
}
