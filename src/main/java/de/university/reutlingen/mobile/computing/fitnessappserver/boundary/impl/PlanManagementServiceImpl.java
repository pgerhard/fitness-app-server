package de.university.reutlingen.mobile.computing.fitnessappserver.boundary.impl;

import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.PlanManagementService;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.PlanService;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Plan;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.parameter.PlanSearchParameter;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.ExerciseDetailDto;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.PlanDto;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.PlanReferenceDto;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.PlannedExerciseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

public class PlanManagementServiceImpl implements PlanManagementService {

    private final PlanService planService;

    public PlanManagementServiceImpl ( PlanService planService ) {
        this.planService = planService;
    }

    @Override
    public Flux<PlanReferenceDto> findAll () {
        return planService.findAll ().map ( this::mapPlanToPlanReference );
    }

    @Override
    public Mono<PlanDto> findOneByIdentifier ( String identifier ) {
        final PlanSearchParameter planSearchParameter = new PlanSearchParameter ();
        planSearchParameter.identifier = identifier;

        // Map to DTO
        return planService.findOneBySearchParameter ( planSearchParameter ).map ( plan -> convertToDto ( plan ) );
    }

    private PlanDto convertToDto ( Plan plan ) {
        final PlanDto planDto = new PlanDto ();
        planDto.identifier = plan.getIdentifier ().toString ();
        planDto.exerciseList = plan.getExerciseList ().stream ()
                .map ( plannedExercise -> {
                    final ExerciseDetailDto exerciseDetailDto = new ExerciseDetailDto ();
                    exerciseDetailDto.name = plannedExercise.getExercise ().getName ();
                    exerciseDetailDto.description = plannedExercise.getExercise ().getDescription ();

                    final PlannedExerciseDto plannedExerciseDto = new PlannedExerciseDto ();
                    plannedExerciseDto.aimUnit = plannedExercise.getAimUnit ();
                    plannedExerciseDto.breakDurationInSeconds = plannedExercise.getBreakDurationInSeconds ();
                    plannedExerciseDto.exercise = exerciseDetailDto;
                    plannedExerciseDto.intensityLevel = plannedExercise.getIntensityLevel ();
                    plannedExerciseDto.intensityUnit = plannedExercise.getIntensityUnit ();
                    plannedExerciseDto.numOfRepetitions = plannedExercise.getNumOfRepetitions ();
                    plannedExerciseDto.numOfSets = plannedExercise.getNumOfSets ();
                    plannedExerciseDto.repetitionUnit = plannedExercise.getRepetitionUnit ();
                    return plannedExerciseDto;
                } )
                .collect ( Collectors.toList () );

        return planDto;
    }

    private PlanReferenceDto mapPlanToPlanReference ( Plan plan ) {
        return new PlanReferenceDto ( plan.getName (), plan.getIdentifier ().toString () );
    }

}
