package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TagName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new TagName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> TagName.isValidName(null));

        // invalid name
        assertFalse(TagName.isValidName("")); // empty string
        assertFalse(TagName.isValidName(" ")); // spaces only
        assertFalse(TagName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(TagName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(TagName.isValidName("peter jack")); // alphabets only
        assertTrue(TagName.isValidName("12345")); // numbers only
        assertTrue(TagName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(TagName.isValidName("Capital Tan")); // with capital letters
        assertTrue(TagName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
