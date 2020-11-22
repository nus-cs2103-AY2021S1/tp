package seedu.address.model.expense;

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
        String invalidDate = "-10";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // invalid Date
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("2020-13-22")); // wrong month
        assertFalse(Date.isValidDate("2020-12-32")); // wrong date
        assertFalse(Date.isValidDate("2020-09-31")); // 30 days in September
        assertFalse(Date.isValidDate("2020-12")); // not in YYYY-MM-DD format
        assertFalse(Date.isValidDate("124293842")); // Date too large

        // valid Date
        assertTrue(Date.isValidDate("1")); // exactly 1 numbers
        assertTrue(Date.isValidDate("2020-09-30")); // in YYYY-MM-DD format
        assertTrue(Date.isValidDate("3650")); // boundary value
    }
}
