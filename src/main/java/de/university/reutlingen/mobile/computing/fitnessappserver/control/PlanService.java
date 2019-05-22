package de.university.reutlingen.mobile.computing.fitnessappserver.control;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.Plan;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.PlanRepository;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.parameter.PlanSearchParameter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlanService extends AbstractDocumentService<Plan, String, PlanRepository> {

    Flux<Plan> findAll();

    Mono<Plan> findOneBySearchParameter ( PlanSearchParameter planSearchParameter );
}
