package seedu.address.model.meeting;

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
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidFormat() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidFormat(null));

        // invalid format
        assertFalse(Date.isValidFormat("")); // empty string
        assertFalse(Date.isValidFormat(" ")); // spaces only
        assertFalse(Date.isValidFormat("")); // only non-alphanumeric characters
        assertFalse(Date.isValidFormat("peter*")); // contains non-alphanumeric characters
        assertFalse(Date.isValidFormat("abcd-12-12")); // year portion contains alphabets
        assertFalse(Date.isValidFormat("2012-ab-12")); // month portion contains alphabets
        assertFalse(Date.isValidFormat("2012-12-ab")); // date portion contains alphabets
        assertFalse(Date.isValidFormat("2012212-12")); // "-" not existent at 5th char
        assertFalse(Date.isValidFormat("2012-12012")); // "-" not existent at 8th char
        assertFalse(Date.isValidFormat("1234546789042456")); //

        //valid format
        assertTrue(Date.isValidFormat("1234-56-78")); // follows validation regex
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid date
        assertFalse(Date.isValidDate("2020-12-50"));
        assertFalse(Date.isValidDate("2020-17-12"));
        assertFalse(Date.isValidDate("0000-08-19"));
        assertFalse(Date.isValidDate("2020-00-10"));

        // valid date
        assertTrue(Date.isValidDate("2020-10-12"));
        assertTrue(Date.isValidDate("2000-11-03"));
        assertTrue(Date.isValidDate("1800-07-30"));
        assertTrue(Date.isValidDate("2700-02-12"));
    }

    @Test
    public void isValidDateInput() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDateInput(null));

        // invalid date
        assertFalse(Date.isValidDateInput("1234567890"));
        assertFalse(Date.isValidDateInput("abcd-fg-hi"));

        // valid date
        assertTrue(Date.isValidDateInput("1790-12-30"));
        assertTrue(Date.isValidDateInput("9999-12-04"));
    }
}
