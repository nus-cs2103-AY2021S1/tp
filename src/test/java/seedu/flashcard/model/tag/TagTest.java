package seedu.flashcard.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashcard.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void isValidTagName() {
        // special characters
        assertFalse(Tag.isValidTagName("aaa%aaa"));
        // more than 1 word
        assertFalse(Tag.isValidTagName("aaa aaa"));
        // 51 characters
        assertFalse(Tag.isValidTagName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa."));
        // 50 characters
        assertTrue(Tag.isValidTagName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        // valid tag
        assertTrue(Tag.isValidTagName("aaaa"));
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }
}
