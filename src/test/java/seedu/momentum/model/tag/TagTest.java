package seedu.momentum.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.momentum.testutil.Assert.assertThrows;

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
    }

    @Test
    public void equals() {
        Tag tag = new Tag("tag");
        // same object -> returns true
        assertTrue(tag.equals(tag));

        // same tag name -> returns true
        assertTrue(tag.equals(new Tag("tag")));

        // different types -> returns false
        assertFalse(tag.equals("1"));

        // null -> return false
        assertFalse(tag.equals(null));

        // different completion status -> return false
        assertFalse(tag.equals(new Tag("aefrweze")));
    }
}
