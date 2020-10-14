package seedu.fma.model.log;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_COMMENT_B;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_DATE_TIME_B;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_EXERCISE_B;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_REP_B;
import static seedu.fma.testutil.TypicalLogs.LOG_A;
import static seedu.fma.testutil.TypicalLogs.LOG_B;

import org.junit.jupiter.api.Test;

import seedu.fma.testutil.LogBuilder;


public class LogTest {
    @Test
    public void getPrettyDateTime_hasDateTime_returnCorrectFormat() {
        assertEquals("Wed 1:01AM, 01 Jan 2020", LOG_A.getPrettyDateTime());
        assertEquals("Sun 2:02AM, 02 Feb 2020", LOG_B.getPrettyDateTime());
    }

    @Test
    public void isSameLog() {
        // same object -> returns true
        assertTrue(LOG_A.isSameLog(LOG_A));

        // null -> returns false
        assertFalse(LOG_A.isSameLog(null));

        // different exercise and dateTime -> returns false
        Log editedAlice = new LogBuilder(LOG_A).withExercise(VALID_EXERCISE_B).withDateTime(VALID_DATE_TIME_B).build();
        assertFalse(LOG_A.isSameLog(editedAlice));

        // different dateTime -> returns false
        editedAlice = new LogBuilder(LOG_A).withDateTime(VALID_DATE_TIME_B).build();
        assertFalse(LOG_A.isSameLog(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Log aliceCopy = new LogBuilder(LOG_A).build();
        assertEquals(LOG_A, aliceCopy);

        // same object -> returns true
        assertEquals(LOG_A, LOG_A);

        // null -> returns false
        assertNotEquals(null, LOG_A);

        // different type -> returns false
        assertNotEquals(5, LOG_A);

        // different log -> returns false
        assertNotEquals(LOG_A, LOG_B);

        // different exercise -> returns false
        Log editedAlice = new LogBuilder(LOG_A).withExercise(VALID_EXERCISE_B).build();
        assertNotEquals(LOG_A, editedAlice);

        // different reps -> returns false
        editedAlice = new LogBuilder(LOG_A).withReps(VALID_REP_B).build();
        assertNotEquals(LOG_A, editedAlice);

        // different comment -> returns false
        editedAlice = new LogBuilder(LOG_A).withComment(VALID_COMMENT_B).build();
        assertNotEquals(LOG_A, editedAlice);

        // different dateTime -> returns false
        editedAlice = new LogBuilder(LOG_A).withDateTime(VALID_DATE_TIME_B).build();
        assertNotEquals(LOG_A, editedAlice);
    }
}
