package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // invalid tag name
        assertFalse(Tag.isValidTagName("")); // empty string
        assertFalse(Tag.isValidTagName(" ")); // spaces only
        assertFalse(Tag.isValidTagName("%^&")); // only non-alphanumeric characters
        assertFalse(Tag.isValidTagName("tag&")); // // contains non-alphanumeric characters
        assertFalse(Tag.isValidTagName("tag 1")); // contains whitespace

        // valid tag name
        assertTrue(Tag.isValidTagName("tag")); // alphabets only
        assertTrue(Tag.isValidTagName("123")); // numbers only
        assertTrue(Tag.isValidTagName("tag1")); // alphanumeric characters
        assertTrue(Tag.isValidTagName("Tag")); // contains capital letter
        assertTrue(Tag.isValidTagName("t")); // single alphabet character
    }

}
