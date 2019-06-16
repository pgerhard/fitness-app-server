package de.university.reutlingen.mobile.computing.fitnessappserver.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties ( prefix = "fitness-app-server" )
public class FitnessAppProperties {

    private boolean productionMode;

    private List<String> allowedHeaders;

    private List<String> allowedMethods;

    private List<String> allowedOrigins;

    public boolean isProductionMode () {
        return productionMode;
    }

    public void setProductionMode ( boolean productionMode ) {
        this.productionMode = productionMode;
    }

    public List<String> getAllowedHeaders () {
        return allowedHeaders;
    }

    public void setAllowedHeaders ( List<String> allowedHeaders ) {
        this.allowedHeaders = allowedHeaders;
    }

    public List<String> getAllowedMethods () {
        return allowedMethods;
    }

    public void setAllowedMethods ( List<String> allowedMethods ) {
        this.allowedMethods = allowedMethods;
    }

    public List<String> getAllowedOrigins () {
        return allowedOrigins;
    }

    public void setAllowedOrigins ( List<String> allowedOrigins ) {
        this.allowedOrigins = allowedOrigins;
    }
}
