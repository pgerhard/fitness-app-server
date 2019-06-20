package de.university.reutlingen.mobile.computing.fitnessappserver.boundary;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.Plan;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.PlanDto;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.PlanReferenceDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlanManagementService {
    Flux<PlanReferenceDto> findAll ();

    Mono<PlanDto> findOneByIdentifier( String identifier);
}
