package de.university.reutlingen.mobile.computing.fitnessappserver.boundary.impl;

import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.ExerciseManagementService;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.ExerciseService;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Exercise;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.parameter.ExerciseSearchParameter;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.ExerciseReferenceDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Standard implementation of {@link ExerciseManagementService}.
 */
public class ExerciseManagementServiceImpl implements ExerciseManagementService {

    private final ExerciseService exerciseService;

    public ExerciseManagementServiceImpl ( ExerciseService exerciseService ) {
        this.exerciseService = exerciseService;
    }

    @Override
    public Flux<ExerciseReferenceDto> loadAll () {
        return exerciseService.findAll ().map ( this::mapToDto );
    }

    private ExerciseReferenceDto mapToDto ( Exercise exercise ) {
        return new ExerciseReferenceDto ( exercise.getIdentifier ().toString (), exercise.getName () );
    }

    @Override
    public Mono<Exercise> findOneByIdentifier ( String identifier ) {
        ExerciseSearchParameter searchParameter = new ExerciseSearchParameter ();
        searchParameter.identifier = identifier;
        return exerciseService.findOneByIdentifier ( searchParameter );
    }
}
