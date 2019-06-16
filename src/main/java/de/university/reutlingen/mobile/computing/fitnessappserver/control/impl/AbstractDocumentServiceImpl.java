package de.university.reutlingen.mobile.computing.fitnessappserver.control.impl;

import de.university.reutlingen.mobile.computing.fitnessappserver.control.AbstractDocumentService;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.AbstractDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.io.Serializable;

/**
 * An abstract class providing base methods for documents.
 *
 * @param <D> type of document
 * @param <P> type of identifier
 * @param <R> type of repository
 */
public abstract class AbstractDocumentServiceImpl<D extends AbstractDocument<P>, P extends Serializable, R extends ReactiveMongoRepository<D,
        P>> implements AbstractDocumentService<D, P, R> {

    @Override
    public final Mono<D> save ( D document ) {
        doSave ( document );
        return getRepository ().save ( document );
    }

    /**
     * Hook to be used by subclasses to tie into the save process. <br /> This method is always called at the very end of the save process
     *
     * @param document that is currently being saved
     */
    protected void doSave ( D document ) {
        // nothing to do
    }

    ;

}
