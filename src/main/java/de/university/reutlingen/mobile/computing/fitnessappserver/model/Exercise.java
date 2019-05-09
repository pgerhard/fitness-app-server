package de.university.reutlingen.mobile.computing.fitnessappserver.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Exercise extends AbstractDocument<String> {

    private String name;

    private String description;

    private ExerciseType exerciseType;

    /**
     * Default constructor.
     */
    public Exercise () {
    }

    /**
     * Constructor with all fields to allow querying by example
     * @param name to set
     * @param description to set
     * @param exerciseType to set
     */
    public Exercise ( String name, String description, ExerciseType exerciseType ) {
        this.name = name;
        this.description = description;
        this.exerciseType = exerciseType;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription ( String description ) {
        this.description = description;
    }

    public ExerciseType getExerciseType () {
        return exerciseType;
    }

    public void setExerciseType ( ExerciseType exerciseType ) {
        this.exerciseType = exerciseType;
    }

}
