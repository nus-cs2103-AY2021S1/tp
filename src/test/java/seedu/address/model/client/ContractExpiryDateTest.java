package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestUtil.basicEqualsTests;

import org.junit.jupiter.api.Test;

public class ContractExpiryDateTest {

    private static final String TEST_DATE_1 = "11-12-2020";
    private static final String TEST_DATE_2 = "30-1-2019";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ContractExpiryDate(null));
    }

    @Test
    public void equals() {
        ContractExpiryDate contractExpiryDate = new ContractExpiryDate(TEST_DATE_1);
        // basic equals tests
        basicEqualsTests(contractExpiryDate);
        // Same date, return true
        assertTrue(contractExpiryDate.equals(new ContractExpiryDate(TEST_DATE_1)));
        // Different date, return false
        assertFalse(contractExpiryDate.equals(new ContractExpiryDate(TEST_DATE_2)));
    }

    @Test
    public void hashCode_test() {
        // Same date, return same hashcode
        ContractExpiryDate contractExpiryDate = new ContractExpiryDate(TEST_DATE_1);
        assertEquals(contractExpiryDate.hashCode(), new ContractExpiryDate(TEST_DATE_1).hashCode());
        // Different date, return different hashcode
        assertNotEquals(contractExpiryDate.hashCode(), new ContractExpiryDate(TEST_DATE_2).hashCode());
    }

    @Test
    public void compareTo() {
        ContractExpiryDate contractExpiryDate1 = new ContractExpiryDate(TEST_DATE_1);
        ContractExpiryDate contractExpiryDate2 = new ContractExpiryDate(TEST_DATE_2);
        assertTrue(contractExpiryDate1.compareTo(contractExpiryDate2) > 0);
        assertTrue(contractExpiryDate2.compareTo(contractExpiryDate1) < 0);
    }

    @Test
    public void toString_test() {
        ContractExpiryDate contractExpiryDate = new ContractExpiryDate(TEST_DATE_1);
        assertEquals(contractExpiryDate.toString(), TEST_DATE_1);
    }

    @Test
    public void isValidDate() {
        // null dates
        assertThrows(NullPointerException.class, () -> ContractExpiryDate.isValidDate(null));

        // invalid dates
        assertFalse(ContractExpiryDate.isValidDate("")); // empty string
        assertFalse(ContractExpiryDate.isValidDate(" ")); // spaces only
        assertFalse(ContractExpiryDate.isValidDate("30")); // number only
        assertFalse(ContractExpiryDate.isValidDate("30-2")); // 2 numbers only
        assertFalse(ContractExpiryDate.isValidDate("001-2-2049")); // 3 digit day
        assertFalse(ContractExpiryDate.isValidDate("1-200-2029")); // 3 digit month
        assertFalse(ContractExpiryDate.isValidDate("1-20-0")); // 1 digit year
        assertFalse(ContractExpiryDate.isValidDate("1-20-020")); // 3 digit year
        assertFalse(ContractExpiryDate.isValidDate("1-20-20020")); // 5 digit year
        assertFalse(ContractExpiryDate.isValidDate("1-0-20")); // month is 0
        assertFalse(ContractExpiryDate.isValidDate("0-1-20")); // day is 0
        assertFalse(ContractExpiryDate.isValidDate("32-12-2020")); // day is 32
        assertFalse(ContractExpiryDate.isValidDate("12-13-2023")); // month is 13
        assertFalse(ContractExpiryDate.isValidDate("30/1/2021")); // slashes are used
        assertFalse(ContractExpiryDate.isValidDate("30-2-2021")); // 30th Feb
        assertFalse(ContractExpiryDate.isValidDate("29-2-2021")); // 29th Feb on non-leap year

        // valid dates
        assertTrue(ContractExpiryDate.isValidDate("12-12-20")); // valid date
        assertTrue(ContractExpiryDate.isValidDate("29-2-2020")); // 29th Feb on leap year
        assertTrue(ContractExpiryDate.isValidDate("1-1-0000")); // smallest possible date
        assertTrue(ContractExpiryDate.isValidDate("31-12-9999")); // largest possible date
    }
}
