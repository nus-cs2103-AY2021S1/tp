package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DateTest {
    private final Date date1 = new Date("11-10-2020");
    private final Date date2 = new Date("21-10-2020");
    private final Date date3 = new Date("26-10-2020");

    @Test
    void isValidDate_invalidDate_false() {
        assertFalse(Date.isValidDate("11/01/2020")); // should use "-" instead of "/"
        assertFalse(Date.isValidDate("11-1-2020")); // should be in the form of dd-mm-yyyy
        assertFalse(Date.isValidDate("30-02-2020")); // February does not have 30 days
        assertFalse(Date.isValidDate("29-02-2100")); // 2100 is not a leap year

    }

    @Test
    void isValidDate_validDate_true() {
        assertTrue(Date.isValidDate("27-10-2020"));
        assertTrue(Date.isValidDate("29-02-2000")); // 2000 is a leap year
        assertTrue(Date.isValidDate("29-02-2020")); // 2020 is a leap year

    }

    @Test
    void isBefore_compareWithSameDate_false() {
        assertFalse(date2.isBefore(date2));
    }

    @Test
    void isBefore_compareWithDateBefore_false() {
        assertFalse(date2.isBefore(date1));
    }

    @Test
    void isBefore_compareWithDateAfter_true() {
        assertTrue(date2.isBefore(date3));
    }
}
