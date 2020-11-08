package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MUSCLE_ARM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MUSCLE_CHEST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_GYM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ExerciseBook;
import seedu.address.model.exercise.Exercise;

/**
 * A utility class containing a list of {@code Exercise} objects to be used in tests.
 */
public class TypicalExercise {

    public static final Exercise TYPICAL_EXERCISE = new ExerciseBuilder().withName(VALID_NAME_PUSH_UP)
            .withDescription(VALID_DESCRIPTION_PUSH_UP).withDate(VALID_DATE_PUSH_UP)
            .withCalories(VALID_CALORIES_PUSH_UP).withMuscleTags(VALID_MUSCLE_CHEST).withTags(VALID_TAG_GYM).build();

    public static final Exercise PUSH_UP = new ExerciseBuilder().withName(VALID_NAME_PUSH_UP)
            .withDescription(VALID_DESCRIPTION_PUSH_UP).withDate(VALID_DATE_PUSH_UP)
            .withCalories(VALID_CALORIES_PUSH_UP).withTags(VALID_TAG_GYM).withMuscleTags(VALID_MUSCLE_CHEST).build();

    public static final Exercise SIT_UP = new ExerciseBuilder().withName(VALID_NAME_SIT_UP)
            .withDescription(VALID_DESCRIPTION_SIT_UP).withDate(VALID_DATE_SIT_UP)
            .withCalories(VALID_CALORIES_SIT_UP).withMuscleTags(VALID_MUSCLE_ARM).build();

    public static final Exercise RUN = new ExerciseBuilder().withName("Run")
            .withDescription("Test 3").withDate("17-10-2020").withCalories("12348")
            .withMuscleTags("leg").build();

    public static final Exercise WALK = new ExerciseBuilder().withName("Walk")
            .withDescription("Test 4").withDate("24-10-2020").withCalories("12349")
            .withMuscleTags("leg").build();

    public static final Exercise PULL_UP = new ExerciseBuilder().withName("Pull up")
            .withDescription("Test 5").withDate("31-10-2020").withCalories("123400")
            .withMuscleTags("arm").build();

    public static final Exercise SQUAT = new ExerciseBuilder().withName("Squat")
            .withDescription("Test 6").withDate("07-11-2020").withCalories("255491")
            .withMuscleTags("leg").build();;

    private TypicalExercise() {
    } // prevents instantiation

    /**
     * Returns an {@code ExerciseBook} with all the typical Exercises.
     */
    public static ExerciseBook getTypicalExerciseBook() {
        ExerciseBook eb = new ExerciseBook();
        for (Exercise exercise : getTypicalExercises()) {
            eb.addExercise(exercise);
        }
        return eb;
    }

    public static List<Exercise> getTypicalExercises() {
        return new ArrayList<>(Arrays.asList(PUSH_UP, PULL_UP, SIT_UP, RUN, WALK, SQUAT));
    }

}
