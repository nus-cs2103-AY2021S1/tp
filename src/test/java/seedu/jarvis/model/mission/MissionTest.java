package seedu.jarvis.model.mission;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.testutil.TypicalMissions.FRACTAL_DIMENSIONS;
import static seedu.jarvis.testutil.TypicalMissions.MUSICAL_NOTES;

import org.junit.jupiter.api.Test;

import seedu.jarvis.testutil.MissionBuilder;

class MissionTest {

    @Test
    void getTitle() {
        assertTrue(FRACTAL_DIMENSIONS.getTitle().equals("Fractal Dimensions"));
    }

    @Test
    void getDeadline() {
        assertTrue(FRACTAL_DIMENSIONS.getDeadline().equals("Due: 26th August, 23:59"));
    }

    @Test
    void testEquals() {
        // same values -> return true
        assertTrue(FRACTAL_DIMENSIONS.equals(FRACTAL_DIMENSIONS));

        // same object -> return true
        assertTrue(FRACTAL_DIMENSIONS.equals(FRACTAL_DIMENSIONS));

        // null -> returns false
        assertFalse(FRACTAL_DIMENSIONS.equals(null));

        // different type -> returns false
        assertFalse(FRACTAL_DIMENSIONS.equals(5));

        // different mission -> returns false
        assertFalse(FRACTAL_DIMENSIONS.equals(MUSICAL_NOTES));

        // different deadline -> returns false
        Mission editedFractualDimensions = new MissionBuilder(FRACTAL_DIMENSIONS)
                .withDeadline("Due: 20th October, 23:59").build();
        assertFalse(editedFractualDimensions.equals(FRACTAL_DIMENSIONS));

        // different isGraded -> return false
        Mission editedMusicalNotes = new MissionBuilder(MUSICAL_NOTES).withIsGraded(false).build();
        assertFalse(editedMusicalNotes.equals(MUSICAL_NOTES));
    }

    @Test
    void testToString() {
        String missionTestToString = FRACTAL_DIMENSIONS.getTitle() + " Deadline: " + FRACTAL_DIMENSIONS.getDeadline();
        assertTrue(FRACTAL_DIMENSIONS.toString().equals(missionTestToString));
    }
}
