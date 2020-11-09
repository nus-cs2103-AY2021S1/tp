package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE2;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

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
        // null Date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid Date because of incorrect value type
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("abcd")); // non-numeric
        assertFalse(Date.isValidDate("halo 123")); // alphanumeric

        // invalid Date because of incorrect format
        assertFalse(Date.isValidDate("2020/10/30")); // use forward slashed
        assertFalse(Date.isValidDate("2020.10.30")); // use dot
        assertFalse(Date.isValidDate("2020,10,30")); // use comma
        assertFalse(Date.isValidDate("2020 10 30")); // use whitespace
        assertFalse(Date.isValidDate("2020|10|30")); // use bar
        assertFalse(Date.isValidDate("2020_10_30")); // use underscore

        // invalid Date because of incorrect order
        assertFalse(Date.isValidDate("10-30-2020"));
        assertFalse(Date.isValidDate("30-10-2020"));
        assertFalse(Date.isValidDate("2020-30-10"));
        assertFalse(Date.isValidDate("10-2020-30"));
        assertFalse(Date.isValidDate("30-2020-10"));

        // invalid Date because of incomplete value
        assertFalse(Date.isValidDate("2020")); // year only
        assertFalse(Date.isValidDate("10")); // month only
        assertFalse(Date.isValidDate("30")); // day only
        assertFalse(Date.isValidDate("2020-10")); // year and month only
        assertFalse(Date.isValidDate("10-30")); // month and day only
        assertFalse(Date.isValidDate("2020-30")); // year and day only
        assertFalse(Date.isValidDate("2020")); // year and month only

        // invalid Date because of invalid month value
        assertFalse(Date.isValidDate("2020-14-30")); // boundary value
        assertFalse(Date.isValidDate("2020-13-30")); // boundary value
        assertFalse(Date.isValidDate("2020-0-30")); // boundary value
        assertFalse(Date.isValidDate("2020-30-30")); // non-boundary value

        // invalid Date because of invalid day value for month with 31 days
        assertFalse(Date.isValidDate("2020-10-32")); // boundary value
        assertFalse(Date.isValidDate("2020-10-33")); // boundary value
        assertFalse(Date.isValidDate("2020-10-0")); // boundary value
        assertFalse(Date.isValidDate("2020-10-100")); // non-boundary value

        // invalid Date because of invalid day value for month with 30 days
        assertFalse(Date.isValidDate("2020-09-31")); // boundary value
        assertFalse(Date.isValidDate("2020-09-32")); // boundary value
        assertFalse(Date.isValidDate("2020-09-0")); // boundary value
        assertFalse(Date.isValidDate("2020-09-100")); // non-boundary value

        // invalid Date because of invalid day value for month with 28 days (not in leap year)
        assertFalse(Date.isValidDate("2019-02-29")); // boundary value
        assertFalse(Date.isValidDate("2019-02-30")); // boundary value
        assertFalse(Date.isValidDate("2019-02-0")); // boundary value
        assertFalse(Date.isValidDate("2019-02-100")); // non-boundary value

        // invalid Date because of invalid day value for month with 28 days (in leap year)
        assertFalse(Date.isValidDate("2020-02-30")); // boundary value
        assertFalse(Date.isValidDate("2020-02-31")); // boundary value
        assertFalse(Date.isValidDate("2020-02-0")); // boundary value
        assertFalse(Date.isValidDate("2020-02-100")); // non-boundary value

        // valid Date, different months
        assertTrue(Date.isValidDate("2020-01-31")); // boundary value
        assertTrue(Date.isValidDate("2020-11-30")); // boundary value
        assertTrue(Date.isValidDate("2020-12-29")); // boundary value
        assertTrue(Date.isValidDate("2020-05-15")); // non-boundary value

        // valid Date, same month (31 days)
        assertTrue(Date.isValidDate("2020-08-31")); // boundary value
        assertTrue(Date.isValidDate("2020-08-30")); // boundary value
        assertTrue(Date.isValidDate("2020-08-29")); // boundary value
        assertTrue(Date.isValidDate("2020-08-15")); // non-boundary value

        // valid Date, same month (30 days)
        assertTrue(Date.isValidDate("2020-07-30")); // boundary value
        assertTrue(Date.isValidDate("2020-07-29")); // boundary value
        assertTrue(Date.isValidDate("2020-07-28")); // boundary value
        assertTrue(Date.isValidDate("2020-07-15")); // non-boundary value

        // valid Date, same month (28 days)
        assertTrue(Date.isValidDate("2020-02-28")); // boundary value
        assertTrue(Date.isValidDate("2020-02-27")); // boundary value
        assertTrue(Date.isValidDate("2020-02-26")); // boundary value
        assertTrue(Date.isValidDate("2020-02-15")); // non-boundary value
    }

    @Test
    public void equals() {
        Date first = new Date(VALID_DATE1);
        Date second = new Date(VALID_DATE2);

        // null value -> false
        assertFalse(first.equals(null));

        // other type -> false
        assertFalse(first.equals(10));

        // same date -> true
        assertTrue(first.equals(first));

        // different date -> false
        assertFalse(first.equals(second));
    }

    @Test
    public void getValue() {
        Date date = new Date(VALID_DATE1);
        LocalDate value = LocalDate.parse(VALID_DATE1);
        LocalDate invalidValue = LocalDate.parse(VALID_DATE2);

        // valid value -> true
        assertEquals(date.getValue(), value);

        // invalid value -> false
        assertNotEquals(date.getValue(), invalidValue);
    }

    @Test
    public void test_toString() {
        Date first = new Date(VALID_DATE1);
        Date second = new Date(VALID_DATE2);

        // valid -> true
        assertEquals(first.toString(), VALID_DATE1);
        assertEquals(second.toString(), VALID_DATE2);

        // invalid -> false
        assertNotEquals(first.toString(), VALID_DATE2);
        assertNotEquals(second.toString(), VALID_DATE1);
    }
}
