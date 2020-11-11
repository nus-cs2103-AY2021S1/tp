package seedu.bookmark.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.bookmark.testutil.Assert.assertThrows;

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
    public void constructor_tooLogTagName_throwsIllegalArgumentException() {
        String tooLong = "a".repeat(Tag.MAX_TAG_LENGTH + 1);

        assertThrows(IllegalArgumentException.class, () -> new Tag(tooLong));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // 1 char too long tag name
        assertFalse(Tag.isValidTagName("a".repeat(Tag.MAX_TAG_LENGTH + 1)));
    }

}
