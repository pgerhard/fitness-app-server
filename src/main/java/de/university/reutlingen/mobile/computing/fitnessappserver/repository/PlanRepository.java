package de.university.reutlingen.mobile.computing.fitnessappserver.repository;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.Plan;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * {@link ReactiveMongoRepository} for {@link Plan}.
 */
public interface PlanRepository extends ReactiveMongoRepository<Plan, ObjectId> {

    // no additional methods

}
