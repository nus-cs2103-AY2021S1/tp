package seedu.address.model.task.deadline;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DurationTest {

    @Test
    public void constructor_invalidTitle_throwsIllegalArgumentException() {
        int invalidDuration = -10;
        assertThrows(IllegalArgumentException.class, () -> new Duration(invalidDuration));
    }

    @Test
    public void isValidTitle() {

        // invalid duration
        assertFalse(Duration.isValidDuration(-10)); // negative number

        // valid duration
        assertTrue(Duration.isValidDuration(Duration.NULL_VALUE)); // default value
        assertTrue(Duration.isValidDuration(120)); // positive numbers only
    }
}
