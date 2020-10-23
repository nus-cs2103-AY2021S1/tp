package seedu.address.model.account.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AmountTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Amount(null));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        String invalidAmount = "";
        assertThrows(IllegalArgumentException.class, () -> new Amount(invalidAmount));
    }

    @Test
    public void isValidAmount_success() {
        // null amount
        assertThrows(NullPointerException.class, () -> Amount.isValidAmount(null));

        // blank amount
        assertFalse(Amount.isValidAmount("")); // empty string
        assertFalse(Amount.isValidAmount(" ")); // spaces only

        // invalid amount
        assertFalse(Amount.isValidAmount("a")); // alphabets
        assertFalse(Amount.isValidAmount("a1")); // alphabets with numbers
        assertFalse(Amount.isValidAmount("1.3a")); // alphabets at the end of double
        assertFalse(Amount.isValidAmount("1.333")); // double ending with 3 decimal places
        assertFalse(Amount.isValidAmount(" 1 .30")); // space between integer and decimal place
        assertFalse(Amount.isValidAmount("1.30 ")); // trailing space
        assertFalse(Amount.isValidAmount(" 1.30")); // starts with a space
        assertFalse(Amount.isValidAmount(".0")); // starts with a zero after dot
        assertFalse(Amount.isValidAmount(".00")); // starts with two zero after dot
        assertFalse(Amount.isValidAmount("00.00")); // all zeros
        assertFalse(Amount.isValidAmount("0000000.00")); // all zeros
        assertFalse(Amount.isValidAmount("0")); // one zero

        // valid amount
        assertTrue(Amount.isValidAmount("1")); // short integer
        assertTrue(Amount.isValidAmount("100000")); // long integer
        assertTrue(Amount.isValidAmount("10.00")); // double value with all zeros
        assertTrue(Amount.isValidAmount("01.30")); // starts with a zero
        assertTrue(Amount.isValidAmount("10.")); //a dot with no decimal places
        assertTrue(Amount.isValidAmount("10.1")); //a dot with one decimal places
        assertTrue(Amount.isValidAmount("10.10")); // double value with varying ones and zeros
        assertTrue(Amount.isValidAmount(".1")); //only decimal with a dot with one decimal place
        assertTrue(Amount.isValidAmount(".10")); //only decimal with a dot with two decimal places
        assertTrue(Amount.isValidAmount(".01")); //only decimal a dot with two decimal places
        assertTrue(Amount.isValidAmount("0.39")); //start with 0 only decimal a dot with two decimal places
        assertTrue(Amount.isValidAmount("000000.39")); //start with multiple 0only decimal a dot with two decimal places
        assertTrue(Amount.isValidAmount("2147483647")); // max INT
    }

    @Test
    public void isValidAmount_max() {
        // Valid Amounts
        assertTrue(Amount.isValidAmount("1000000.00"));
        assertTrue(Amount.isValidAmount("1000000"));

        // Invalid Amounts
        assertFalse(Amount.isValidAmount("1000000.01"));
        assertFalse(Amount.isValidAmount("1000001.00"));
        assertFalse(Amount.isValidAmount("99999999.99"));
    }

}
