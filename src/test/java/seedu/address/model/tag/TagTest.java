package seedu.address.model.tag;

import static seedu.address.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void tagNotFilled_UseDefaultTag() {
        Tag defaultTag = Tag.defaultTag();
        assertTrue(defaultTag.tagName.equals(""));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String emptyTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(emptyTagName));
        String invalidSymbols = "$*&#*)$&*#";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidSymbols));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }
    @Test
    public void isUpperCase() {
        // upper case
        Tag upperCase = new Tag("CS1101S");
        // lower case
        Tag lowerCase = new Tag("cs1101s");
        assertTrue(upperCase.equals(lowerCase));
        // mix of lower and upper case
        Tag mixOfUpperAndLowerCase = new Tag("cS1101s");
        assertTrue(upperCase.equals(mixOfUpperAndLowerCase));
    }

}
