package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.session.SessionCommandTestUtil.VALID_DURATION_MACHOMAN;
import static seedu.address.logic.commands.session.SessionCommandTestUtil.VALID_EXERCISE_TYPE_MACHOMAN;
import static seedu.address.logic.commands.session.SessionCommandTestUtil.VALID_GYM_MACHOMAN;
import static seedu.address.logic.commands.session.SessionCommandTestUtil.VALID_START_TIME_MACHOMAN;
import static seedu.address.testutil.TypicalSessions.GETWELL;
import static seedu.address.testutil.TypicalSessions.MACHOMAN;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.session.SessionParserUtil;
import seedu.address.testutil.SessionBuilder;

public class SessionTest {

    @Test
    public void testLoadFromStorageConstructor() {
        try {
            LocalDateTime start = SessionParserUtil.parseStringToDateTime(VALID_START_TIME_MACHOMAN);
            Session machoman = new Session(
                    VALID_GYM_MACHOMAN,
                    VALID_EXERCISE_TYPE_MACHOMAN,
                    start,
                    Integer.parseInt(VALID_DURATION_MACHOMAN)
            );
            assertTrue(MACHOMAN.equals(machoman));
        } catch (Exception e) {
            // Should never reach here. If you get here, check the data in SessionCommandTestUtil
            throw new AssertionError("This should not be called!");
        }

    }

    @Test
    public void isUniqueSession() {
        // same object -> returns true
        assertTrue(GETWELL.isUnique(GETWELL));

        // null -> returns false
        assertFalse(GETWELL.isUnique(null));

        // different exerciseType -> returns false
        Session editedGetwell = new SessionBuilder(GETWELL).withExerciseType(VALID_EXERCISE_TYPE_MACHOMAN).build();
        assertFalse(GETWELL.isUnique(editedGetwell));

        // different gym -> returns false
        editedGetwell = new SessionBuilder(GETWELL).withGym(VALID_GYM_MACHOMAN).build();
        assertFalse(GETWELL.isUnique(editedGetwell));

        // non-overlapping interval -> returns true
        editedGetwell = new SessionBuilder(GETWELL)
                .withInterval(VALID_START_TIME_MACHOMAN, VALID_DURATION_MACHOMAN).build();
        assertTrue(GETWELL.isUnique(editedGetwell));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Session aliceCopy = new SessionBuilder(GETWELL).build();
        assertTrue(GETWELL.equals(aliceCopy));

        // same object -> returns true
        assertTrue(GETWELL.equals(GETWELL));

        // null -> returns false
        assertFalse(GETWELL.equals(null));

        // different type -> returns false
        assertFalse(GETWELL.equals(5));

        // different Session -> returns false
        assertFalse(GETWELL.equals(MACHOMAN));

        // different gym -> returns false
        Session editedGetwell = new SessionBuilder(GETWELL).withGym(VALID_GYM_MACHOMAN).build();
        assertFalse(GETWELL.equals(editedGetwell));

        // different exerciseType -> returns false
        editedGetwell = new SessionBuilder(GETWELL).withExerciseType(VALID_EXERCISE_TYPE_MACHOMAN).build();
        assertFalse(GETWELL.equals(editedGetwell));

        // different interval -> returns false
        editedGetwell = new SessionBuilder(GETWELL)
                .withInterval(VALID_START_TIME_MACHOMAN, VALID_DURATION_MACHOMAN).build();
        assertFalse(GETWELL.equals(editedGetwell));
    }
}
