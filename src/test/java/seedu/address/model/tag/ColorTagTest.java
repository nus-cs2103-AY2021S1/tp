package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ColorTagTest {

    @Test
    public void constructor_redColor_success() {
        String colorName = "red";

        ColorTag colorTag = new ColorTag(colorName);

        assertEquals(colorTag.originalColor, colorName);
        assertEquals(colorTag.toString(), colorName);
        assertEquals(colorTag.cssColor, "rgba(255,0,0,1)");
        assertEquals(colorTag, new ColorTag("rgba(255,0,0,1)"));
    }

    @Test
    public void constructor_none_success() {
        ColorTag colorTag = new ColorTag();

        assertEquals(colorTag.originalColor, "None");
        assertEquals(colorTag.toString(), "None");
        assertEquals(colorTag.cssColor, "transparent");
        assertEquals(colorTag, new ColorTag("None"));
        assertTrue(colorTag.isPlaceholder());
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ColorTag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidColorName = "gibberish";
        assertThrows(IllegalArgumentException.class, () -> new ColorTag(invalidColorName));
    }

    @Test
    public void isValidColorName() {
        assertTrue(ColorTag.isValidColorName("red"));
        assertTrue(ColorTag.isValidColorName("None"));

        assertFalse(ColorTag.isValidColorName("gibberish"));
        assertFalse(ColorTag.isValidColorName(""));

        assertThrows(NullPointerException.class, () -> ColorTag.isValidColorName(null));
    }

}
