package seedu.address.model.project;

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
        // incorrect format: missing "-", ":"
        assertFalse(Deadline.isValidDeadline("22031999230000"));
        // incorrect format: using "/",  instead of "-"
        assertFalse(Deadline.isValidDeadline("22/03/1999 23:00:00"));
        // incorrect format: using ",",  instead of ":"
        assertFalse(Deadline.isValidDeadline("22-03-1999 23,00,00"));
        // incorrect format: missing " " between date and time
        assertFalse(Deadline.isValidDeadline("22-03-199923:00:00"));
        // incorrect format: more than 2 digits of integers for day
        assertFalse(Deadline.isValidDeadline("001-03-1999 23:00:00"));
        // incorrect format: less than 2 digits of integers for day
        assertFalse(Deadline.isValidDeadline("4-03-1999 23:00:00"));
        // incorrect format: more than 2 digits of integers for month
        assertFalse(Deadline.isValidDeadline("14-003-1999 23:00:00"));
        // incorrect format: less than 2 digits of integers for day
        assertFalse(Deadline.isValidDeadline("14-3-1999 23:00:00"));
        // incorrect format: less than 4 digits of integers for day
        assertFalse(Deadline.isValidDeadline("14-03-999 23:00:00"));
        // incorrect format: less than 2 digits of integers for hour
        assertFalse(Deadline.isValidDeadline("14-03-1999 2:00:00"));
        // incorrect format: more than 2 digits of integers for hour
        assertFalse(Deadline.isValidDeadline("14-03-1999 200:00:00"));
        // incorrect format: less than 2 digits of integers for minute
        assertFalse(Deadline.isValidDeadline("14-03-1999 20:1:00"));
        // incorrect format: more than 2 digits of integers for minute
        assertFalse(Deadline.isValidDeadline("14-03-1999 20:100:00"));
        // incorrect format: less than 2 digits of integers for second
        assertFalse(Deadline.isValidDeadline("14-03-1999 20:00:1"));
        // incorrect format: more than 2 digits of integers for second
        assertFalse(Deadline.isValidDeadline("14-03-1999 20:00:100"));
        // incorrect format: incorrect day
        assertFalse(Deadline.isValidDeadline("40-03-1999 23:00:00"));
        // incorrect format: incorrect month
        assertFalse(Deadline.isValidDeadline("31-13-1999 23:00:00"));
        // incorrect format: incorrect year, more than 4 digits of integers for year
        assertFalse(Deadline.isValidDeadline("29-03-10000 23:00:00"));
        // incorrect format: incorrect day of February
        assertFalse(Deadline.isValidDeadline("29-02-2019 23:00:00"));
        // incorrect format: incorrect day of February
        assertFalse(Deadline.isValidDeadline("30-02-2019 23:00:00"));
        // incorrect format: incorrect day of February
        assertFalse(Deadline.isValidDeadline("31-02-2019 23:00:00"));
        // incorrect format: incorrect day of April
        assertFalse(Deadline.isValidDeadline("31-04-2019 23:00:00"));
        // incorrect format: incorrect day of June
        assertFalse(Deadline.isValidDeadline("31-06-2019 23:00:00"));
        // incorrect format: incorrect day of September
        assertFalse(Deadline.isValidDeadline("31-09-2019 23:00:00"));
        // incorrect format: incorrect day of November
        assertFalse(Deadline.isValidDeadline("31-11-2019 23:00:00"));
        // incorrect format: incorrect hour
        assertFalse(Deadline.isValidDeadline("20-03-1999 24:00:00"));
        // incorrect format: incorrect minute
        assertFalse(Deadline.isValidDeadline("20-03-1999 23:80:00"));
        // incorrect format: incorrect second
        assertFalse(Deadline.isValidDeadline("20-03-1999 23:00:99"));

        // valid deadlines
        assertTrue(Deadline.isValidDeadline("20-03-1999 23:00:00")); // correct format
        assertTrue(Deadline.isValidDeadline("29-02-2020 23:00:00")); // correct day in February
        assertTrue(Deadline.isValidDeadline("31-01-2020 23:00:00")); // correct day in January
        assertTrue(Deadline.isValidDeadline("31-03-2020 23:00:00")); // correct day in March
        assertTrue(Deadline.isValidDeadline("31-05-2020 23:00:00")); // correct day in May
        assertTrue(Deadline.isValidDeadline("31-07-2020 23:00:00")); // correct day in July
        assertTrue(Deadline.isValidDeadline("31-08-2020 23:00:00")); // correct day in August
        assertTrue(Deadline.isValidDeadline("31-10-2020 23:00:00")); // correct day in October
        assertTrue(Deadline.isValidDeadline("31-12-2020 23:00:00")); // correct day in December
        assertTrue(Deadline.isValidDeadline("29-02-2020 00:00:00")); // correct time
    }
}
