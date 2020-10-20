package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IcNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IcNumber(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidIcNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new IcNumber(invalidIcNumber));
    }

    @Test
    public void isValidIcNumber() {
        // null icNumber
        assertThrows(NullPointerException.class, () -> IcNumber.isValidIcNumber(null));

        // blank icNumber
        assertFalse(IcNumber.isValidIcNumber("")); // empty string
        assertFalse(IcNumber.isValidIcNumber(" ")); // spaces only

        // missing parts
        assertFalse(IcNumber.isValidIcNumber("S234567Z")); // missing first character
        assertFalse(IcNumber.isValidIcNumber("SZ")); // missing 7 digits
        assertFalse(IcNumber.isValidIcNumber("S12334567")); // missing last character

        // invalid parts
        assertFalse(IcNumber.isValidIcNumber(" S1234567Z")); // leading space
        assertFalse(IcNumber.isValidIcNumber("S1234567Z ")); // trailing space
        assertFalse(IcNumber.isValidIcNumber("A1234567Z")); // invalid alphabet in first character
        assertFalse(IcNumber.isValidIcNumber("01234567Z")); // digit in first character
        assertFalse(IcNumber.isValidIcNumber("!1234567Z")); // special character in first character
        assertFalse(IcNumber.isValidIcNumber("SS1234567Z")); // two alphabets in first character
        assertFalse(IcNumber.isValidIcNumber("S 1234567Z")); // space after first character
        assertFalse(IcNumber.isValidIcNumber("S12345678Z")); // 8 digits
        assertFalse(IcNumber.isValidIcNumber("S123 4567Z")); // space between 7 digits
        assertFalse(IcNumber.isValidIcNumber("S123A567Z")); // alphabet in 7 digits
        assertFalse(IcNumber.isValidIcNumber("S12345670")); // digit in last character
        assertFalse(IcNumber.isValidIcNumber("S1234567@")); // special character in last character
        assertFalse(IcNumber.isValidIcNumber("S1234567ZZ")); // two alphabets in last character
        assertFalse(IcNumber.isValidIcNumber("S1234567 Z")); // space before last character

        // valid icNumber
        assertTrue(IcNumber.isValidIcNumber("S1234567Z")); // S in first character
        assertTrue(IcNumber.isValidIcNumber("T1234567Z")); // T in first character
        assertTrue(IcNumber.isValidIcNumber("F1234567Z")); // F in first character
        assertTrue(IcNumber.isValidIcNumber("G1234567Z")); // G in first character
        assertTrue(IcNumber.isValidIcNumber("s1234567z")); // lower-case alphabets only
        assertTrue(IcNumber.isValidIcNumber("S7777777Z")); // same number digits
    }
}
