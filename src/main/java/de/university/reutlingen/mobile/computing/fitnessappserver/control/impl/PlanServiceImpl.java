package de.university.reutlingen.mobile.computing.fitnessappserver.control.impl;

import de.university.reutlingen.mobile.computing.fitnessappserver.control.PlanService;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Plan;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.PlanRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public class PlanServiceImpl extends AbstractDocumentServiceImpl<Plan, String, PlanRepository> implements PlanService {

    private final PlanRepository planRepository;

    public PlanServiceImpl ( PlanRepository planRepository ) {
        this.planRepository = planRepository;
    }

    @Override
    public Flux<Plan> findAll () {
        return planRepository.findAll ();
    }

    @Override
    protected void doSave ( Plan document ) {
        // nothing to do
    }

    @Override
    public PlanRepository getRepository () {
        return this.planRepository;
    }

    @Override
    public String generateId () {
        return UUID.randomUUID ().toString ();
    }
}
