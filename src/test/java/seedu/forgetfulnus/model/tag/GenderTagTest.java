package seedu.forgetfulnus.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GenderTagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GenderTag(null));
    }

    @Test
    public void constructor_invalidDifficultyTag_throwsIllegalArgumentException() {
        String invalidGenderTagName = "NONBINARY";
        assertThrows(IllegalArgumentException.class, () -> new DifficultyTag(invalidGenderTagName));
    }

    @Test
    public void isValidDifficultyTagName() {
        // null gender tag
        assertThrows(NullPointerException.class, () -> GenderTag.isValidGenderTag(null));

        // valid gender tag
        assertTrue(GenderTag.isValidGenderTag("M")); // M gender tag
        assertTrue(GenderTag.isValidGenderTag("F")); // F gender tag
        assertTrue(GenderTag.isValidGenderTag("NEUTRAL")); // Neutral gender tag
        assertTrue(GenderTag.isValidGenderTag("none")); // none gender tag
        assertTrue(GenderTag.isValidGenderTag(" none ")); // none gender tag with whitespace
        assertTrue(GenderTag.isValidGenderTag("NoNe")); // none gender tag case insensitive

        //invalid gender tags
        assertFalse(GenderTag.isValidGenderTag("NONBINARY")); // invalid gender tag
        assertFalse(GenderTag.isValidGenderTag("2")); // invalid gender tag
        assertFalse(GenderTag.isValidGenderTag(" ")); // invalid gender tag
    }
}

