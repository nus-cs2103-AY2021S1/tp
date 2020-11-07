package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriorityTest {

    @Test
    public void isValidPriority() {
        // null Priority
        assertThrows(NullPointerException.class, () -> Priority.isValidPriority(null));

        // invalid Priority
        assertFalse(Priority.isValidPriority("")); // empty string
        assertFalse(Priority.isValidPriority(" ")); // spaces only
        assertFalse(Priority.isValidPriority("$")); // only non-alphanumeric characters
        assertFalse(Priority.isValidPriority("peter*")); // contains non-alphanumeric characters
        assertFalse(Priority.isValidPriority("1234")); // contains all number
        assertFalse(Priority.isValidPriority("H I G H")); // contains whitespaces in between
        assertFalse(Priority.isValidPriority("HIG H")); // contains one whitespace
        assertFalse(Priority.isValidPriority(" HIGH")); // contains whitespace in the front
        assertFalse(Priority.isValidPriority("HIGH ")); // contains trailing whitespace

        // valid Priority, all uppercase
        assertTrue(Priority.isValidPriority("HIGH"));
        assertTrue(Priority.isValidPriority("NORMAL"));
        assertTrue(Priority.isValidPriority("LOW"));
    }
}
