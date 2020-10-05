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
        assertThrows(NullPointerException.class, () -> Time.isValidDeadline(null));

        // invalid phone numbers
        assertFalse(Time.isValidDeadline("")); // empty string
        assertFalse(Time.isValidDeadline(" ")); // spaces only
        assertFalse(Time.isValidDeadline("1200 1999-10-10")); // invalid time

        // valid phone numbers
        assertTrue(Time.isValidDeadline("01-01-2020 1200"));
        assertTrue(Time.isValidDeadline("02-02-2019 2359"));
        assertTrue(Time.isValidDeadline("30-12-2021 1200"));
    }
}
