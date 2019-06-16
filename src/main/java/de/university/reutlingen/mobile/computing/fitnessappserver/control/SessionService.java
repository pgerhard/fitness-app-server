package de.university.reutlingen.mobile.computing.fitnessappserver.control;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.Session;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.SessionRepository;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.parameter.SessionSearchParameter;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Control service for {@link Session Sessions}.
 */
public interface SessionService extends AbstractDocumentService<Session, ObjectId, SessionRepository> {

    /**
     * Load all sessions.
     *
     * @return stream of all sessions.
     */
    Flux<Session> findAll ();

    Mono<Session> findOneByIdentifier ( SessionSearchParameter searchParameter );


}
