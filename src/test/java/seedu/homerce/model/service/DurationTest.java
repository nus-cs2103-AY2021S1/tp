package seedu.homerce.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DurationTest {
    @Test
    public void constructorNullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Duration(null));
    }

    @Test
    public void constructorInvalidDurationThrowsIllegalArgumentException() {
        double invalidDurationZero = 0;
        assertThrows(IllegalArgumentException.class, () -> new Duration(invalidDurationZero));

        double invalidDurationExceedHours = 25;
        assertThrows(IllegalArgumentException.class, () -> new Duration(invalidDurationExceedHours));

        double invalidDurationNegativeHours = -1;
        assertThrows(IllegalArgumentException.class, () -> new Duration(invalidDurationNegativeHours));

        double invalidDurationNotHalfHourInterval = 1.8;
        assertThrows(IllegalArgumentException.class, () -> new Duration(invalidDurationNotHalfHourInterval));
    }


    @Test
    public void isValidDuration() {

        // null Duration
        assertThrows(NullPointerException.class, () -> Duration.isValidDuration(null));

        // Invalid Duration
        assertFalse(Duration.isValidDuration(0.0)); // Zero duration
        assertFalse(Duration.isValidDuration(-5.0)); // Negative Duration
        assertFalse(Duration.isValidDuration(25.0));
        assertFalse(Duration.isValidDuration(1.8)); // Non-half hour interval

        //Valid Duration
        assertTrue(Duration.isValidDuration(1.0)); // Normal case
        assertTrue(Duration.isValidDuration(1.5)); // Only 1 digit
        assertTrue(Duration.isValidDuration(0023.5)); // When contains leading zeros
        assertTrue(Duration.isValidDuration(2.5000)); // Additional 0s
    }

    @Test
    public void testToString() {
        assertEquals("2.0", new Duration(2.0).toString());
    }

    @Test
    public void testEquals() {
        assertNotEquals(new Duration(1.5), null); // Compare with null
        assertNotEquals(new Duration(1.5), new Duration(3.5)); // Different durations
        assertEquals(new Duration(5.0), new Duration(5.0)); // Same durations
    }

    @Test
    public void testHashCode() {
        // Equal Hashcode
        assertEquals(new Duration(5.5).hashCode(), new Duration(5.5).hashCode());

        //Non Equal Hashcode
        assertNotEquals(new Duration(5.5).hashCode(), new Duration(0.5).hashCode());
    }
}
