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

        // invalid tags
        assertFalse(Tag.isValidTagName("")); // empty string
        assertFalse(Tag.isValidTagName(" ")); // whitespace
        assertFalse(Tag.isValidTagName("hello!")); // non alpha-numeric
        assertFalse(Tag.isValidTagName("hello hello")); // non alpha-numeric
        assertFalse(Tag.isValidTagName("hello1234567891234567")); // alpha-numeric with 21 characters

        //valid tags
        assertTrue(Tag.isValidTagName("hello123")); // alpha-numeric
        assertTrue(Tag.isValidTagName("hello123456789123456")); // alpha-numeric with 20 characters
    }

}
