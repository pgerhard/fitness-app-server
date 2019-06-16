package de.university.reutlingen.mobile.computing.fitnessappserver.control;

import de.university.reutlingen.mobile.computing.fitnessappserver.model.Exercise;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith ( SpringRunner.class )
@SpringBootTest
public class ExerciseServiceIntegrationTest {

    @Autowired
    private ExerciseService exerciseService;

    @Test
    public void verifySave () {
        final Exercise exercise = new Exercise ();
        exercise.setName ( "Test exercise" );

        final Mono<Exercise> save = exerciseService.save ( exercise );
        save.subscribe ( exercise1 -> {
            assertThat ( exercise1.getId (), notNullValue () );
            assertThat ( exercise1.getId (), isA ( ObjectId.class ) );

            assertThat ( exercise1.getCreatedBy (), notNullValue () );
            assertThat ( exercise1.getCreatedBy (), isA ( String.class ) );

            assertThat ( exercise1.getCreatedDate (), notNullValue () );
            assertThat ( exercise1.getCreatedDate (), isA ( LocalDateTime.class ) );

            assertThat ( exercise1.getLastModifiedBy (), notNullValue () );
            assertThat ( exercise1.getLastModifiedBy (), isA ( String.class ) );

            assertThat ( exercise1.getLastModifiedDate (), notNullValue () );
            assertThat ( exercise1.getLastModifiedDate (), isA ( LocalDateTime.class ) );

            assertThat ( exercise1.getName (), not ( isEmptyOrNullString () ) );
            assertThat ( exercise1.getName (), is ( "Test exercise" ) );
        } );
    }

}
