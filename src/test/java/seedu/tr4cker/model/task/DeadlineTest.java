package seedu.tr4cker.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null, false));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDeadline, false));
    }

    @Test
    public void isValidDeadline() {
        // null deadline time
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // invalid deadline times
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only
        assertFalse(Deadline.isValidDeadline("02-12-2021")); // only has date
        assertFalse(Deadline.isValidDeadline("deadline")); // non-numeric
        assertFalse(Deadline.isValidDeadline("02-12-2021   0000")); // additional spaces within digits
        assertFalse(Deadline.isValidDeadline("10/10/2021 2359")); // wrong format
        assertFalse(Deadline.isValidDeadline("29/02/2021 2359")); // invalid leap year

        // valid deadline times
        assertTrue(Deadline.isValidDeadline("02-12-2021 0000"));
        assertTrue(Deadline.isValidDeadline("10-Oct-2021 2359"));
        assertTrue(Deadline.isValidDeadline("20-Sep-2021 1800"));
    }

    @Test
    public void isDeadlineWithTime() {
        // invalid deadline times
        assertFalse(Deadline.isDeadlineWithTime("20-Sept-2021 1900")); // additional alphabets
        assertFalse(Deadline.isDeadlineWithTime("201-091-20211")); // additional numbers
        assertFalse(Deadline.isDeadlineWithTime("20-Sep-2021")); // no time
        assertFalse(Deadline.isDeadlineWithTime("20-09-2021")); // no time

        // valid deadline times
        assertTrue(Deadline.isDeadlineWithTime("02-12-2021 0000"));
        assertTrue(Deadline.isDeadlineWithTime("02-Dec-2021 0000"));
    }

    @Test
    public void isFutureDeadline() {
        // invalid deadline times
        assertFalse(Deadline.isFutureDeadline("20-Sep-2020 1900")); // time has passed

        // valid deadline times
        assertTrue(Deadline.isDeadlineWithTime("02-12-2030 0000"));
    }

    @Test
    public void testHashcode() {
        Deadline deadline1 = new Deadline("02-12-2021 1800", false);
        Deadline deadline2 = new Deadline("02-12-2021 1800", false);
        assertEquals(deadline1.hashCode(), deadline2.hashCode());
    }

}
