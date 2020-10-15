package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagNameTest {

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
    public void isValidTagName() {
        // null name
        assertThrows(NullPointerException.class, () -> TagName.isValidTagName(null));

        // invalid name
        assertFalse(TagName.isValidTagName("")); // empty string
        assertFalse(TagName.isValidTagName(" ")); // spaces only
        assertFalse(TagName.isValidTagName("^")); // only non-alphanumeric characters
        assertFalse(TagName.isValidTagName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(TagName.isValidTagName("peter jack")); // alphabets only
        assertTrue(TagName.isValidTagName("12345")); // numbers only
        assertTrue(TagName.isValidTagName("peter the 2nd")); // alphanumeric characters
        assertTrue(TagName.isValidTagName("Capital Tan")); // with capital letters
        assertTrue(TagName.isValidTagName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
