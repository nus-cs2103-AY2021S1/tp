package seedu.tr4cker.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskDescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDescription(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskDescription(invalidDescription));
    }

    @Test
    public void isValidTaskDescription() {
        // null tr4cker
        assertThrows(NullPointerException.class, () -> TaskDescription.isValidTaskDescription(null));

        // invalid descriptions
        assertFalse(TaskDescription.isValidTaskDescription("")); // empty string
        assertFalse(TaskDescription.isValidTaskDescription(" ")); // spaces only

        // valid descriptions
        assertTrue(TaskDescription.isValidTaskDescription("description"));
        assertTrue(TaskDescription.isValidTaskDescription("-")); // one character
        assertTrue(TaskDescription.isValidTaskDescription("Do all assignments and quizzes, "
                + "assignment 1 is blahblahblahblahblah")); // long tr4cker
    }

    @Test
    public void testHashcode() {
        TaskDescription taskDescription1 = new TaskDescription("description");
        TaskDescription taskDescription2 = new TaskDescription("description");
        assertEquals(taskDescription1.hashCode(), taskDescription2.hashCode());
    }

}
