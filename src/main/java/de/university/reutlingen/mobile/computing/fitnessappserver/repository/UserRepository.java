package de.university.reutlingen.mobile.computing.fitnessappserver.repository;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.UserWithPassword;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<UserWithPassword, ObjectId> {

    // no additional methods

}
