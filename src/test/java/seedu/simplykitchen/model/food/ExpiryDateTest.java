package seedu.simplykitchen.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ExpiryDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExpiryDate(null));
    }

    @Test
    public void constructor_invalidExpiryDate_throwsIllegalArgumentException() {
        String invalidExpiryDate = "";
        assertThrows(IllegalArgumentException.class, () -> new ExpiryDate(invalidExpiryDate));
    }

    @Test
    public void isValidExpiryDate() {
        // EP: null expiry date
        assertThrows(NullPointerException.class, () -> ExpiryDate.isValidExpiryDate(null));

        // EP: blank expiry date
        assertFalse(ExpiryDate.isValidExpiryDate("")); // empty string
        assertFalse(ExpiryDate.isValidExpiryDate(" ")); // spaces only

        // EP: invalid day
        assertFalse(ExpiryDate.isValidExpiryDate("01/2020")); // missing day
        assertFalse(ExpiryDate.isValidExpiryDate("0/01/2020")); // invalid day -lower bound
        assertFalse(ExpiryDate.isValidExpiryDate("32/01/2020")); // invalid day - upper bound
        assertFalse(ExpiryDate.isValidExpiryDate("a/01/2020")); // letters as day
        assertFalse(ExpiryDate.isValidExpiryDate("//01/2020")); // special character as day
        assertFalse(ExpiryDate.isValidExpiryDate("01.0/01/2020")); // double value as day

        // EP: invalid month
        assertFalse(ExpiryDate.isValidExpiryDate("01//2020")); // missing month
        assertFalse(ExpiryDate.isValidExpiryDate("01/00/2020")); // invalid month - lower bound
        assertFalse(ExpiryDate.isValidExpiryDate("01/13/2020")); // invalid month - upper bound
        assertFalse(ExpiryDate.isValidExpiryDate("01/a/2020")); // letters as month
        assertFalse(ExpiryDate.isValidExpiryDate("01/*6/2020")); // special character as month
        assertFalse(ExpiryDate.isValidExpiryDate("01/01.0/2020")); // double value as month

        // EP: invalid year
        assertFalse(ExpiryDate.isValidExpiryDate("01/01")); // missing year
        assertFalse(ExpiryDate.isValidExpiryDate("31/12/2019")); // invalid month - lower bound
        assertFalse(ExpiryDate.isValidExpiryDate("01/01/2121")); // invalid year - upper bound
        assertFalse(ExpiryDate.isValidExpiryDate("01/01/aaaa")); // letters as year
        assertFalse(ExpiryDate.isValidExpiryDate("01/01//*020")); // special character as year
        assertFalse(ExpiryDate.isValidExpiryDate("01/01/2030.0")); // double value as year
        assertFalse(ExpiryDate.isValidExpiryDate("01/01/20")); // shortened year

        // invalid expiry date
        assertFalse(ExpiryDate.isValidExpiryDate("30-02-2021")); // non-existent expiry date
        assertFalse(ExpiryDate.isValidExpiryDate("1--10-2020")); // extra dash
        assertFalse(ExpiryDate.isValidExpiryDate("1//10/2020")); // extra slash
        assertFalse(ExpiryDate.isValidExpiryDate("2020-1-1")); // wrong date format

        // valid expiry date
        assertTrue(ExpiryDate.isValidExpiryDate("1-1-2020")); // lower bound with dash
        assertTrue(ExpiryDate.isValidExpiryDate("12-12-2120")); // upper bound with dash
        assertTrue(ExpiryDate.isValidExpiryDate("1/1/2020")); // lower bound with slash
        assertTrue(ExpiryDate.isValidExpiryDate("12/12/2120")); // upper bound with dash
    }
}
