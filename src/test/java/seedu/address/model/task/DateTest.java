package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null date number
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid date numbers
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("12-13-2000-1200")); // month greater than 12
        assertFalse(Date.isValidDate("date")); // non-numeric
        assertFalse(Date.isValidDate("32-01-1200")); // day greater than 12
        assertFalse(Date.isValidDate("12-12-2020")); // no time
        assertFalse(Date.isValidDate("5-6-2020-1200")); // no leading zero for day and month

        // valid date numbers
        assertTrue(Date.isValidDate("05-09-2020-1800")); // date and month with leading zero
        assertTrue(Date.isValidDate("12-12-2020-1200"));
    }
}
