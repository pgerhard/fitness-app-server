package de.university.reutlingen.mobile.computing.fitnessappserver.session;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.Plan;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Session;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.UserWithPassword;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.embeddable.CompletedExercise;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.embeddable.PlannedExercise;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.CompletedExerciseDto;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public final class CreateSessionHandler {

    private final Session session;

    /**
     * Private constructor to avoid instantiation via constructor. Used by the factory method.
     */
    private CreateSessionHandler () {
        // nothing to do
        this.session = new Session ();
    }

    public Session build () {
        validate ();
        return this.session;
    }

    private void validate () {
        Assert.notNull ( session.getPlan (), "A session must contain a plan" );
        Assert.notNull ( session.getUserWithPassword (), "A session must contain a user" );
//        Assert.isTrue ( !session.getCompletedExercises ().isEmpty (), "A session must contain a list of exercises" );
    }


    /**
     * Get an instance of this handler.
     *
     * @return the handler
     */
    public static CreateSessionHandler instance () {
        return new CreateSessionHandler ();
    }

    public CreateSessionHandler storeUserInSession ( UserWithPassword user ) {
        this.session.setUserWithPassword ( user );
        return this;
    }

    public CreateSessionHandler storePlanInSession ( Plan plan ) {
        this.session.setPlan ( plan );
        return this;
    }

    public CreateSessionHandler storeCompletedExercisesInSession ( List<CompletedExercise> completedExerciseDtos ) {
        this.session.setCompletedExercises ( completedExerciseDtos );
        return this;
    }

    public PlannedExercise retrievePlannedExercise ( String plannedExerciseIdentifier ) {
        return this.session.getPlan ().getExerciseList ().stream ()
                .filter ( plannedExercise -> plannedExercise.getExercise ().getIdentifier ().equals ( UUID.fromString (plannedExerciseIdentifier) ) )
                .findFirst ()
                .orElseThrow ( () -> new IllegalStateException ( "Could not find planned exercise for given identifier" ) );
    }
}
