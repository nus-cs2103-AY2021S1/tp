package seedu.address.model.information;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        String emptyDate = "";
        String negativeDate = "-1";
        String wordDate = "3 July 2020";
        assertThrows(IllegalArgumentException.class, () -> new Date(emptyDate));
        assertThrows(IllegalArgumentException.class, () -> new Date(negativeDate));
        assertThrows(IllegalArgumentException.class, () -> new Date(wordDate));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid date
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate("  ")); // spaces only
        assertFalse(Date.isValidDate("12/12/12")); // date with slash
        assertFalse(Date.isValidDate("3 July 2020")); // date with words
        assertFalse(Date.isValidDate("01-01-2020")); // date with yyyy
        assertFalse(Date.isValidDate("31-02-20")); // date that does not exist
        assertFalse(Date.isValidDate("35-12-20")); // date that does not exist

        // valid date
        assertTrue(Date.isValidDate("12-12-12")); // date with dashes
        assertTrue(Date.isValidDate("1-1-20")); // date with 1 day and 1 month number
        assertTrue(Date.isValidDate("   11-11-11   ")); // date with whitespace
        assertTrue(Date.isValidDate("   29-02-20   ")); // leap year date that is valid
    }

    @Test
    public void isEquals() {
        // same date
        assertEquals(new Date("01-01-20"), new Date("01-01-20"));

        // same date but with 1 vs 2 day/month numbers
        assertEquals(new Date("1-1-20"), new Date("01-01-20"));
        assertEquals(new Date("12-9-20"), new Date("12-09-20"));
        assertEquals(new Date("2-09-20"), new Date("02-09-20"));
    }
}
