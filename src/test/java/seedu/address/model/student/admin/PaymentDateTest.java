package seedu.address.model.student.admin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PaymentDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PaymentDate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String[] invalidDates = new String[]{"12-1-31", "32/10/19"};
        for (String invalidDate : invalidDates) {
            assertThrows(IllegalArgumentException.class, () -> new PaymentDate(invalidDate));
        }
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> new PaymentDate(null));

        // invalid date
        assertFalse(PaymentDate.isValidDate(""));
        assertFalse(PaymentDate.isValidDate("2-2-22"));
        assertFalse(PaymentDate.isValidDate("2/2/2"));
        assertFalse(PaymentDate.isValidDate("12.2.31"));
        assertFalse(PaymentDate.isValidDate("abc"));

        // valid date
        assertTrue(PaymentDate.isValidDate("12/12/12"));
        assertTrue(PaymentDate.isValidDate("12/2/12"));
        assertTrue(PaymentDate.isValidDate("2/12/12"));
        assertTrue(PaymentDate.isValidDate("2/2/12"));
        assertTrue(PaymentDate.isValidDate("12/12/2002"));
        assertTrue(PaymentDate.isValidDate("12/2/2002"));
        assertTrue(PaymentDate.isValidDate("2/12/2002"));
        assertTrue(PaymentDate.isValidDate("2/2/2002"));
    }
}
