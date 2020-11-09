package seedu.expense.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.expense.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "23 June 2020";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null email
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // blank email
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only

        // missing parts
        assertFalse(Date.isValidDate("04-2020")); // missing day
        assertFalse(Date.isValidDate("04-12")); // missing year
        assertFalse(Date.isValidDate("04121998")); // missing dashes
        assertFalse(Date.isValidDate("24-1998")); // missing month


        // invalid parts
        assertFalse(Date.isValidDate("1-02-1998")); // only 1 digit for day
        assertFalse(Date.isValidDate("04-1-1998")); // only 1 digit for month
        assertFalse(Date.isValidDate("04-10-20")); // only 2 digits for year
        assertFalse(Date.isValidDate("04/10/2020")); // '/' symbol instead of '-' symbol
        assertFalse(Date.isValidDate(" 04-10-2020")); // leading space
        assertFalse(Date.isValidDate("04-10-2020 ")); // trailing space
        assertFalse(Date.isValidDate("04-10--2020")); // double '-' symbol
        assertFalse(Date.isValidDate("4a-10-2020")); // letters used
        assertFalse(Date.isValidDate("23 June 2020")); // written format instead of dd-MM-yyyy
        assertFalse(Date.isValidDate("04-10-2020-")); // extra trailing '-' symbol

        // valid date
        assertTrue(Date.isValidDate("04-10-2020"));
    }
}
