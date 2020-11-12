package seedu.address.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXERCISE_NAME_SQUATS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXERCISE_TAG_CHEST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXERCISE_TAG_LEGS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExercises.BENCH_PRESS;
import static seedu.address.testutil.TypicalExercises.SQUATS;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ExerciseBuilder;

public class ExerciseTest {

    @Test
    public void asObservableExerciseList_modifyExerciseList_throwsUnsupportedOperationException() {
        Exercise exercise = new ExerciseBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> exercise.getTags().remove(0));
    }

    @Test
    public void isSameExercise() {
        // same object -> returns true
        assertTrue(BENCH_PRESS.isSameExercise(BENCH_PRESS));

        // null -> returns false
        assertFalse(BENCH_PRESS.isSameExercise(null));

        // different name -> returns false
        Exercise editedBenchPress = new ExerciseBuilder(BENCH_PRESS).withName(VALID_EXERCISE_NAME_SQUATS).build();
        assertFalse(BENCH_PRESS.isSameExercise(editedBenchPress));

        // same name, different tags -> returns true
        editedBenchPress = new ExerciseBuilder(BENCH_PRESS).withTags(VALID_EXERCISE_TAG_CHEST).build();
        assertTrue(BENCH_PRESS.isSameExercise(editedBenchPress));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Exercise benchPressCopy = new ExerciseBuilder(BENCH_PRESS).build();
        assertTrue(BENCH_PRESS.equals(benchPressCopy));

        // same object -> returns true
        assertTrue(BENCH_PRESS.equals(BENCH_PRESS));

        // null -> returns false
        assertFalse(BENCH_PRESS.equals(null));

        // different type -> returns false
        assertFalse(BENCH_PRESS.equals(5));

        // different exercise -> returns false
        assertFalse(BENCH_PRESS.equals(SQUATS));

        // different name -> returns false
        Exercise editedBenchPress = new ExerciseBuilder(BENCH_PRESS).withName(VALID_EXERCISE_NAME_SQUATS).build();
        assertFalse(BENCH_PRESS.equals(editedBenchPress));

        // different tags -> returns false
        editedBenchPress = new ExerciseBuilder(BENCH_PRESS).withTags(VALID_EXERCISE_TAG_LEGS).build();
        assertFalse(BENCH_PRESS.equals(editedBenchPress));
    }
}
