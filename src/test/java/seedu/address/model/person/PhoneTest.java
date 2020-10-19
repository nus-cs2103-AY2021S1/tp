package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        //null phone
        assertThrows(NullPointerException.class, () -> Phone.isValidPhone(null));

        // invalid phone
        assertFalse(Phone.isValidPhone("")); // empty string
        assertFalse(Phone.isValidPhone(" ")); // spaces only
        assertFalse(Phone.isValidPhone("invalid")); // letters only
        assertFalse(Phone.isValidPhone("INVALID")); // capital letters with spaces
        assertFalse(Phone.isValidPhone("92800f")); // letters and numbers
        assertFalse(Phone.isValidPhone(" 92800")); // numbers with space in front
        assertFalse(Phone.isValidPhone("92 3323")); //  numbers with space in between
        assertFalse(Phone.isValidPhone("3")); // less than 3 digits ( < 3)
        assertFalse(Phone.isValidPhone("")); // 20 digit number ( > 16)

        // valid phone
        assertTrue(Phone.isValidPhone("92838392")); // 8 digit number
        assertTrue(Phone.isValidPhone("234")); // 3 digit number
        assertTrue(Phone.isValidPhone("1837263859473627")); // 16 digit number
    }

}
