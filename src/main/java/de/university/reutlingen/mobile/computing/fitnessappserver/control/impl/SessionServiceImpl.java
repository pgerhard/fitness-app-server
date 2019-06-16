package de.university.reutlingen.mobile.computing.fitnessappserver.control.impl;

import de.university.reutlingen.mobile.computing.fitnessappserver.control.SessionService;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Session;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.SessionRepository;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.parameter.SessionSearchParameter;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Default implementation of {@link SessionService}.
 */
public class SessionServiceImpl extends AbstractDocumentServiceImpl<Session, ObjectId, SessionRepository> implements SessionService {

    private final SessionRepository sessionRepository;

    /**
     * Constructor to set all required beans.
     *
     * @param sessionRepository to set
     */
    public SessionServiceImpl ( SessionRepository sessionRepository ) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Flux<Session> findAll () {
        return sessionRepository.findAll ();
    }

    @Override
    public Mono<Session> findOneByIdentifier ( SessionSearchParameter searchParameter ) {
        return sessionRepository.findOne ( Example.of ( buildProbe ( searchParameter ) ) );
    }

    @Override
    public SessionRepository getRepository () {
        return this.sessionRepository;
    }

    private Session buildProbe ( SessionSearchParameter searchParameter ) {
        final Session probe = new Session ();

        if ( searchParameter.identifier != null ) {
            probe.setIdentifier ( UUID.fromString ( searchParameter.identifier ) );
        }

        return probe;
    }

}
