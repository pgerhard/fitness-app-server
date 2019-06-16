package de.university.reutlingen.mobile.computing.fitnessappserver.boundary;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.Session;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.SessionDto;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.SessionReferenceDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Management service for {@link de.university.reutlingen.mobile.computing.fitnessappserver.model.Session Sessions}.
 */
public interface SessionManagementService {

    /**
     * Load references to all {@link de.university.reutlingen.mobile.computing.fitnessappserver.model.Session Sessions}.
     *
     * @return a stream of {@link SessionReferenceDto SessionReferenceDtos}
     */
    Flux<SessionReferenceDto> loadAll ();

    /**
     * Load a session by identifier.
     *
     * @param identifier of the session to load
     *
     * @return the loaded session
     */
    Mono<Session> loadOneByIdentifier ( String identifier );

    /**
     * Store the given session and return a reference to the stored result.
     * @param session                                                      to store
     * @return                                                                     a reference to the stored session
     */
    Mono<SessionReferenceDto> storeSession ( SessionDto session );
}
