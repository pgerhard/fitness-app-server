package de.university.reutlingen.mobile.computing.fitnessappserver.configuration;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.converter.StringToGrantedAuthoritiesConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;

/**
 * Configuration to
 */
@Configuration
public class MongoDbConfiguration {

    @Bean
    public MongoCustomConversions customConversions() {
        final ArrayList<Converter<?,?>> converters = new ArrayList<> ();
        converters.add ( new StringToGrantedAuthoritiesConverter () );
        return new MongoCustomConversions ( converters );
    }
}
