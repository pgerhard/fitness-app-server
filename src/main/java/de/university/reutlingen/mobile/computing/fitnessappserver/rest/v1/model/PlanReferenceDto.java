package de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model;

/**
 * Reference DTO for {@link de.university.reutlingen.mobile.computing.fitnessappserver.model.Plan}.
 * <pre />
 * All fields should be marked public, this is done on purpose but only in DTOs.
 */
public class PlanReferenceDto {

    public String name;

    public String identifier;

    public PlanReferenceDto () {
        // nothing to do
    }

    public PlanReferenceDto ( String name, String identifier ) {
        this.name = name;
        this.identifier = identifier;
    }

}
