package seedu.fma.model.log;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_COMMENT_A_STR;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_COMMENT_B_STR;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_DATE_TIME_B;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_EXERCISE_A;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_EXERCISE_B;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_REP_A;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_REP_A_INT;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_REP_B_INT;
import static seedu.fma.testutil.Assert.assertThrows;
import static seedu.fma.testutil.TypicalLogs.LOG_A;
import static seedu.fma.testutil.TypicalLogs.LOG_B;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.fma.model.util.Calories;
import seedu.fma.testutil.LogBuilder;

public class LogTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Log(null, null, null));
    }

    @Test
    public void constructor_validParameters_noException() {
        Log logA = new Log(VALID_EXERCISE_A, VALID_REP_A, new Comment(VALID_COMMENT_A_STR));

        // Check all the variables are equal
        assertEquals(VALID_EXERCISE_A, logA.getExercise());
        assertEquals(VALID_REP_A, logA.getReps());
        Calories calories = new Calories(VALID_EXERCISE_A.getCaloriesPerRep()
                .value * VALID_REP_A_INT);
        assertTrue(calories.equals(logA.getCalories()));
    }

    @Test
    public void getPrettyDateTime_hasDateTime_returnCorrectFormat() {
        assertEquals("Wed 1:01AM, 01 Jan 2020", LOG_A.getPrettyDateTime());
        assertEquals("Sun 2:02AM, 02 Feb 2020", LOG_B.getPrettyDateTime());
    }

    @Test
    public void isSameLog_sameLog_returnTrue() {
        // same object -> returns true
        assertTrue(LOG_A.isSameLog(LOG_A));
    }

    @Test
    public void isSameLog_differentLog_returnFalse() {
        // null -> returns false
        assertFalse(LOG_A.isSameLog(null));

        // different exercise and dateTime -> returns false
        Log editedLog = new LogBuilder(LOG_A)
                .withExercise(VALID_EXERCISE_B)
                .withDateTime(VALID_DATE_TIME_B)
                .build();
        assertFalse(LOG_A.isSameLog(editedLog));

        // different dateTime -> returns false
        editedLog = new LogBuilder(LOG_A).withDateTime(VALID_DATE_TIME_B).build();
        assertFalse(LOG_A.isSameLog(editedLog));
    }

    @Test
    public void equals() {
        // different object -> returns true
        Log logCopy = new LogBuilder(LOG_A).build();
        assertFalse(LOG_A == logCopy);

        // same object -> returns true
        assertTrue(LOG_A == LOG_A);

        // null -> returns false
        assertFalse(LOG_A == null);

        // different type -> returns false
        assertFalse(LOG_A.equals(5));

        // different log -> returns false
        assertFalse(LOG_A == LOG_B);

        // different exercise -> returns false
        Log editedLog = new LogBuilder(LOG_A).withExercise(VALID_EXERCISE_B).build();
        assertFalse(LOG_A == editedLog);

        // different reps -> returns false
        editedLog = new LogBuilder(LOG_A).withReps(VALID_REP_B_INT).build();
        assertFalse(LOG_A == editedLog);

        // different comment -> returns false
        editedLog = new LogBuilder(LOG_A).withComment(VALID_COMMENT_B_STR).build();
        assertFalse(LOG_A == editedLog);

        // different dateTime -> returns false
        editedLog = new LogBuilder(LOG_A).withDateTime(VALID_DATE_TIME_B).build();
        assertFalse(LOG_A == editedLog);
    }

    @Test
    void testHashCode() {
        assertTrue(LOG_A.hashCode() == LOG_A.hashCode());
        assertTrue(LOG_A.hashCode()
                == Objects.hash(LOG_A.getExercise(), LOG_A.getDateTime(), LOG_A.getReps(),
                LOG_A.getComment()));
    }
}
