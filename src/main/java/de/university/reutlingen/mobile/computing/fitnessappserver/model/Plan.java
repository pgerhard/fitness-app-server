package de.university.reutlingen.mobile.computing.fitnessappserver.model;

import java.util.ArrayList;
import java.util.List;

public class Plan extends AbstractDocument<String> {

    private List<PlannedExercise> exerciseList;

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
