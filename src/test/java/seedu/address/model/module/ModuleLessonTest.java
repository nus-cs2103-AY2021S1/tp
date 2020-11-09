package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleLessonTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleLesson(null));
    }

    @Test
    public void constructor_invalidLesson_throwsIllegalArgumentException() {
        String invalidLesson = "";
        assertThrows(IllegalArgumentException.class, () -> new ModuleLesson(invalidLesson));
    }

    @Test
    public void isValidLesson() {
        // null lesson
        assertThrows(NullPointerException.class, () -> ModuleLesson.isValidLesson(null));

        // invalid lesson
        assertFalse(ModuleLesson.isValidLesson("")); // empty string
        assertFalse(ModuleLesson.isValidLesson("  ")); // spaces only
        assertFalse(ModuleLesson.isValidLesson("&^")); // only non-alphanumeric characters
        assertFalse(ModuleLesson.isValidLesson("lecture$")); // contains non-alphanumeric characters

        // valid lesson
        assertTrue(ModuleLesson.isValidLesson("lecture")); // alphabets only
        assertTrue(ModuleLesson.isValidLesson("123456")); // numbers only
        assertTrue(ModuleLesson.isValidLesson("lecture6")); // alphanumeric characters
        assertTrue(ModuleLesson.isValidLesson("lecture 10")); // alphanumeric characters with whitespace
        assertTrue(ModuleLesson.isValidLesson("Lecture17")); // with capital letters
        assertTrue(ModuleLesson.isValidLesson("lecture-weds")); // alphabets and hyphen character
        assertTrue(ModuleLesson.isValidLesson("lecture on weds and fri")); // long lesson
    }

}
