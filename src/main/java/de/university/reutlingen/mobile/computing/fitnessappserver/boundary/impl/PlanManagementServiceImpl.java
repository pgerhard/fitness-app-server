package de.university.reutlingen.mobile.computing.fitnessappserver.boundary.impl;

import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.PlanManagementService;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.PlanService;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Plan;
import reactor.core.publisher.Flux;

public class PlanManagementServiceImpl implements PlanManagementService {

    private final PlanService planService;

    public PlanManagementServiceImpl ( PlanService planService ) {
        this.planService = planService;
    }

    @Override
    public Flux<Plan> findAll () {
        return planService.findAll ();
    }
}
