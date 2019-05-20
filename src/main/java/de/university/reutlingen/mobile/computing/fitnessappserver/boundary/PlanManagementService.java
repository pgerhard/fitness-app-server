package de.university.reutlingen.mobile.computing.fitnessappserver.boundary;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.Plan;
import reactor.core.publisher.Flux;

public interface PlanManagementService {
    Flux<Plan> findAll ();
}
