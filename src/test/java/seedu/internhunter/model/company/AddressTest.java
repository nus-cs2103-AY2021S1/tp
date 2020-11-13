package seedu.internhunter.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_ADDRESS_BLANK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.INVALID_ADDRESS_EMPTY;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_FACEBOOK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_GOLDMAN;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_GOOGLE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil
        .VALID_ADDRESS_GOOGLE_LEADING_AND_TRAILING_SPACES;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_GOOGLE_LEADING_SPACES;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_GOOGLE_TRAILING_SPACES;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_ONE_ALPHABET;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_ONE_NUMBER;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_ONE_SPECIAL_CHAR;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_SPECIAL_CHARS;

import org.junit.jupiter.api.Test;

class AddressTest {

    private static final Address VALID_ADDRESS_ONE = new Address(VALID_ADDRESS_GOOGLE);
    private static final Address VALID_ADDRESS_TWO = new Address(VALID_ADDRESS_FACEBOOK);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                Address.MESSAGE_CONSTRAINTS, () -> new Address(INVALID_ADDRESS_EMPTY));
        assertThrows(IllegalArgumentException.class,
                Address.MESSAGE_CONSTRAINTS, () -> new Address(INVALID_ADDRESS_BLANK));
    }

    @Test
    public void isValidAddress_invalidFormats_success() {
        assertFalse(Address.isValidAddress(INVALID_ADDRESS_EMPTY));
        assertFalse(Address.isValidAddress(INVALID_ADDRESS_BLANK));
    }

    @Test
    public void isValidAddress_validFormats_success() {
        assertTrue(Address.isValidAddress(VALID_ADDRESS_ONE_ALPHABET));
        assertTrue(Address.isValidAddress(VALID_ADDRESS_ONE_NUMBER));
        assertTrue(Address.isValidAddress(VALID_ADDRESS_ONE_SPECIAL_CHAR));
        assertTrue(Address.isValidAddress(VALID_ADDRESS_SPECIAL_CHARS));
        assertTrue(Address.isValidAddress(VALID_ADDRESS_FACEBOOK));
        assertTrue(Address.isValidAddress(VALID_ADDRESS_GOLDMAN));
        assertTrue(Address.isValidAddress(VALID_ADDRESS_GOOGLE));
        assertTrue(Address.isValidAddress(VALID_ADDRESS_GOOGLE_LEADING_SPACES));
        assertTrue(Address.isValidAddress(VALID_ADDRESS_GOOGLE_TRAILING_SPACES));
        assertTrue(Address.isValidAddress(VALID_ADDRESS_GOOGLE_LEADING_AND_TRAILING_SPACES));
    }

    @Test
    public void getValue_equalityTest_success() {
        assertEquals(VALID_ADDRESS_GOOGLE, VALID_ADDRESS_ONE.getValue());
    }

    @Test
    public void toString_validFormat_success() {
        assertEquals(VALID_ADDRESS_GOOGLE, VALID_ADDRESS_ONE.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        assertEquals(VALID_ADDRESS_ONE, VALID_ADDRESS_ONE);
        Address addressCopy = new Address(VALID_ADDRESS_GOOGLE);
        assertEquals(VALID_ADDRESS_ONE, addressCopy);
    }

    @Test
    public void equals_nonEqualityTest_success() {
        assertNotEquals(VALID_ADDRESS_ONE, VALID_ADDRESS_TWO);
    }

    @Test
    public void hashCode_equalityTest_success() {
        assertEquals(VALID_ADDRESS_ONE.hashCode(), VALID_ADDRESS_ONE.hashCode());
    }

}
