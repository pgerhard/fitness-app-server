package de.university.reutlingen.mobile.computing.fitnessappserver.control;

import de.university.reutlingen.mobile.computing.fitnessappserver.control.impl.AbstractDocumentServiceImpl;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.UserWithPassword;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.embeddable.User;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.UserRepository;
import de.university.reutlingen.mobile.computing.fitnessappserver.security.FitnessAppServerAuthorities;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

import java.util.Collections;

public class UserService extends AbstractDocumentServiceImpl<UserWithPassword, ObjectId, UserRepository> implements ReactiveUserDetailsService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for required spring beans.
     *
     * @param userRepository to set
     * @param passwordEncoder to set
     */
    public UserService ( UserRepository userRepository, PasswordEncoder passwordEncoder ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void doSave ( UserWithPassword document ) {
        document.setPassword ( passwordEncoder.encode ( document.getPassword () ) );
    }

    public Mono<UserWithPassword> loadUserByUsername( String username) {
        final UserWithPassword probe = new UserWithPassword ();
        final User user = new User ();
        user.setUsername ( username );
        probe.setUser ( user );
        return this.userRepository.findOne ( Example.of ( probe ) );
    }

    @Override
    public Mono<UserDetails> findByUsername ( String username ) {
        final UserWithPassword probe = new UserWithPassword ();
        final User probeUserDetails = new User ();
        probeUserDetails.setUsername ( username );
        probe.setUser ( probeUserDetails );

        return this.userRepository.findOne ( Example.of ( probe ) ).map ( user -> user );
    }

    @Override
    public UserRepository getRepository () {
        return userRepository;
    }
}
