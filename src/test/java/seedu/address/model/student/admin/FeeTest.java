package seedu.address.model.student.admin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FeeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Fee(null));
    }

    @Test
    public void constructor_invalidFee_throwsIllegalArgumentException() {
        String invalidFee = "";
        assertThrows(IllegalArgumentException.class, () -> new Fee(invalidFee));
    }

    @Test
    public void isValidFee() {
        // null fee
        assertThrows(NullPointerException.class, () -> Fee.isValidFee(null));

        // invalid fees
        assertFalse(Fee.isValidFee(""));
        assertFalse(Fee.isValidFee("a"));
        assertFalse(Fee.isValidFee("b81.1"));
        assertFalse(Fee.isValidFee("12.a"));

        // valid fees
        assertTrue(Fee.isValidFee("12.23"));
        assertTrue(Fee.isValidFee("213123.21"));
        assertTrue(Fee.isValidFee("324"));
        assertTrue(Fee.isValidFee("13.4"));
    }
}
