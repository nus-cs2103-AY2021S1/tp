package seedu.medibook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.medibook.model.commonfields.Date;

class DateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null, true));
        assertThrows(NullPointerException.class, () -> new Date(null, false));
    }

    @Test
    public void constructor_validNonFutureDate_successWithCorrectPublicValues() {
        Date nonFutureDate = new Date("19-02-2020", true);
        assertEquals("19-02-2020", nonFutureDate.inputValue);
        assertEquals("19 Feb 2020", nonFutureDate.outputValue);
    }

    @Test
    public void constructor_validFutureDate_successWithCorrectPublicValues() {
        Date futureDate = new Date("19-02-2021", false);
        assertEquals("19-02-2021", futureDate.inputValue);
        assertEquals("19 Feb 2021", futureDate.outputValue);
    }

    @Test
    public void constructor_validFutureDateWithNonFutureAsTrue_failure() {
        assertThrows(IllegalArgumentException.class, () -> new Date("19-02-2021", true));
    }

    @Test
    public void constructor_invalidDate_failure() {
        assertThrows(IllegalArgumentException.class, () -> new Date("19/02/2021", false));
        assertThrows(IllegalArgumentException.class, () -> new Date("19022021", false));
        assertThrows(IllegalArgumentException.class, () -> new Date("19 Feb 2021", false));
        assertThrows(IllegalArgumentException.class, () -> new Date("19/02/2020", true));
        assertThrows(IllegalArgumentException.class, () -> new Date("19022020", true));
        assertThrows(IllegalArgumentException.class, () -> new Date("19 Feb 2020", true));
    }

    @Test
    public void isValidDate() {
        // invalid date
        assertFalse(Date.isValidDate(null)); // null input
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate("21/03/89")); // wrong format with /
        assertFalse(Date.isValidDate("23-04-00")); // wrong year format
        assertFalse(Date.isValidDate("2 Feb 1996")); // wrong format with letters

        // valid date of birth
        assertTrue(Date.isValidDate("14-09-3000")); // future date
        assertTrue(Date.isValidDate("23-03-2000"));
        assertTrue(Date.isValidDate("01-07-1995"));
        assertTrue(Date.isValidDate("31-12-1995"));
    }

    @Test
    public void compareTo() {
        // same date
        Date date1 = new Date("19-02-2020", true);
        Date date2 = new Date("19-02-2020", false);
        assertTrue(date1.compareTo(date2) == 0);

        // earlier date
        Date date3 = new Date("30-12-2018", false);
        assertTrue(date3.compareTo(date1) < 0);

        // later date
        assertTrue(date1.compareTo(date3) > 0);
    }
}
