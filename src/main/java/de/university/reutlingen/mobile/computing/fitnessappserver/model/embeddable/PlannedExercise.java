package de.university.reutlingen.mobile.computing.fitnessappserver.model.embeddable;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.Exercise;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.unit.AimUnit;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.unit.IntensityUnit;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.unit.RepetitionUnit;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class PlannedExercise {

    @DBRef
    private Exercise exercise;

    private Integer numOfRepetitions;

    private RepetitionUnit repetitionUnit;

    private Integer numOfSets;

    private Integer breakDurationInSeconds;

    private Integer intensityLevel;

    private IntensityUnit intensityUnit;

    private AimUnit aimUnit;

    public Exercise getExercise () {
        return exercise;
    }

    public void setExercise ( Exercise exercise ) {
        this.exercise = exercise;
    }

    public Integer getNumOfRepetitions () {
        return numOfRepetitions;
    }

    public void setNumOfRepetitions ( Integer numOfRepetitions ) {
        this.numOfRepetitions = numOfRepetitions;
    }

    public RepetitionUnit getRepetitionUnit () {
        return repetitionUnit;
    }

    public void setRepetitionUnit ( RepetitionUnit repetitionUnit ) {
        this.repetitionUnit = repetitionUnit;
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

    public IntensityUnit getIntensityUnit () {
        return intensityUnit;
    }

    public void setIntensityUnit ( IntensityUnit intensityUnit ) {
        this.intensityUnit = intensityUnit;
    }

    public AimUnit getAimUnit () {
        return aimUnit;
    }

    public void setAimUnit ( AimUnit aimUnit ) {
        this.aimUnit = aimUnit;
    }
}
