package de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model;

public class PlanReferenceDto {

    private String name;

    private String identifier;

    public PlanReferenceDto ( String name, String identifier ) {
        this.name = name;
        this.identifier = identifier;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getIdentifier () {
        return identifier;
    }

    public void setIdentifier ( String identifier ) {
        this.identifier = identifier;
    }
}
