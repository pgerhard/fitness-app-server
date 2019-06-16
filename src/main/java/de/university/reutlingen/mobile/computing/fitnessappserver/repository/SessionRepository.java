package de.university.reutlingen.mobile.computing.fitnessappserver.repository;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.Plan;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Session;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * {@link ReactiveMongoRepository} for {@link Session Sessions}.
 */
public interface SessionRepository extends ReactiveMongoRepository<Session, ObjectId> {

    // no additional methods

}
