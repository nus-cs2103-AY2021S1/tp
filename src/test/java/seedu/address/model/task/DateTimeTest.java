package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new DateTime(invalidDate));
    }

    @Test
    public void isValidDateTime() {
        // null date number
        assertThrows(NullPointerException.class, () -> DateTime.isValidDateTime(null));

        // invalid date numbers
        assertFalse(DateTime.isValidDateTime("")); // empty string
        assertFalse(DateTime.isValidDateTime(" ")); // spaces only
        assertFalse(DateTime.isValidDateTime("12-13-2000-1200")); // month greater than 12
        assertFalse(DateTime.isValidDateTime("date")); // non-numeric
        assertFalse(DateTime.isValidDateTime("32-01-1200")); // day greater than 31
        assertFalse(DateTime.isValidDateTime("12-12-2020")); // no time
        assertFalse(DateTime.isValidDateTime("5-6-2020-1200")); // no leading zero for day and month

        // valid date numbers
        assertTrue(DateTime.isValidDateTime("05-09-2020-1800")); // date and month with leading zero
        assertTrue(DateTime.isValidDateTime("12-12-2020-1200"));
    }
}
