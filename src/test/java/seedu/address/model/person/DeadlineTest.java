package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDeadline));
    }

    @Test
    public void isValidDeadline() {
        // null deadline
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // invalid deadlines
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only
        assertFalse(Deadline.isValidDeadline("00:00")); // time only
        assertFalse(Deadline.isValidDeadline("deadline")); // non-numeric
        assertFalse(Deadline.isValidDeadline("01-01-2020")); // date only
        assertFalse(Deadline.isValidDeadline("2a-02-2O2O aa:00")); // alphabets within digits
        assertFalse(Deadline.isValidDeadline("2*-02-2O2O #a:00")); // special characters within digits
        assertFalse(Deadline.isValidDeadline("01- 01-2020 18:00")); // spaces within date
        assertFalse(Deadline.isValidDeadline("01-01-2020 18 : 00")); // spaces within time
        assertFalse(Deadline.isValidDeadline("00-00-0000 00:00")); // all zeros
        assertFalse(Deadline.isValidDeadline("31-02-2020 18:00")); // invalid day of month
        assertFalse(Deadline.isValidDeadline("01-99-2020 18:00")); // invalid month
        assertFalse(Deadline.isValidDeadline("01-01-99999 18:00")); // invalid year
        assertFalse(Deadline.isValidDeadline("01-01-2020 24:00")); // invalid hour
        assertFalse(Deadline.isValidDeadline("01-01-2020 18:60")); // invalid minute
        assertFalse(Deadline.isValidDeadline("2020-01-01 18:00")); // invalid format ("uuuu-MM-dd HH:mm")
        assertFalse(Deadline.isValidDeadline("04-30-2020 18:00")); // invalid format ("MM-dd-uuuu HH:mm")
        assertFalse(Deadline.isValidDeadline("1-01-2020 18:00")); // invalid format ("d-MM-uuuu HH:mm")
        assertFalse(Deadline.isValidDeadline("01-1-2020 18:00")); // invalid format ("dd-M-uuuu HH:mm")
        assertFalse(Deadline.isValidDeadline("01-01-20 18:00")); // invalid format ("dd-MM-y HH:mm")
        assertFalse(Deadline.isValidDeadline("01-01-2020 06:00 PM")); // invalid format ("dd-MM-uuuu hh:mm a")

        // valid phone numbers
        assertTrue(Deadline.isValidDeadline("30-04-2020 18:00")); // exactly of format "dd-MM-uuuu HH:mm"
        assertTrue(Deadline.isValidDeadline("31-12-2020 23:59"));
    }
}
