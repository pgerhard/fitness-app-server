package de.university.reutlingen.mobile.computing.fitnessappserver.model;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.embeddable.CompletedExercise;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Document to store information about a session.
 */
@Document
public class Session extends AbstractDocument<ObjectId> {

    @DBRef
    private UserWithPassword userWithPassword;

    @DBRef
    private Plan plan;

    private List<CompletedExercise> completedExercises;

    public Session () {
        // nothing to do
    }

    public UserWithPassword getUserWithPassword () {
        return userWithPassword;
    }

    public void setUserWithPassword ( UserWithPassword userWithPassword ) {
        this.userWithPassword = userWithPassword;
    }

    public Plan getPlan () {
        return plan;
    }

    public void setPlan ( Plan plan ) {
        this.plan = plan;
    }

    public List<CompletedExercise> getCompletedExercises () {
        return completedExercises;
    }

    public void setCompletedExercises ( List<CompletedExercise> completedExercises ) {
        this.completedExercises = completedExercises;
    }
}
