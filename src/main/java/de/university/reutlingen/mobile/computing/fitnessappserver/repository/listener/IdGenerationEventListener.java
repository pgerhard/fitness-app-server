package de.university.reutlingen.mobile.computing.fitnessappserver.repository.listener;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.AbstractDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.util.IdGenerator;

public class IdGenerationEventListener implements ApplicationListener<BeforeConvertEvent<Object>> {

    private static final Logger LOGGER = LoggerFactory.getLogger ( IdGenerationEventListener.class );

    private final IdGenerator idGenerator;

    public IdGenerationEventListener ( IdGenerator idGenerator ) {
        this.idGenerator = idGenerator;
    }


    @Override
    public void onApplicationEvent ( BeforeConvertEvent<Object> event ) {
        LOGGER.info ( "Received before convert event - " + event.toString () );
        if ( AbstractDocument.class.isAssignableFrom ( event.getSource ().getClass () ) ) {
            LOGGER.info ( "Before convert event contained AbstractDocument" );
            final AbstractDocument eventSource = (AbstractDocument) event.getSource ();

            if ( eventSource.isNew () && eventSource.getIdentifier () == null ) {
                eventSource.setIdentifier ( idGenerator.generateId () );
                LOGGER.info ( String.format ( "Generated identifier: %s", eventSource.getIdentifier () ) );
            }

        }

    }
}
