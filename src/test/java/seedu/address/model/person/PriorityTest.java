package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriorityTest {

    @Test
    public void constructor_invalidPriority_throwsIllegalArgumentException() {
        String invalidPriority = "x";
        assertThrows(IllegalArgumentException.class, () -> new Priority(invalidPriority));
    }

    @Test
    public void isValidPriority() {
        // invalid priority
        assertFalse(Priority.isValidPriority("")); // empty string
        assertFalse(Priority.isValidPriority(" ")); // spaces only
        assertFalse(Priority.isValidPriority("X")); // Not supposed to be X
        assertFalse(Priority.isValidPriority("Low")); // capital letters should NOT work


        // valid priority numbers
        assertTrue(Priority.isValidPriority("L")); // L
        assertTrue(Priority.isValidPriority("l")); // Small letters should also work
        assertTrue(Priority.isValidPriority("low")); // small letters should work
    }

    @Test
    public void emptyPriority_equals_undefined() {
        assertEquals(new Priority(null).value, new Priority("u").value);
    }
}
