package seedu.address.model.log;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLogs.*;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LogBuilder;

public class LogTest {

    @Test
    public void isSameLog() {
        // same object -> returns true
        assertTrue(LOG_A.isSameLog(LOG_A));

        // null -> returns false
        assertFalse(LOG_A.isSameLog(null));

        // different exercise and dateTime -> returns false
        Log editedAlice = new LogBuilder(LOG_A).withExercise(VALID_EXERCISE_B).withDateTime(VALID_DATE_TIME_B).build();
        assertFalse(LOG_A.isSameLog(editedAlice));

        // different exercise -> returns false
        editedAlice = new LogBuilder(LOG_A).withExercise(VALID_EXERCISE_B).build();
        assertFalse(LOG_A.isSameLog(editedAlice));

        // different dateTime -> returns false
        editedAlice = new LogBuilder(LOG_A).withDateTime(VALID_DATE_TIME_B).build();
        assertFalse(LOG_A.isSameLog(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Log aliceCopy = new LogBuilder(LOG_A).build();
        assertTrue(LOG_A.equals(aliceCopy));

        // same object -> returns true
        assertTrue(LOG_A.equals(LOG_A));

        // null -> returns false
        assertFalse(LOG_A.equals(null));

        // different type -> returns false
        assertFalse(LOG_A.equals(5));

        // different log -> returns false
        assertFalse(LOG_A.equals(LOG_B));

        // different exercise -> returns false
        Log editedAlice = new LogBuilder(LOG_A).withExercise(VALID_EXERCISE_B).build();
        assertFalse(LOG_A.equals(editedAlice));

        // different reps -> returns false
        editedAlice = new LogBuilder(LOG_A).withReps(VALID_REP_B).build();
        assertFalse(LOG_A.equals(editedAlice));

        // different comment -> returns false
        editedAlice = new LogBuilder(LOG_A).withComment(VALID_COMMENT_B).build();
        assertFalse(LOG_A.equals(editedAlice));

        // different dateTime -> returns false
        editedAlice = new LogBuilder(LOG_A).withDateTime(VALID_DATE_TIME_B).build();
        assertFalse(LOG_A.equals(editedAlice));

    }
}
