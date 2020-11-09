package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
    public void constructor_validTagName_tagWithDefaultModuleCount() {
        String expectedTagName = "core";
        int expectedModuleCount = 1;
        Tag actualTag = new Tag(expectedTagName);
        assertEquals(expectedTagName, actualTag.tagName);
        assertEquals(expectedModuleCount, actualTag.getModuleCount());
    }

    @Test
    public void isValidTagName() {
        // null name --> throw NPE
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
        // single word with letters
        assertTrue(Tag.isValidTagName("core"));
        // multiword with letters
        assertTrue(Tag.isValidTagName("core module"));
        // single word with number
        assertTrue(Tag.isValidTagName("core1"));
        // uppercase letter
        assertTrue(Tag.isValidTagName("Core"));
        // empty name
        assertFalse(Tag.isValidTagName(""));
        // only whitespace
        assertFalse(Tag.isValidTagName("  "));
        // multiword with extra space in between
        assertFalse(Tag.isValidTagName("core   module"));
    }

    @Test
    public void moduleCount() {
        Tag tag = new Tag("core");

        // module count: 1 --> 0
        tag.decrementModuleCount();
        assertEquals(0, tag.getModuleCount());
        assertTrue(tag.isEmpty());

        // module count: 0 --> 1
        tag.incrementModuleCount();
        assertEquals(1, tag.getModuleCount());
    }

    @Test
    public void equals() {
        Tag tag1 = new Tag("core");

        // null --> false
        assertFalse(tag1.equals(null));
        // different types --> false
        assertFalse(tag1.equals("core"));


        // same object --> true
        assertTrue(tag1.equals(tag1));

        // different object, same tagName --> true
        assertTrue(tag1.equals(new Tag("core")));

        // different object, same tagName, different moduleCount --> true
        Tag tag2 = new Tag("core");
        tag2.incrementModuleCount();
        assertTrue(tag1.equals(tag2));

        // different object, different tagName --> false
        assertFalse(tag1.equals(new Tag("notCore")));
    }

    @Test
    public void hashCodeTest() {
        Tag tag1 = new Tag("core");
        Tag tag2 = new Tag("notCore");

        // same object --> same hashcode
        assertEquals(tag1.hashCode(), tag1.hashCode());

        // different object, same tagName --> same hashcode
        assertEquals(tag1.hashCode(), new Tag("core").hashCode());

        //different object, different tagName --> different hashcode
        assertNotEquals(tag1.hashCode(), tag2.hashCode());
    }

}
