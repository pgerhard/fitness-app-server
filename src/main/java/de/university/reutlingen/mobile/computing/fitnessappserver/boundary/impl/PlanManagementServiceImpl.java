package de.university.reutlingen.mobile.computing.fitnessappserver.boundary.impl;

import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.PlanManagementService;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.PlanService;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Plan;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.PlanReferenceDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PlanManagementServiceImpl implements PlanManagementService {

    private final PlanService planService;

    public PlanManagementServiceImpl ( PlanService planService ) {
        this.planService = planService;
    }

    @Override
    public Flux<PlanReferenceDto> findAll () {
        return planService.findAll ().map ( this::mapPlanToPlanReference);
    }

    private PlanReferenceDto mapPlanToPlanReference ( Plan plan ) {
        return new PlanReferenceDto (plan.getId (), plan.getId ());
    }

    @Override
    public Mono<Plan> findOneByIdentifier ( String identifier ) {
        return planService.findOneByIdentifier(identifier);
    }
}
