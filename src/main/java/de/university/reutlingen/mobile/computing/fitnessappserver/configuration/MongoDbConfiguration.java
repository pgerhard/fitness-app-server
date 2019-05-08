package de.university.reutlingen.mobile.computing.fitnessappserver.configuration;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * Configuration to
 */
@EnableReactiveMongoRepositories
public class MongoDbConfiguration extends AbstractReactiveMongoConfiguration {


    @Bean
    @Override
    public MongoClient reactiveMongoClient () {
        return MongoClients.create ();
    }

    @Override
    protected String getDatabaseName () {
        return "fitness-app";
    }
}
