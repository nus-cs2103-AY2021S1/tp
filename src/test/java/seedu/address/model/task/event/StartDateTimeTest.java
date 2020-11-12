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
        assertThrows(NullPointerException.class, () -> StartDateTime.isValidDateTime(null));

        // invalid dateTime numbers
        assertFalse(StartDateTime.isValidDateTime("")); // empty string
        assertFalse(StartDateTime.isValidDateTime(" ")); // spaces only
        assertFalse(StartDateTime.isValidDateTime("12-13-2000 12:00")); // month greater than 12
        assertFalse(StartDateTime.isValidDateTime("date")); // non-numeric
        assertFalse(StartDateTime.isValidDateTime("32-01-1200")); // day greater than 31
        assertFalse(StartDateTime.isValidDateTime("12-12-2020")); // no time
        assertFalse(StartDateTime.isValidDateTime("5-6-2020 12:00")); // no leading zero for day and month

        // valid dateTime numbers
        assertTrue(StartDateTime.isValidDateTime("05-09-2020 18:00")); // date and month with leading zero
        assertTrue(StartDateTime.isValidDateTime("12-12-2020 12:00"));
    }
}
