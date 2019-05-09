package de.university.reutlingen.mobile.computing.fitnessappserver.control.impl;

import de.university.reutlingen.mobile.computing.fitnessappserver.control.ExerciseService;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Exercise;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.ExerciseType;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.ExerciseRepository;
import org.springframework.data.domain.Example;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class ExerciseServiceImpl extends AbstractDocumentServiceImpl<Exercise, String, ExerciseRepository> implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl ( ExerciseRepository exerciseRepository ) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Mono<Exercise> findOneByNameAndType ( String name, ExerciseType exerciseType ) {
        return getRepository ().findOne ( Example.of ( new Exercise ( name, null, exerciseType ) ) );
    };

    @Override
    public Mono<Exercise> findOneByIdentifier ( String identifier ) {
        final Exercise probe = new Exercise ();
        probe.setId ( identifier );
        return getRepository ().findOne ( Example.of ( probe ) );
    }

    @Override
    protected void doSave ( Exercise document ) {
        // nothing to do
    }

    @Override
    public ExerciseRepository getRepository () {
        return this.exerciseRepository;
    }

    @Override
    public String generateId () {
        return UUID.randomUUID ().toString ();
    }

    @Override
    public Flux<Exercise> findAll () {
        return this.exerciseRepository.findAll ();
    }
}
