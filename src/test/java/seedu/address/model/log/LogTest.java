package seedu.address.model.log;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.LogBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalLogs.LOG_A;
import static seedu.address.testutil.TypicalLogs.LOG_B;

public class LogTest {
    @Test
    public void getPrettyDateTime_hasDateTime_returnCorrectFormat() {
        assertEquals("Wed 1:01AM, 01 Jan 2020", LOG_A.getPrettyDateTime());   assertEquals("Sun 2:02AM, 02 Feb 2020", LOG_B.getPrettyDateTime());
    }

//    @Test
//    public void isSameLog() {
//        // same object -> returns true
//        assertTrue(LOG_A.isSameLog(LOG_A));
//
//        // null -> returns false
//        assertFalse(LOG_A.isSameLog(null));
//
//        // different exercise and dateTime -> returns false
//        Log editedAlice = new LogBuilder(LOG_A).withExercise(VALID_EXERCISE_B).withDateTime(VALID_DATE_TIME_B).build();
//        assertFalse(LOG_A.isSameLog(editedAlice));
//
//        // different exercise -> returns false
//        editedAlice = new LogBuilder(LOG_A).withExercise(VALID_EXERCISE_B).build();
//        assertFalse(LOG_A.isSameLog(editedAlice));
//
//        // different dateTime -> returns false
//        editedAlice = new LogBuilder(LOG_A).withDateTime(VALID_DATE_TIME_B).build();
//        assertFalse(LOG_A.isSameLog(editedAlice));
//    }
//
//    @Test
//    public void equals() {
//        // same values -> returns true
//        Log aliceCopy = new LogBuilder(LOG_A).build();
//        assertTrue(LOG_A.equals(aliceCopy));
//
//        // same object -> returns true
//        assertTrue(LOG_A.equals(LOG_A));
//
//        // null -> returns false
//        assertFalse(LOG_A.equals(null));
//
//        // different type -> returns false
//        assertFalse(LOG_A.equals(5));
//
//        // different log -> returns false
//        assertFalse(LOG_A.equals(LOG_B));
//
//        // different exercise -> returns false
//        Log editedAlice = new LogBuilder(LOG_A).withExercise(VALID_EXERCISE_B).build();
//        assertFalse(LOG_A.equals(editedAlice));
//
//        // different reps -> returns false
//        editedAlice = new LogBuilder(LOG_A).withReps(VALID_REP_B).build();
//        assertFalse(LOG_A.equals(editedAlice));
//
//        // different comment -> returns false
//        editedAlice = new LogBuilder(LOG_A).withComment(VALID_COMMENT_B).build();
//        assertFalse(LOG_A.equals(editedAlice));
//
//        // different dateTime -> returns false
//        editedAlice = new LogBuilder(LOG_A).withDateTime(VALID_DATE_TIME_B).build();
//        assertFalse(LOG_A.equals(editedAlice));
//
//    }
}
