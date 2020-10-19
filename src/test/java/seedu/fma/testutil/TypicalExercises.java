package seedu.fma.testutil;

import static seedu.fma.model.util.SampleDataUtil.getSampleExercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.fma.model.exercise.Exercise;


/**
 * A utility class containing a list of {@code Exercise} objects to be used in tests.
 */
public class TypicalExercises {
    public static final Exercise[] SAMPLE_EXERCISES = getSampleExercises();

    public static final Exercise EXERCISE_A = new ExerciseBuilder(SAMPLE_EXERCISES[0]).build();
    public static final Exercise EXERCISE_B = new ExerciseBuilder(SAMPLE_EXERCISES[1]).build();
    public static final Exercise EXERCISE_C = new ExerciseBuilder(SAMPLE_EXERCISES[2]).build();
    public static final Exercise EXERCISE_D = new ExerciseBuilder(SAMPLE_EXERCISES[3]).build();
    public static final Exercise EXERCISE_E = new ExerciseBuilder(SAMPLE_EXERCISES[4]).build();
    public static final Exercise EXERCISE_F = new ExerciseBuilder(SAMPLE_EXERCISES[5]).build();

    private TypicalExercises() {} // prevents instantiation

    public static List<Exercise> getTypicalExercises() {
        return new ArrayList<>(Arrays.asList(EXERCISE_A, EXERCISE_B, EXERCISE_C, EXERCISE_D, EXERCISE_E, EXERCISE_F));
    }
}
