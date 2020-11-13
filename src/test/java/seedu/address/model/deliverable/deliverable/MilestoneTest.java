package seedu.address.model.deliverable.deliverable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MilestoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Milestone(null));
    }

    @Test
    public void constructor_invalidMilestone_throwsIllegalArgumentException() {
        String invalidMilestone = "";
        assertThrows(IllegalArgumentException.class, () -> new Milestone(invalidMilestone));
    }

    @Test
    public void isValidMilestone() {
        // null milestone
        assertThrows(NullPointerException.class, () -> Milestone.isValidMilestone(null));

        // blank milestone
        assertFalse(Milestone.isValidMilestone("")); // empty string
        assertFalse(Milestone.isValidMilestone(" ")); // only spaces

        // invalid characters
        assertFalse(Milestone.isValidMilestone("hello")); // alphabets
        assertFalse(Milestone.isValidMilestone("!@#$%^&*(),")); // special characters

        // invalid format
        assertFalse(Milestone.isValidMilestone(".1.1")); // does not start with number
        assertFalse(Milestone.isValidMilestone("1.1.")); // does not end with number
        assertFalse(Milestone.isValidMilestone("1..1")); // consecutive dots

        // valid milestone
        assertTrue(Milestone.isValidMilestone("1")); // simplest milestone
        assertTrue(Milestone.isValidMilestone("1.1")); // standard milestone
        assertTrue(Milestone.isValidMilestone("1.1.1.1.1.1.1.1.1.1")); // very long milestone
        assertTrue(Milestone.isValidMilestone("10.10.111")); // multiple digit numbers
    }
}
