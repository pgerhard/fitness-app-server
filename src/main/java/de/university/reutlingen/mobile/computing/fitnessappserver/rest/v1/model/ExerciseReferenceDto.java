package de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model;

/**
 * Reference DTO for {@link de.university.reutlingen.mobile.computing.fitnessappserver.model.Exercise}.
 * <pre />
 * All fields should be marked public, this is done on purpose but only in DTOs.
 */
public class ExerciseReferenceDto {

    public String identifier;

    public String name;

    public ExerciseReferenceDto () {
        // nothing to do
    }

    public ExerciseReferenceDto ( String identifier, String name ) {
        this.identifier = identifier;
        this.name = name;
    }
}
