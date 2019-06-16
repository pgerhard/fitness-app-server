package de.university.reutlingen.mobile.computing.fitnessappserver.model;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.embeddable.PlannedExercise;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Plan extends AbstractDocument<ObjectId> {

    private String name;

    private List<PlannedExercise> exerciseList;

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public List<PlannedExercise> getExerciseList () {
        return exerciseList;
    }

    public void setExerciseList ( List<PlannedExercise> exerciseList ) {
        this.exerciseList = exerciseList;
    }

    public void addPlannedExercise ( PlannedExercise plannedExercise ) {
        if (exerciseList == null) {
            this.exerciseList = new ArrayList<> ();
        }
        this.exerciseList.add ( plannedExercise );
    }

}
