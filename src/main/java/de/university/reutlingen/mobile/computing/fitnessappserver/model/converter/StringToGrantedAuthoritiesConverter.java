package de.university.reutlingen.mobile.computing.fitnessappserver.model.converter;

import de.university.reutlingen.mobile.computing.fitnessappserver.security.FitnessAppServerAuthorities;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.security.core.GrantedAuthority;

@ReadingConverter
public class StringToGrantedAuthoritiesConverter implements Converter<String, GrantedAuthority> {

    @Override
    public GrantedAuthority convert ( String source ) {
        return FitnessAppServerAuthorities.valueOf ( source );
    }
}
