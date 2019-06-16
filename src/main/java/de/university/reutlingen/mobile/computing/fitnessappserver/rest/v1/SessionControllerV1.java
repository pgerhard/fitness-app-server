package de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1;

import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.SessionManagementService;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Session;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.SessionDto;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.SessionReferenceDto;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * Rest controller providing endpoints to manage sessions.
 */
@RestController
@RequestMapping ( "/api/v1/sessions" )
public class SessionControllerV1 {

    private final SessionManagementService delegate;

    public SessionControllerV1 ( SessionManagementService delegate ) {
        this.delegate = delegate;
    }

    /**
     * Load references to all sessions.
     *
     * @return references to all sessions.
     */
    @GetMapping
    public Flux<SessionReferenceDto> loadAll () {
        return delegate.loadAll ();
    }

    @GetMapping ( path = "/{identifier}" )
    public Mono<Session> loadOneByIdentifier ( @PathVariable ( "identifier" ) String identifier ) {
        return delegate.loadOneByIdentifier ( identifier );
    }

    @PostMapping
    public Mono<ResponseEntity<String>> recordSession ( @RequestBody SessionDto session ) {
        return delegate.storeSession ( session )
                .map ( sessionReferenceDto -> sessionReferenceDto.identifier )
                .map ( this::createCreatedResponse );
    }

    /**
     * Create a response entity containing an URL pointing to the created resource.
     *
     * @param identifier of the created resource
     *
     * @return the response entity
     */
    private ResponseEntity<String> createCreatedResponse ( String identifier ) {
        final URI location = UriComponentsBuilder
                .fromHttpUrl ( "http://localhost:8090/fitness-app/api/v1/{identifier}" )
                .buildAndExpand ( identifier )
                .toUri ();

        return ResponseEntity.created ( location ).build ();
    }
}
