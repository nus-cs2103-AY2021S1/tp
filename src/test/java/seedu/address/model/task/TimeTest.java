package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time((String) null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidDeadline));
    }

    @Test
    public void isValidDeadline() {
        // null deadline
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid deadlines
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("00:00")); // time only
        assertFalse(Time.isValidTime("deadline")); // non-numeric
        assertFalse(Time.isValidTime("01-01-2020")); // date only
        assertFalse(Time.isValidTime("2a-02-2O2O aa00")); // alphabets within digits
        assertFalse(Time.isValidTime("2*-02-2O2O #a00")); // special characters within digits
        assertFalse(Time.isValidTime("01- 01-2020 1800")); // spaces within date
        assertFalse(Time.isValidTime("01-01-2020 18 00")); // spaces within time
        assertFalse(Time.isValidTime("00-00-0000 0000")); // all zeros
        assertFalse(Time.isValidTime("31-02-2020 1800")); // invalid day of month
        assertFalse(Time.isValidTime("01-99-2020 1800")); // invalid month
        assertFalse(Time.isValidTime("01-01-99999 1800")); // invalid year
        assertFalse(Time.isValidTime("01-01-2020 2400")); // invalid hour
        assertFalse(Time.isValidTime("01-01-2020 1860")); // invalid minute
        assertFalse(Time.isValidTime("2020-01-01 1800")); // invalid format ("uuuu-MM-dd HHmm")
        assertFalse(Time.isValidTime("04-30-2020 1800")); // invalid format ("MM-dd-uuuu HHmm")
        assertFalse(Time.isValidTime("1-01-2020 1800")); // invalid format ("d-MM-uuuu HHmm")
        assertFalse(Time.isValidTime("01-1-2020 1800")); // invalid format ("dd-M-uuuu HHmm")
        assertFalse(Time.isValidTime("01-01-20 1800")); // invalid format ("dd-MM-y HHmm")
        assertFalse(Time.isValidTime("01-01-20 18:00")); // invalid format ("dd-MM-y HH:mm")
        assertFalse(Time.isValidTime("01-01-2020 06:00 PM")); // invalid format ("dd-MM-uuuu hh:mm a")

        // valid deadline dates and time
        assertTrue(Time.isValidTime("30-04-2020 1800")); // exactly of format "dd-MM-uuuu HHmm"
        assertTrue(Time.isValidTime("31-12-2020 2359"));
    }
}
