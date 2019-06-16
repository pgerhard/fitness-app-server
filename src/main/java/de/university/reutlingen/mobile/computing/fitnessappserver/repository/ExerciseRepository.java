package de.university.reutlingen.mobile.computing.fitnessappserver.repository;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.Exercise;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

/**
 * {@link ReactiveMongoRepository} for {@link Exercise}.
 */
public interface ExerciseRepository extends ReactiveMongoRepository<Exercise, ObjectId> {

    // no additional methods

}
