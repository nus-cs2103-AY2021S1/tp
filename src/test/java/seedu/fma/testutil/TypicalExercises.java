package seedu.fma.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.fma.model.LogBook;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.log.Comment;
import seedu.fma.model.log.Log;
import seedu.fma.model.log.Rep;

/**
 * A utility class containing a list of {@code Exercise} objects to be used in tests.
 */
public class TypicalExercises {

    public static final Exercise SIT_UP = new ExerciseBuilder().withName("Sit ups")
            .withCaloriesPerRep(20).build();
    public static final Exercise JUMPING_JACK = new ExerciseBuilder().withName("Jumping jacks")
            .withCaloriesPerRep(15).build();
    public static final Exercise PULL_UP = new ExerciseBuilder().withName("Pull ups")
            .withCaloriesPerRep(30).build();
    public static final Rep REP = new Rep("50");
    public static final Comment COMMENT = new Comment("This is a test log from Typical Exercises");

    private TypicalExercises() {} // prevents instantiation

    /**
     * Returns a {@code LogBook} with all the typical exercises and standardised reps and comments.
     */
    public static LogBook getTypicalLogBook() {
        LogBook lb = new LogBook();
        for (Exercise exercise : getTypicalExercises()) {
            Log log = new Log(exercise, REP, COMMENT);
            lb.addLog(log);
        }
        return lb;
    }

    public static List<Exercise> getTypicalExercises() {
        return new ArrayList<>(Arrays.asList(SIT_UP, JUMPING_JACK, PULL_UP));
    }
}
