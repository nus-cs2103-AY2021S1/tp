package seedu.address.model.information;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriorityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Priority(null));
    }

    @Test
    public void constructor_invalidPriority_throwsIllegalArgumentException() {
        String invalidPriority = "urgent";
        assertThrows(IllegalArgumentException.class, () -> new Priority(invalidPriority));
    }

    @Test
    public void isValidPriority() {
        // null priority
        assertThrows(NullPointerException.class, () -> Priority.isValidPriority(null));

        // blank priority
        assertFalse(Priority.isValidPriority("")); // empty string
        assertFalse(Priority.isValidPriority(" ")); // spaces only

        // invalid priority values
        assertFalse(Priority.isValidPriority("urgent")); // not accepted value

        // valid priority
        assertTrue(Priority.isValidPriority("low")); // low priority
        assertTrue(Priority.isValidPriority("moderate")); // moderate priority
        assertTrue(Priority.isValidPriority("high")); // high priority
    }
}
