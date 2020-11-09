package seedu.fma.model.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.fma.logic.commands.CommandTestUtil.EXERCISE_B;
import static seedu.fma.testutil.Assert.assertThrows;
import static seedu.fma.testutil.TypicalExercises.EXERCISE_A;
import static seedu.fma.testutil.TypicalExercises.EXERCISE_C;

import org.junit.jupiter.api.Test;

import seedu.fma.model.exercise.exceptions.ExerciseNotFoundException;
import seedu.fma.model.util.Calories;
import seedu.fma.testutil.ExerciseBuilder;

public class ExerciseTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        // null name
        assertThrows(NullPointerException.class, () -> new Exercise(null, new Calories(123)));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Exercise exerciseCopy = new ExerciseBuilder(EXERCISE_A).build();
        assertEquals(exerciseCopy, EXERCISE_A);

        // same object -> returns true
        assertEquals(exerciseCopy, exerciseCopy);

        // different object -> returns false
        assertNotEquals(new ExerciseNotFoundException(), EXERCISE_A);

        // null -> returns false
        assertNotEquals(EXERCISE_A, null);

        // different exercise -> returns false
        assertNotEquals(EXERCISE_C, EXERCISE_A);

        // different name -> returns false
        Exercise editedSitUp = new ExerciseBuilder(EXERCISE_A).withStringName(EXERCISE_B).build();
        assertNotEquals(editedSitUp, EXERCISE_A);
    }

    @Test
    void testHashCode() {
        assertEquals(EXERCISE_A.hashCode(), EXERCISE_A.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Name: Flying kicks\nCalories per rep: 15", EXERCISE_A.toString());
    }
}
