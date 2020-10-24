package seedu.address.model.task.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StartDateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StartDateTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidStartDateTime = "12-30-2020 12:12";
        assertThrows(IllegalArgumentException.class, () -> new StartDateTime(invalidStartDateTime));
    }

    @Test
    public void isValidDateTime() {
        // null dateTime number
        assertThrows(NullPointerException.class, () -> StartDateTime.isValidStartDateTime(null));

        // invalid dateTime numbers
        assertFalse(StartDateTime.isValidStartDateTime("")); // empty string
        assertFalse(StartDateTime.isValidStartDateTime(" ")); // spaces only
        assertFalse(StartDateTime.isValidStartDateTime("12-13-2000 12:00")); // month greater than 12
        assertFalse(StartDateTime.isValidStartDateTime("date")); // non-numeric
        assertFalse(StartDateTime.isValidStartDateTime("32-01-1200")); // day greater than 31
        assertFalse(StartDateTime.isValidStartDateTime("12-12-2020")); // no time
        assertFalse(StartDateTime.isValidStartDateTime("5-6-2020 12:00")); // no leading zero for day and month

        // valid dateTime numbers
        assertTrue(StartDateTime.isValidStartDateTime("05-09-2020 18:00")); // date and month with leading zero
        assertTrue(StartDateTime.isValidStartDateTime("12-12-2020 12:00"));
    }
}
