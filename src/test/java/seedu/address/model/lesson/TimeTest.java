package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime));
    }

    @Test
    public void isValidTime() {
        // null time
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid time
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("1200")); //no colon
        assertFalse(Time.isValidTime("24:00")); //wrong hour
        assertFalse(Time.isValidTime("12:60")); //wrong minute
        assertFalse(Time.isValidTime("1:00")); //incomplete hour
        assertFalse(Time.isValidTime("12:0")); //incomplete minute

        // valid time
        assertTrue(Time.isValidTime("00:00"));
        assertTrue(Time.isValidTime("23:59"));
    }
}
