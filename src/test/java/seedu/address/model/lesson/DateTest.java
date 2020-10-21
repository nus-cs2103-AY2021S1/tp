package seedu.address.model.lesson;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid date
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("01012020")); //no dashes
        assertFalse(Date.isValidDate("00-12-2020")); //wrong day
        assertFalse(Date.isValidDate("12-13-2020")); //wrong month
        assertFalse(Date.isValidDate("12-13-20201")); //wrong year
        assertFalse(Date.isValidDate("12-12-20")); //incomplete year
        
        // valid date
        assertTrue(Date.isValidDate("12-12-2020"));
        assertTrue(Date.isValidDate("01-01-1000"));
    }
}
