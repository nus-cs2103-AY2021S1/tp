package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTimeTest {

    @Test
    public void isValidDateTime() {
        // null dateTime number
        assertThrows(NullPointerException.class, () -> DateTime.isValidDateTime(null));

        // invalid dateTime numbers
        assertFalse(DateTime.isValidDateTime("")); // empty string
        assertFalse(DateTime.isValidDateTime(" ")); // spaces only
        assertFalse(DateTime.isValidDateTime("12-13-2000 12:00")); // month greater than 12
        assertFalse(DateTime.isValidDateTime("date")); // non-numeric
        assertFalse(DateTime.isValidDateTime("32-01-1200")); // day greater than 31
        assertFalse(DateTime.isValidDateTime("12-12-2020")); // no time
        assertFalse(DateTime.isValidDateTime("5-6-2020 12:00")); // no leading zero for day and month

        // valid dateTime numbers
        assertTrue(DateTime.isValidDateTime("05-09-2020 18:00")); // date and month with leading zero
        assertTrue(DateTime.isValidDateTime("12-12-2020 12:00"));
    }
}
