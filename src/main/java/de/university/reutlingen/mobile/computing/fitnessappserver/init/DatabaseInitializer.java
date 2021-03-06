package de.university.reutlingen.mobile.computing.fitnessappserver.init;


import de.university.reutlingen.mobile.computing.fitnessappserver.control.ExerciseService;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.PlanService;
import de.university.reutlingen.mobile.computing.fitnessappserver.control.UserService;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.*;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.embeddable.PlannedExercise;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.embeddable.User;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.unit.AimUnit;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.unit.ExerciseType;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.unit.IntensityUnit;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.unit.RepetitionUnit;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.parameter.PlanSearchParameter;
import de.university.reutlingen.mobile.computing.fitnessappserver.security.FitnessAppServerAuthorities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * Initializer for the database.
 */
public class DatabaseInitializer extends AbstractInitializationService {

    private static final Logger LOGGER = LoggerFactory.getLogger ( DatabaseInitializer.class );
    private static final String BENCH_PRESS_NAME = "Bench Press";
    private static final String MILITARY_PRESS_NAME = "Military Press";
    private static final String SQUAT_EXERCISE_NAME = "Squat";
    private static final String LAT_PULLDOWNS_NAME = "Lat Pulldowns";
    private static final String DUMBBELL_CURLS_NAME = "Dumbbell Curls";
    private static final String DECLINE_SIT_UP_NAME = "Decline Sit Up";
    private static final String FULL_BODY_TRAINING_PLAN_NAME = "Basic full-body workout";

    private final ExerciseService exerciseService;
    private final PlanService planService;

    @Autowired
    private UserService userService;

    /**
     * Constructor for required fields.
     *
     * @param isProductionMode flag showing if application is running in production mode.
     */
    public DatabaseInitializer ( boolean isProductionMode, ExerciseService exerciseService, PlanService planService ) {
        super ( isProductionMode );

        this.exerciseService = exerciseService;
        this.planService = planService;
    }

    @Override
    protected void createProductionData () {
        LOGGER.info ( "No production data to create" );
    }

    @Override
    protected void createDummyData () {
        createOrUpdateSystemExercises ()
                .collectMap ( Exercise::getName, exercise -> exercise )
                .flatMap ( this::createOrLoadFullBodyTrainingPlan )
                .doOnError ( Throwable::printStackTrace )
                .then ( this.createOrLoadDefaultUser () )
                .then ( this.createOrLoadCarstenHummel () )
                .then ( this.createOrLoadYeeunPark () )
                .then ( this.createOrLoadErikaMueller () )
                .subscribe ();
    }

    private void logExerciseInfo ( @NotNull Exercise exercise ) {
        LOGGER.debug ( String.format ( "Exercise created:\n Name: %s\n ID: %s", exercise.getName (), exercise.getIdentifier () ) );
    }

    private void logPlanInfo ( Plan plan ) {
        LOGGER.debug ( String.format ( "Plan created:\n Name: %s\n ID: %s", plan.getName (), plan.getIdentifier () ) );
    }

    private Mono<Plan> createOrLoadFullBodyTrainingPlan ( Map<String, Exercise> exerciseList ) {
        final Plan plan = new Plan ();
        plan.setName ( FULL_BODY_TRAINING_PLAN_NAME );

        // Planned Bench Press
        final PlannedExercise plannedBenchPress = new PlannedExercise ();
        plannedBenchPress.setExercise ( exerciseList.get ( BENCH_PRESS_NAME ) );
        plannedBenchPress.setAimUnit ( AimUnit.STRENGTH );
        plannedBenchPress.setNumOfSets ( 3 );
        plannedBenchPress.setNumOfRepetitions ( 5 );
        plannedBenchPress.setRepetitionUnit ( RepetitionUnit.NUMBER );
        plannedBenchPress.setIntensityLevel ( 30 );
        plannedBenchPress.setIntensityUnit ( IntensityUnit.WEIGHT );
        plannedBenchPress.setBreakDurationInSeconds ( 60 );

        // Planned Military Press
        final PlannedExercise plannedMilitaryPress = new PlannedExercise ();
        plannedMilitaryPress.setExercise ( exerciseList.get ( MILITARY_PRESS_NAME ) );
        plannedMilitaryPress.setAimUnit ( AimUnit.STRENGTH );
        plannedMilitaryPress.setNumOfSets ( 3 );
        plannedMilitaryPress.setNumOfRepetitions ( 5 );
        plannedMilitaryPress.setRepetitionUnit ( RepetitionUnit.NUMBER );
        plannedMilitaryPress.setIntensityLevel ( 30 );
        plannedMilitaryPress.setIntensityUnit ( IntensityUnit.WEIGHT );
        plannedMilitaryPress.setBreakDurationInSeconds ( 60 );

        // Planned Squat
        final PlannedExercise plannedSquats = new PlannedExercise ();
        plannedSquats.setExercise ( exerciseList.get ( SQUAT_EXERCISE_NAME ) );
        plannedSquats.setAimUnit ( AimUnit.STRENGTH );
        plannedSquats.setNumOfSets ( 3 );
        plannedSquats.setNumOfRepetitions ( 5 );
        plannedSquats.setRepetitionUnit ( RepetitionUnit.NUMBER );
        plannedSquats.setIntensityLevel ( 30 );
        plannedSquats.setIntensityUnit ( IntensityUnit.WEIGHT );
        plannedSquats.setBreakDurationInSeconds ( 60 );

        // Planned Lat Pulldowns
        final PlannedExercise plannedLatPulldowns = new PlannedExercise ();
        plannedLatPulldowns.setExercise ( exerciseList.get ( LAT_PULLDOWNS_NAME ) );
        plannedLatPulldowns.setAimUnit ( AimUnit.STRENGTH );
        plannedLatPulldowns.setNumOfSets ( 3 );
        plannedLatPulldowns.setNumOfRepetitions ( 5 );
        plannedLatPulldowns.setRepetitionUnit ( RepetitionUnit.NUMBER );
        plannedLatPulldowns.setIntensityLevel ( 30 );
        plannedLatPulldowns.setIntensityUnit ( IntensityUnit.WEIGHT );
        plannedLatPulldowns.setBreakDurationInSeconds ( 60 );

        // Planned Dumbbell Curls
        final PlannedExercise plannedDumbbellCurls = new PlannedExercise ();
        plannedDumbbellCurls.setExercise ( exerciseList.get ( DUMBBELL_CURLS_NAME ) );
        plannedDumbbellCurls.setAimUnit ( AimUnit.STRENGTH );
        plannedDumbbellCurls.setNumOfSets ( 3 );
        plannedDumbbellCurls.setNumOfRepetitions ( 5 );
        plannedDumbbellCurls.setRepetitionUnit ( RepetitionUnit.NUMBER );
        plannedDumbbellCurls.setIntensityLevel ( 30 );
        plannedDumbbellCurls.setIntensityUnit ( IntensityUnit.WEIGHT );
        plannedDumbbellCurls.setBreakDurationInSeconds ( 60 );

        // Planned Decline Sit Ups
        final PlannedExercise plannedDeclineSitUps = new PlannedExercise ();
        plannedDeclineSitUps.setExercise ( exerciseList.get ( DECLINE_SIT_UP_NAME ) );
        plannedDeclineSitUps.setAimUnit ( AimUnit.STRENGTH );
        plannedDeclineSitUps.setNumOfSets ( 3 );
        plannedDeclineSitUps.setNumOfRepetitions ( 5 );
        plannedDeclineSitUps.setRepetitionUnit ( RepetitionUnit.NUMBER );
        plannedDeclineSitUps.setIntensityLevel ( 30 );
        plannedDeclineSitUps.setIntensityUnit ( IntensityUnit.WEIGHT );
        plannedDeclineSitUps.setBreakDurationInSeconds ( 60 );

        plan.addPlannedExercise ( plannedBenchPress );
        plan.addPlannedExercise ( plannedMilitaryPress );
        plan.addPlannedExercise ( plannedSquats );
        plan.addPlannedExercise ( plannedLatPulldowns );
        plan.addPlannedExercise ( plannedDumbbellCurls );
        plan.addPlannedExercise ( plannedDeclineSitUps );

        final PlanSearchParameter planSearchParameter = new PlanSearchParameter ();
        planSearchParameter.name = FULL_BODY_TRAINING_PLAN_NAME;

        return planService.findOneBySearchParameter ( planSearchParameter )
                .switchIfEmpty ( planService.save ( plan ) )
                .doOnSuccess ( this::logPlanInfo )
                .doOnError ( throwable -> LOGGER.error ( "Failed to createOrUpdateFullBodyTrainingPlan", throwable ) );
    }

    /**
     * Create or update all system exercises.
     *
     * @return Flux of system exercises
     */
    private Flux<Exercise> createOrUpdateSystemExercises () {
        return Flux.merge (
                // Bench Press
                createOrLoadBenchPress (),
                // Military Press
                createOrLoadMilitaryPress (),
                // Squats
                createOrLoadSquats (),
                // Lat pulldowns
                createOrLoadLatPulldowns (),
                // Dumbell curls
                createOrLoadDumbbellCurls (),
                // Decline sit ups
                createOrLoadDeclineSitUps () );
    }

    private Mono<Exercise> createOrLoadBenchPress () {
        final String description = "The bench press is an upper-body strength-training exercise that consists of pressing a weight upwards from a supine " +
                "position.\n The person performing the exercise lies on their back on a bench with a weight grasped in both hands. " +
                "They push the weight upwards until their arms are extended, not allowing the elbows to lock. " +
                "They then lower the weight to chest level. This is one repetition (rep).";

        return exerciseService.findOneByNameAndType ( BENCH_PRESS_NAME, ExerciseType.SYSTEM )
                .switchIfEmpty ( exerciseService.save ( new Exercise ( BENCH_PRESS_NAME, description, ExerciseType.SYSTEM ) ) )
                .doOnSuccess ( this::logExerciseInfo )
                .doOnError ( throwable -> LOGGER.error ( "Failed to createOrUpdateBenchPress", throwable ) );
    }

    private Mono<Exercise> createOrLoadMilitaryPress () {
        final String description = "The military press is a weight training exercise with many variations, typically performed while standing, " +
                "in which a weight is pressed straight upwards from racking position until the arms are locked out overhead, " +
                "while the legs, lower back and abs maintain balance. The person performin the exercise lies stands with their heels together and puts the " +
                "weight in a racking position. The weight is then pressed to overhead until the elbows are fully locked out. As the weight clears the head " +
                "the lifters leans forward or steps under the weight in order to maintain balance. No pre-movement is allowed";

        return exerciseService.findOneByNameAndType ( MILITARY_PRESS_NAME, ExerciseType.SYSTEM )
                .switchIfEmpty ( exerciseService.save ( new Exercise ( MILITARY_PRESS_NAME, description, ExerciseType.SYSTEM ) ) )
                .doOnSuccess ( this::logExerciseInfo )
                .doOnError ( throwable -> LOGGER.error ( "Failed to createOrUpdateMilitaryPress", throwable ) );
    }

    private Mono<Exercise> createOrLoadSquats () {
        final String description = "The squat is a compound, full-body exercise that primarily trains the muscles of the thighs, hips and buttocks, " +
                "quadriceps femoris muscle and hamstrings. At the start of the movement stand with feet slightly wider apart than hip width. If using a " +
                "barbell as a weight lay it across the back of the shoulders. Keeping the upper body facing forward squat down until the top of the thighs " +
                "are below the level of the knees. Ensure that the knees keep pointing outward and that they do not come forward of the toes.";

        return exerciseService.findOneByNameAndType ( SQUAT_EXERCISE_NAME, ExerciseType.SYSTEM )
                .switchIfEmpty ( exerciseService.save ( new Exercise ( SQUAT_EXERCISE_NAME, description, ExerciseType.SYSTEM ) ) )
                .doOnSuccess ( this::logExerciseInfo )
                .doOnError ( throwable -> LOGGER.error ( "Failed to createOrUpdateSquats", throwable ) );
    }

    private Mono<Exercise> createOrLoadLatPulldowns () {
        final String description = "The lat pulldown is a strength training exercise designed to develop the latissimus dorsi muscle. Sit at the pulldown " +
                "machine with thighs braced, back straight and feet flat on the floor. Hold the arms overhead at full extension, grasping the bar connected " +
                "to the weight stacks. Pull the bar down until it touches the chest, then slowly return to the starting position.";

        return exerciseService.findOneByNameAndType ( LAT_PULLDOWNS_NAME, ExerciseType.SYSTEM )
                .switchIfEmpty ( exerciseService.save ( new Exercise ( LAT_PULLDOWNS_NAME, description, ExerciseType.SYSTEM ) ) )
                .doOnSuccess ( this::logExerciseInfo )
                .doOnError ( throwable -> LOGGER.error ( "Failed to createOrUpdateLatPulldowns", throwable ) );
    }

    private Mono<Exercise> createOrLoadDumbbellCurls () {
        final String description = "Dumbbell curls are a strength exercise to target the biceps brachii muscle. Stand with upright holding the dumbbell in " +
                "one hand with supine grip. Use the bicep to lift the weight upward in an arc until no further movement is possible. Then lower the weight " +
                "back to the starting position.";

        return exerciseService.findOneByNameAndType ( DUMBBELL_CURLS_NAME, ExerciseType.SYSTEM )
                .switchIfEmpty ( exerciseService.save ( new Exercise ( DUMBBELL_CURLS_NAME, description, ExerciseType.SYSTEM ) ) )
                .doOnSuccess ( this::logExerciseInfo )
                .doOnError ( throwable -> LOGGER.error ( "Failed to createOrUpdateDumbbellCurls", throwable ) );
    }

    private Mono<Exercise> createOrLoadDeclineSitUps () {
        final String description = "Decline sit ups are a strength exercise that target the abdominal muscles. Before beginning set the decline bench to an" +
                " angle of between 30 and 45 degrees. Sit on the bench with the lower legs hanging off the elevated end of the bench. " +
                "Lie flat on the bench and cross the arms over the chest. Raise the upper body until it is vertical then lower back down to lie flat.";

        return exerciseService.findOneByNameAndType ( DECLINE_SIT_UP_NAME, ExerciseType.SYSTEM )
                .switchIfEmpty ( exerciseService.save ( new Exercise ( DECLINE_SIT_UP_NAME, description, ExerciseType.SYSTEM ) ) )
                .doOnSuccess ( this::logExerciseInfo )
                .doOnError ( throwable -> LOGGER.error ( "Failed to createOrUpdateDeclineSitUps", throwable ) );
    }

    private Mono<UserWithPassword> createOrLoadDefaultUser () {
        final String username = "user";

        final UserWithPassword userWithPassword = new UserWithPassword ();
        final User user = new User ();
        user.setUsername ( username );
        user.setFirstName ( "Root" );
        user.setLastName ( "User" );
        user.setGrantedAuthorities ( Collections.singletonList ( FitnessAppServerAuthorities.ADMIN ) );
        userWithPassword.setUser ( user );
        userWithPassword.setPassword ( "uniReutlingen2019!" );
        return this.userService.loadUserByUsername ( username ).switchIfEmpty ( this.userService.save ( userWithPassword ) );
    }

    private Mono<UserWithPassword> createOrLoadCarstenHummel () {
        final String username = "chummel";
        final UserWithPassword carstenHummel = new UserWithPassword ();
        final User user = new User ();
        user.setFirstName ( "Carsten" );
        user.setLastName ( "Hummel" );
        user.setUsername ( username );
        user.setGrantedAuthorities ( Collections.singletonList ( FitnessAppServerAuthorities.USER ) );
        carstenHummel.setUser ( user );
        carstenHummel.setPassword ( "X@tjvcwxybnCM87MRZ6arguaC" );

        return this.userService.loadUserByUsername ( username ).switchIfEmpty ( this.userService.save ( carstenHummel ) );
    }

    private Mono<UserWithPassword> createOrLoadYeeunPark () {
        final String username = "ypark";
        final UserWithPassword yeeunPark = new UserWithPassword ();
        final User user = new User ();
        user.setFirstName ( "Ye-eun" );
        user.setLastName ( "Park" );
        user.setUsername ( username );
        user.setGrantedAuthorities ( Collections.singletonList ( FitnessAppServerAuthorities.USER ) );
        yeeunPark.setUser ( user );
        yeeunPark.setPassword ( "ZFDJVd9@DaL7-AX8kD6eUcCXV" );

        return this.userService.loadUserByUsername ( username ).switchIfEmpty ( this.userService.save ( yeeunPark ) );
    }

    private Mono<UserWithPassword> createOrLoadErikaMueller () {
        final String username = "emueller";
        final UserWithPassword erikaMueller = new UserWithPassword ();
        final User user = new User ();
        user.setFirstName ( "Erika" );
        user.setLastName ( "Müller" );
        user.setUsername ( username );
        user.setGrantedAuthorities ( Collections.singletonList ( FitnessAppServerAuthorities.USER ) );
        erikaMueller.setUser ( user );
        erikaMueller.setPassword ( "gPcM94upsriYLe3VzT27qh_34" );

        return this.userService.loadUserByUsername ( username ).switchIfEmpty ( this.userService.save ( erikaMueller ) );
    }

}
