package seedu.fma.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.testutil.Assert.assertThrows;

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

        // valid tag names
        assertTrue(Tag.isValidTagName("abc")); // minimal
        assertTrue(Tag.isValidTagName("newexercises")); // alphabets only
        assertTrue(Tag.isValidTagName("testexercise123")); // alphabets + numbers only

        // invalid tag names
        assertFalse(Tag.isValidTagName("new exercise")); // use of space
        assertFalse(Tag.isValidTagName("!#$%&'*+/=?`{|}~^.-@exercise")); // extensive use of special characters
        assertFalse(Tag.isValidTagName("a1+be!@exercisemanz.com")); // mixture of alphanumeric and special characters
    }


    @Test
    public void equals() {
        Tag tagA = new Tag("newexercise");
        Tag tagASimilar = new Tag("newexercise");
        Tag tagC = new Tag("143231");

        // same values -> returns true
        assertTrue(tagA.equals(tagASimilar));

        // same object -> returns true
        assertTrue(tagA.equals(tagA));

        // null -> returns false
        assertFalse(tagA.equals(null));

        // different types -> returns false
        assertFalse(tagA.equals(5));

        // different values -> returns false
        assertFalse(tagA.equals(tagC));
    }
}
