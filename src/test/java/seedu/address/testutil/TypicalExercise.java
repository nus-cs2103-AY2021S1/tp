package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ExerciseBook;
import seedu.address.model.exercise.Exercise;

/**
 * A utility class containing a list of {@code Exercise} objects to be used in tests.
 */
public class TypicalExercise {

    public static final Exercise TYPICAL_EXERCISE = new ExerciseBuilder().withName("Push Up")
            .withDescription("Push Up Description").withDate("10-10-2020").withCalories("100").build();

    public static final Exercise PUSH_UP = new ExerciseBuilder().withName("Push Up")
            .withDescription("Test 1").withDate("09-10-2020").withCalories("12345").build();

    public static final Exercise SIT_UP = new ExerciseBuilder().withName("Sit up")
            .withDescription("Test 2").withDate("10-10-2020").withCalories("23456").build();

    public static final Exercise RUN = new ExerciseBuilder().withName("Run")
            .withDescription("Test 3").withDate("17-10-2020").withCalories("12348").build();

    public static final Exercise WALK = new ExerciseBuilder().withName("Walk")
            .withDescription("Test 4").withDate("24-10-2020").withCalories("12349").build();

    public static final Exercise PULL_UP = new ExerciseBuilder().withName("Pull up")
            .withDescription("Test 5").withDate("31-10-2020").withCalories("123400").build();

    public static final Exercise SQUAT = new ExerciseBuilder().withName("Squat")
            .withDescription("Test 6").withDate("07-11-2020").withCalories("255491").build();


    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalExercise() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical Exercises.
     */
    public static ExerciseBook getTypicalExerciseBook() {
        ExerciseBook ab = new ExerciseBook();
        for (Exercise exercise : getTypicalExercises()) {
            ab.addExercise(exercise);
        }
        return ab;
    }

    public static List<Exercise> getTypicalExercises() {
        return new ArrayList<>(Arrays.asList(PULL_UP, PUSH_UP, SIT_UP, RUN, WALK, SQUAT));
    }

}
