package seedu.address.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXERCISE_CRUNCHES;
import static seedu.address.testutil.TypicalExercises.JUMPING_JACK;
import static seedu.address.testutil.TypicalExercises.SIT_UP;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ExerciseBuilder;

public class ExerciseTest {

    @Test
    public void equals() {
        // same values -> returns true
        Exercise exerciseCopy = new ExerciseBuilder(SIT_UP).build();
        assertTrue(SIT_UP.equals(exerciseCopy));

        // same object -> returns true
        assertTrue(SIT_UP.equals(SIT_UP));

        // null -> returns false
        assertFalse(SIT_UP.equals(null));

        // different exercise -> returns false
        assertFalse(SIT_UP.equals(JUMPING_JACK));

        // different name -> returns false
        Exercise editedSitUp = new ExerciseBuilder(SIT_UP).withName(VALID_EXERCISE_CRUNCHES).build();
        assertFalse(SIT_UP.equals(editedSitUp));
    }
}
