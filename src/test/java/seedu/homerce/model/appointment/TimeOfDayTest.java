package seedu.homerce.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeOfDayTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TimeOfDay(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new TimeOfDay(invalidTime));
    }

    @Test
    public void isValidTime() {
        // null date
        assertThrows(NullPointerException.class, () -> TimeOfDay.isValidTime(null));

        // invalid times of the day
        assertFalse(TimeOfDay.isValidTime("")); // empty string
        assertFalse(TimeOfDay.isValidTime(" ")); // spaces only
        assertFalse(TimeOfDay.isValidTime("2515")); // invalid hour
        assertFalse(TimeOfDay.isValidTime("1270")); // invalid minutes
        assertFalse(TimeOfDay.isValidTime("13")); // input must have 4 digits
        assertFalse(TimeOfDay.isValidTime("915")); // input must have 4 digits
        assertFalse(TimeOfDay.isValidTime("12:34")); // invalid format

        // valid times of the day
        assertTrue(TimeOfDay.isValidTime("0000"));
        assertTrue(TimeOfDay.isValidTime("0910"));
        assertTrue(TimeOfDay.isValidTime("1200"));
        assertTrue(TimeOfDay.isValidTime("2359"));
    }

    @Test
    public void toStringTest() {
        TimeOfDay time1 = new TimeOfDay("1745");
        TimeOfDay time2 = new TimeOfDay("1030");
        assertEquals("1745", time1.toString());
        assertEquals("1030", time2.toString());
    }

    @Test
    public void toUiStringTest() {
        TimeOfDay time1 = new TimeOfDay("1234");
        TimeOfDay time2 = new TimeOfDay("0001");
        assertEquals("12:34 pm", time1.toUiString());
        assertEquals("12:01 am", time2.toUiString());
    }

    @Test
    public void equalityTest() {
        TimeOfDay time1 = new TimeOfDay("0900");
        TimeOfDay time2 = new TimeOfDay("0900");
        TimeOfDay time3 = new TimeOfDay("2100");
        assertEquals(time1, time2);
        assertNotEquals(time2, time3);
    }

    @Test
    public void hashCodeTest() {
        TimeOfDay time1 = new TimeOfDay("0900");
        TimeOfDay time2 = new TimeOfDay("0900");
        TimeOfDay time3 = new TimeOfDay("2100");
        TimeOfDay time4 = new TimeOfDay("1200");
        assertEquals(time1.hashCode(), time2.hashCode());
        assertNotEquals(time2.hashCode(), time3.hashCode());
        assertEquals(time4.hashCode(), time4.hashCode());
    }
}
