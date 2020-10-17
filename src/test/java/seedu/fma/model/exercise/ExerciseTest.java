package seedu.fma.model.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_EXERCISE_JUMPING_JACKS;
import static seedu.fma.testutil.Assert.assertThrows;
import static seedu.fma.testutil.TypicalExercises.JUMPING_JACK;
import static seedu.fma.testutil.TypicalExercises.SIT_UP;

import org.junit.jupiter.api.Test;

import seedu.fma.model.exercise.exceptions.ExerciseNotFoundException;
import seedu.fma.model.util.Name;
import seedu.fma.testutil.ExerciseBuilder;

public class ExerciseTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        // null name
        assertThrows(NullPointerException.class, () -> new Exercise(null, 123));
    }

    @Test
    public void constructor_invalidCaloriesPerRep_throwsIllegalArgumentException() {
        // invalid caloriesPerRep
        assertThrows(IllegalArgumentException.class, () ->
                new Exercise(new Name("jumping jacks"), -10));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Exercise exerciseCopy = new ExerciseBuilder(SIT_UP).build();
        assertTrue(SIT_UP.equals(exerciseCopy));

        // same object -> returns true
        assertTrue(exerciseCopy.equals(exerciseCopy));

        // different object -> returns false
        assertFalse(SIT_UP.equals(new ExerciseNotFoundException()));

        // null -> returns false
        assertFalse(SIT_UP.equals(null));

        // different exercise -> returns false
        assertFalse(SIT_UP.equals(JUMPING_JACK));

        // different name -> returns false
        Exercise editedSitUp = new ExerciseBuilder(SIT_UP).withName(VALID_EXERCISE_JUMPING_JACKS).build();
        assertFalse(SIT_UP.equals(editedSitUp));
    }

    @Test
    void testHashCode() {
        assertTrue(SIT_UP.hashCode() == SIT_UP.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Sit ups CaloriesPerRep: 20", SIT_UP.toString());
    }
}
