package de.university.reutlingen.mobile.computing.fitnessappserver.init;


import de.university.reutlingen.mobile.computing.fitnessappserver.control.ExerciseService;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Exercise;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.ExerciseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class DatabaseInitializer extends AbstractInitializationService {

    private static final Logger LOGGER = LoggerFactory.getLogger ( DatabaseInitializer.class );

    private final ExerciseService exerciseService;

    /**
     * Constructor for required fields.
     *
     * @param isProductionMode flag showing if application is running in production mode.
     */
    public DatabaseInitializer ( boolean isProductionMode, ExerciseService exerciseService ) {
        super ( isProductionMode );

        this.exerciseService = exerciseService;
    }

    @Override
    protected void createProductionData () {
        LOGGER.info ( "No production data to create" );
    }

    @Override
    protected void createDummyData () {
        createOrUpdateSystemExercises ();
    }

    private void logExerciseInfo ( @NotNull Exercise exercise ) {
        LOGGER.debug ( String.format ( "Exercise created:\n Name: %s\n ID: %s", exercise.getName (), exercise.getId () ) );
    }

    /**
     * Create or update all system exercises.
     */
    private void createOrUpdateSystemExercises () {
        // Bench Press
        createOrUpdateBenchPress ().subscribe ();
        // Military Press
        createOrUpdateMilitaryPress ().subscribe ();
        // Squats
        createOrUpdateSquats ().subscribe ();
        // Lat pulldowns
        createOrUpdateLatPulldowns ().subscribe ();
        // Dumbell curls
        createOrUpdateDumbbellCurls ().subscribe ();
        // Decline sit ups
        createOrUpdateDeclineSitUps ().subscribe ();
    }

    private Mono<Exercise> createOrUpdateBenchPress () {
        final String name = "Bench Press";
        final String description = "The bench press is an upper-body strength-training exercise that consists of pressing a weight upwards from a supine " +
                "position.\n The person performing the exercise lies on their back on a bench with a weight grasped in both hands. " +
                "They push the weight upwards until their arms are extended, not allowing the elbows to lock. " +
                "They then lower the weight to chest level. This is one repetition (rep).";

        return exerciseService.findOneByNameAndType ( name, ExerciseType.SYSTEM )
                .switchIfEmpty ( exerciseService.save ( new Exercise ( name, description, ExerciseType.SYSTEM ) ) )
                .doOnEach ( exerciseSignal -> this.logExerciseInfo ( Objects.requireNonNull ( exerciseSignal.get () ) ) );

    }

    private Mono<Exercise> createOrUpdateMilitaryPress () {
        final String name = "Military Press";
        final String description = "The military press is a weight training exercise with many variations, typically performed while standing, " +
                "in which a weight is pressed straight upwards from racking position until the arms are locked out overhead, " +
                "while the legs, lower back and abs maintain balance. The person performin the exercise lies stands with their heels together and puts the " +
                "weight in a racking position. The weight is then pressed to overhead until the elbows are fully locked out. As the weight clears the head " +
                "the lifters leans forward or steps under the weight in order to maintain balance. No pre-movement is allowed";

        return exerciseService.findOneByNameAndType ( name, ExerciseType.SYSTEM )
                .switchIfEmpty ( exerciseService.save ( new Exercise ( name, description, ExerciseType.SYSTEM ) ) )
                .doOnEach ( exerciseSignal -> this.logExerciseInfo ( Objects.requireNonNull ( exerciseSignal.get () ) ) );
    }

    private Mono<Exercise> createOrUpdateSquats () {
        final String name = "Squat";
        final String description = "The squat is a compound, full-body exercise that primarily trains the muscles of the thighs, hips and buttocks, " +
                "quadriceps femoris muscle and hamstrings. At the start of the movement stand with feet slightly wider apart than hip width. If using a " +
                "barbell as a weight lay it across the back of the shoulders. Keeping the upper body facing forward squat down until the top of the thighs " +
                "are below the level of the knees. Ensure that the knees keep pointing outward and that they do not come forward of the toes.";

        return exerciseService.findOneByNameAndType ( name, ExerciseType.SYSTEM )
                .switchIfEmpty ( exerciseService.save ( new Exercise ( name, description, ExerciseType.SYSTEM ) ) )
                .doOnEach ( exerciseSignal -> this.logExerciseInfo ( Objects.requireNonNull ( exerciseSignal.get () ) ) );
    }

    private Mono<Exercise> createOrUpdateLatPulldowns () {
        final String name = "Lat Pulldowns";
        final String description = "The lat pulldown is a strength training exercise designed to develop the latissimus dorsi muscle. Sit at the pulldown " +
                "machine with thighs braced, back straight and feet flat on the floor. Hold the arms overhead at full extension, grasping the bar connected " +
                "to the weight stacks. Pull the bar down until it touches the chest, then slowly return to the starting position.";

        return exerciseService.findOneByNameAndType ( name, ExerciseType.SYSTEM )
                .switchIfEmpty ( exerciseService.save ( new Exercise ( name, description, ExerciseType.SYSTEM ) ) )
                .doOnEach ( exerciseSignal -> this.logExerciseInfo ( Objects.requireNonNull ( exerciseSignal.get () ) ) );
    }

    private Mono<Exercise> createOrUpdateDumbbellCurls () {
        final String name = "Dumbbell Curls";
        final String description = "Dumbbell curls are a strength exercise to target the biceps brachii muscle. Stand with upright holding the dumbbell in " +
                "one hand with supine grip. Use the bicep to lift the weight upward in an arc until no further movement is possible. Then lower the weight " +
                "back to the starting position.";

        return exerciseService.findOneByNameAndType ( name, ExerciseType.SYSTEM )
                .switchIfEmpty ( exerciseService.save ( new Exercise ( name, description, ExerciseType.SYSTEM ) ) )
                .doOnEach ( exerciseSignal -> this.logExerciseInfo ( Objects.requireNonNull ( exerciseSignal.get () ) ) );
    }

    private Mono<Exercise> createOrUpdateDeclineSitUps () {
        final String name = "Decline Sit Up";
        final String description = "Decline sit ups are a strength exercise that target the abdominal muscles. Before beginning set the decline bench to an" +
                " angle of between 30 and 45 degrees. Sit on the bench with the lower legs hanging off the elevated end of the bench. " +
                "Lie flat on the bench and cross the arms over the chest. Raise the upper body until it is vertical then lower back down to lie flat.";

        return exerciseService.findOneByNameAndType ( name, ExerciseType.SYSTEM )
                .switchIfEmpty ( exerciseService.save ( new Exercise ( name, description, ExerciseType.SYSTEM ) ) )
                .doOnEach ( exerciseSignal -> this.logExerciseInfo ( Objects.requireNonNull ( exerciseSignal.get () ) ) );
    }
}
