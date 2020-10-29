package seedu.pivot.model.investigationcase.caseperson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {
    private static final String VALID_PHONE = "91234567";
    private static final String VALID_PHONE_SHORT = "123";
    private static final String VALID_PHONE_LONG = "123456789765432";
    private static final String INVALID_SHORT = "1";
    private static final String INVALID_VALUES = "123abc+#$!";
    private static final String BLANK = "";
    private static final String EMPTY = " ";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Phone(INVALID_SHORT));
        assertThrows(IllegalArgumentException.class, () -> new Phone(INVALID_VALUES));
    }

    @Test
    public void isValid_true() {
        assertTrue(Phone.isValidPhone(BLANK));
        assertTrue(Phone.isValidPhone(EMPTY));
        assertTrue(Phone.isValidPhone(VALID_PHONE));
        assertTrue(Phone.isValidPhone(VALID_PHONE_SHORT));
        assertTrue(Phone.isValidPhone(VALID_PHONE_LONG));
    }

    @Test
    public void isValid_false() {
        assertFalse(Phone.isValidPhone(INVALID_SHORT));
        assertFalse(Phone.isValidPhone(INVALID_VALUES));
    }

    @Test
    public void equals() {
        Phone defaultPhone = new Phone(VALID_PHONE);
        // Same value --> true
        assertTrue(defaultPhone.equals(new Phone(VALID_PHONE)));

        // same object --> true
        assertTrue(defaultPhone.equals(defaultPhone));

        // null --> false
        assertFalse(defaultPhone.equals(null));

        // different types --> false
        assertFalse(defaultPhone.equals(1));

        // different value --> false
        assertFalse(defaultPhone.equals(new Phone("92273613"))); // any real number is coincidental.
    }
}
