package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestUtil.basicEqualsTests;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, Tag.MESSAGE_CONSTRAINTS, () -> new Tag(invalidTagName));

        String invalidTagName2 = "pneumonoultramicroscopicsilicovolcanoconiosisk";
        assertThrows(IllegalArgumentException.class, Tag.MESSAGE_CONSTRAINTS, () -> new Tag(invalidTagName2));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // empty tag name -> invalid
        assertFalse(Tag.isValidTagName(""));

        // non-alphanumeric tag name -> invalid
        assertFalse(Tag.isValidTagName("hello'"));
        assertFalse(Tag.isValidTagName("hello it me"));

        // tag name longer than 45 characters -> invalid
        assertFalse(Tag.isValidTagName("pneumonoultramicroscopicsilicovolcanoconiosisk"));

        // tag name exactly 45 characters -> valid
        assertTrue(Tag.isValidTagName("pneumonoultramicroscopicsilicovolcanoconiosis"));
    }

    @Test
    public void equals() {
        Tag tag1 = new Tag("tag1");
        Tag tag2 = new Tag("tag2");

        // basic equals tests
        basicEqualsTests(tag1);

        // same tag name -> returns true
        assertTrue(tag1.equals(new Tag("tag1")));

        // different tag name -> returns false
        assertFalse(tag1.equals(tag2));
    }

    @Test
    public void hashCode_test() {
        Tag tag1 = new Tag("tag1");
        Tag tag2 = new Tag("tag2");
        // same object -> returns same hashcode
        assertEquals(tag1.hashCode(), tag1.hashCode());

        // same tag name -> returns same hashcode
        assertEquals(tag1.hashCode(), new Tag("tag1").hashCode());

        // different tag name -> returns different hashcode
        assertNotEquals(tag1.hashCode(), tag2.hashCode());
    }

    @Test
    public void toString_test() {
        String tagName = "thisisatag";
        Tag tag = new Tag(tagName);
        assertEquals(tag.toString(), "[" + tagName + "]");
    }

}
