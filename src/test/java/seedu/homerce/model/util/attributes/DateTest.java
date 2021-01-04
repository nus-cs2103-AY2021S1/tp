package seedu.homerce.model.util.attributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;

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
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid dates
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("1-23-2020")); // invalid month
        assertFalse(Date.isValidDate("32-2-2020 ")); // invalid day
        assertFalse(Date.isValidDate("32-2-20200 ")); // invalid year
        assertFalse(Date.isValidDate("23/2/2020 ")); // invalid format

        // valid dates
        assertTrue(Date.isValidDate("20-1-2020"));
        assertTrue(Date.isValidDate("20-12-2020"));
        assertTrue(Date.isValidDate("2-1-2020"));
        assertTrue(Date.isValidDate("2-12-2020"));

    }

    @Test
    public void toStringTest() {

        Date date1 = new Date("20-1-2020");
        Date date2 = new Date("1-12-2020");
        assertEquals("20-1-2020", date1.toString());
        assertEquals("1-12-2020", date2.toString());
    }
}
