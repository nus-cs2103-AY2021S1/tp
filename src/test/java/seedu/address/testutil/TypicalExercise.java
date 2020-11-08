package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_PULL_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_RUN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_SQUAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_WALK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_PULL_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_RUN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SQUAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_WALK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PULL_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_RUN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SQUAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_WALK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MUSCLE_ARM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MUSCLE_CHEST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MUSCLE_LEG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PULL_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_RUN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_SQUAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_WALK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_GYM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.parser.exceptions.CaloriesOverflow;
import seedu.address.model.ExerciseBook;
import seedu.address.model.exercise.Exercise;

/**
 * A utility class containing a list of {@code Exercise} objects to be used in tests.
 */
public class TypicalExercise {
    public static final Exercise TYPICAL_EXERCISE;
    public static final Exercise PUSH_UP;
    public static final Exercise SIT_UP;
    public static final Exercise RUN;
    public static final Exercise WALK;
    public static final Exercise PULL_UP;
    public static final Exercise SQUAT;

    static {
        try {
            TYPICAL_EXERCISE = new ExerciseBuilder().withName(VALID_NAME_PUSH_UP)
                    .withDescription(VALID_DESCRIPTION_PUSH_UP).withDate(VALID_DATE_PUSH_UP)
                    .withCalories(VALID_CALORIES_PUSH_UP).withMuscleTags(VALID_MUSCLE_CHEST)
                    .withTags(VALID_TAG_GYM).build();

            PUSH_UP = new ExerciseBuilder().withName(VALID_NAME_PUSH_UP)
                    .withDescription(VALID_DESCRIPTION_PUSH_UP).withDate(VALID_DATE_PUSH_UP)
                    .withCalories(VALID_CALORIES_PUSH_UP).withTags(VALID_TAG_GYM).withMuscleTags(VALID_MUSCLE_CHEST)
                    .build();

            SIT_UP = new ExerciseBuilder().withName(VALID_NAME_SIT_UP)
                    .withDescription(VALID_DESCRIPTION_SIT_UP).withDate(VALID_DATE_SIT_UP)
                    .withCalories(VALID_CALORIES_SIT_UP).withMuscleTags(VALID_MUSCLE_ARM).build();

            RUN = new ExerciseBuilder().withName(VALID_NAME_RUN)
                    .withDescription(VALID_DESCRIPTION_RUN).withDate(VALID_DATE_RUN)
                    .withCalories(VALID_CALORIES_RUN).withMuscleTags(VALID_MUSCLE_LEG).build();

            WALK = new ExerciseBuilder().withName(VALID_NAME_WALK)
                    .withDescription(VALID_DESCRIPTION_WALK).withDate(VALID_DATE_WALK)
                    .withCalories(VALID_CALORIES_WALK).withMuscleTags(VALID_MUSCLE_LEG).build();

            PULL_UP = new ExerciseBuilder().withName(VALID_NAME_PULL_UP)
                    .withDescription(VALID_DESCRIPTION_PULL_UP).withDate(VALID_DATE_PULL_UP)
                    .withCalories(VALID_CALORIES_PULL_UP).withMuscleTags(VALID_MUSCLE_CHEST).build();

            SQUAT = new ExerciseBuilder().withName(VALID_NAME_SQUAT)
                    .withDescription(VALID_DESCRIPTION_SQUAT).withDate(VALID_DATE_SQUAT)
                    .withCalories(VALID_CALORIES_SQUAT).withMuscleTags(VALID_MUSCLE_LEG).build();;
        } catch (CaloriesOverflow err) {
            throw new RuntimeException("Typical Exercise contains Invalid Calories Field");
        }
    }

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
