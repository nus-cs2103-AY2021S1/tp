package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.FitNus;
import seedu.address.model.exercise.Exercise;

public class TypicalExercises {

    public static final Exercise BENCH_PRESS = new ExerciseBuilder().withName("Bench press")
            .withTags("chest").build();
    public static final Exercise SQUATS = new ExerciseBuilder().withName("Squats")
            .withTags("Legs").build();

    private TypicalExercises() {} // prevents instantiation

    /**
     * Returns an {@code FitNus} with all the typical exercises.
     */
    public static FitNus getTypicalFitNus() {
        FitNus fn = new FitNus();
        for (Exercise exercise : getTypicalExercises()) {
            fn.addExercise(exercise);
        }
        return fn;
    }

    public static List<Exercise> getTypicalExercises() {
        return new ArrayList<>(Arrays.asList(BENCH_PRESS, SQUATS));
    }
}
