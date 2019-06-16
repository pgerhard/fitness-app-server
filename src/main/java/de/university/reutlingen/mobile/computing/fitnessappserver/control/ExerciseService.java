package de.university.reutlingen.mobile.computing.fitnessappserver.control;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.Exercise;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.unit.ExerciseType;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.ExerciseRepository;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.parameter.ExerciseSearchParameter;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ExerciseService extends AbstractDocumentService<Exercise, ObjectId, ExerciseRepository> {

    Mono<Exercise> findOneByNameAndType ( String name, ExerciseType exerciseType );

    Flux<Exercise> findAll ();

    Mono<Exercise> findOneByIdentifier ( ExerciseSearchParameter searchParameter );
}
