package de.university.reutlingen.mobile.computing.fitnessappserver.control.impl;

import de.university.reutlingen.mobile.computing.fitnessappserver.control.ExerciseService;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Exercise;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.unit.ExerciseType;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.ExerciseRepository;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.parameter.ExerciseSearchParameter;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class ExerciseServiceImpl extends AbstractDocumentServiceImpl<Exercise, ObjectId, ExerciseRepository> implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl ( ExerciseRepository exerciseRepository ) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Mono<Exercise> findOneByNameAndType ( String name, ExerciseType exerciseType ) {
        return getRepository ().findOne ( Example.of ( new Exercise ( name, null, exerciseType ) ) );
    }

    ;

    @Override
    public Mono<Exercise> findOneByIdentifier ( ExerciseSearchParameter searchParameter ) {
        return getRepository ().findOne ( Example.of ( buildProbe ( searchParameter ) ) );
    }

    @Override
    public Flux<Exercise> findAll () {
        return this.exerciseRepository.findAll ();
    }

    @Override
    public ExerciseRepository getRepository () {
        return this.exerciseRepository;
    }

    private Exercise buildProbe ( ExerciseSearchParameter searchParameter ) {
        final Exercise probe = new Exercise ();

        if ( searchParameter.identifier != null ) {
            probe.setIdentifier ( UUID.fromString ( searchParameter.identifier ) );
        }
        return probe;
    }
}
