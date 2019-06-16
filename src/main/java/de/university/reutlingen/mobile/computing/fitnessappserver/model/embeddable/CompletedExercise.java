package de.university.reutlingen.mobile.computing.fitnessappserver.model.embeddable;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.AbstractDocument;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class CompletedExercise extends AbstractDocument<String> {

    private PlannedExercise plannedExercise;

    private Integer numOfRepetitions;

    private Integer numOfSets;

    private Integer breakDurationInSeconds;

    private Integer intensityLevel;

    public PlannedExercise getPlannedExercise () {
        return plannedExercise;
    }

    public void setPlannedExercise ( PlannedExercise plannedExercise ) {
        this.plannedExercise = plannedExercise;
    }

    public Integer getNumOfRepetitions () {
        return numOfRepetitions;
    }

    public void setNumOfRepetitions ( Integer numOfRepetitions ) {
        this.numOfRepetitions = numOfRepetitions;
    }

    public Integer getNumOfSets () {
        return numOfSets;
    }

    public void setNumOfSets ( Integer numOfSets ) {
        this.numOfSets = numOfSets;
    }

    public Integer getBreakDurationInSeconds () {
        return breakDurationInSeconds;
    }

    public void setBreakDurationInSeconds ( Integer breakDurationInSeconds ) {
        this.breakDurationInSeconds = breakDurationInSeconds;
    }

    public Integer getIntensityLevel () {
        return intensityLevel;
    }

    public void setIntensityLevel ( Integer intensityLevel ) {
        this.intensityLevel = intensityLevel;
    }
}
