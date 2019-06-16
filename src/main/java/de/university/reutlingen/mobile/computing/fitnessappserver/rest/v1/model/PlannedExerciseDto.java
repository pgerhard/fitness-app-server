package de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.unit.AimUnit;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.unit.IntensityUnit;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.unit.RepetitionUnit;

public class PlannedExerciseDto {

    public ExerciseReferenceDto exercise;

    public Integer numOfRepetitions;

    public RepetitionUnit repetitionUnit;

    public Integer numOfSets;

    public Integer breakDurationInSeconds;

    public Integer intensityLevel;

    public IntensityUnit intensityUnit;

    public AimUnit aimUnit;
}
