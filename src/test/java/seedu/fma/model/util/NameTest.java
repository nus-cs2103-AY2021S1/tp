package seedu.fma.model.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName_null_throwNullPointerException() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

    }

    @Test
    public void isValidName_invalid_returnFalse() {
        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("situp*")); // contains non-alphanumeric characters
    }

    @Test
    public void isValidName_valid_returnTrue() {
        // valid name
        assertTrue(Name.isValidName("jumping jacks")); // alphabets only
        assertTrue(Name.isValidName("1234143")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Sit Ups")); // with capital letters
        assertTrue(Name.isValidName("Sit ups then jumping jacks then rest")); // long names
    }
}
