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
        assertFalse(Priority.isValidPriority("h i g h")); // contains whitespaces in between
        assertFalse(Priority.isValidPriority("hig h")); // contains one whitespace
        assertFalse(Priority.isValidPriority(" high")); // contains whitespace in the front
        assertFalse(Priority.isValidPriority("high ")); // contains trailing whitespace

        // valid Priority, all uppercase
        assertTrue(Priority.isValidPriority("HIGH"));
        assertTrue(Priority.isValidPriority("NORMAL"));
        assertTrue(Priority.isValidPriority("LOW"));

        // valid Priority, all lowercase
        assertTrue(Priority.isValidPriority("high"));
        assertTrue(Priority.isValidPriority("normal"));
        assertTrue(Priority.isValidPriority("low"));

        // valid Priority, mixed
        assertTrue(Priority.isValidPriority("High"));
        assertTrue(Priority.isValidPriority("hIgH"));
        assertTrue(Priority.isValidPriority("HiGh"));

        assertTrue(Priority.isValidPriority("Normal"));
        assertTrue(Priority.isValidPriority("nOrMaL"));
        assertTrue(Priority.isValidPriority("NoRmAl"));

        assertTrue(Priority.isValidPriority("Low"));
        assertTrue(Priority.isValidPriority("lOw"));
        assertTrue(Priority.isValidPriority("LoW"));
    }
}
