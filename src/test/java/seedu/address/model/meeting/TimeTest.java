package seedu.address.model.meeting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class TimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime));
    }

    @Test
    public void isValidFormat() {
        // null Time
        assertThrows(NullPointerException.class, () -> Time.isValidFormat(null));

        // invalid format
        assertFalse(Time.isValidFormat("")); // empty string
        assertFalse(Time.isValidFormat(" ")); // spaces only
        assertFalse(Time.isValidFormat("1234"));
        assertFalse(Time.isValidFormat("abcde"));
        assertFalse(Time.isValidFormat("00-00"));
        assertFalse(Time.isValidFormat("123:00"));
        assertFalse(Time.isValidFormat("12:000"));

        //valid format
        assertTrue(Time.isValidFormat("90:10")); // follows validation regex
        assertTrue(Time.isValidFormat("12:00")); // follows validation regex
    }

    @Test
    public void isValidTime() {
        // null Time
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid Time
        assertFalse(Time.isValidTime("24:00"));
        assertFalse(Time.isValidTime("55:00"));
        assertFalse(Time.isValidTime("12:60"));
        assertFalse(Time.isValidTime("12:61"));

        // valid Time
        assertTrue(Time.isValidTime("00:00"));
        assertTrue(Time.isValidTime("23:59"));
        assertTrue(Time.isValidTime("12:00"));
    }
}
