package de.university.reutlingen.mobile.computing.fitnessappserver.repository;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.Plan;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PlanRepository extends ReactiveMongoRepository<Plan, String> {

    // no additional methods

}
