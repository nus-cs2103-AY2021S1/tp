package seedu.address.model.task.deadline;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DurationTest {

    @Test
    public void constructor_invalidDuration_throwsIllegalArgumentException() {
        int invalidDuration = -10;
        assertThrows(IllegalArgumentException.class, () -> new Duration(invalidDuration));
    }

    @Test
    public void isValidDuration() {

        // invalid duration
        assertFalse(Duration.isValidDuration(-10)); // negative number
        assertFalse(Duration.isValidDuration(-1)); // negative number
        assertFalse(Duration.isValidDuration(0)); // negative number
        assertFalse(Duration.isValidDuration(Duration.NULL_VALUE)); // default value

        // valid duration
        assertTrue(Duration.isValidDuration(1)); // smallest positive number
        assertTrue(Duration.isValidDuration(120)); // positive numbers only
        assertTrue(Duration.isValidDuration(Integer.MAX_VALUE)); // maximum boundary value
    }
}
