package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LessonNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LessonName(null));
    }

    @Test
    public void constructor_invalidLessonName_throwsIllegalArgumentException() {
        String invalidLessonName = "";
        assertThrows(IllegalArgumentException.class, () -> new LessonName(invalidLessonName));
    }

    @Test
    public void isValidLessonName() {
        // null name
        assertThrows(NullPointerException.class, () -> LessonName.isValidLessonName(null));

        // invalid name
        assertFalse(LessonName.isValidLessonName("")); // empty string
        assertFalse(LessonName.isValidLessonName(" ")); // spaces only
        assertFalse(LessonName.isValidLessonName("^")); // only non-alphanumeric characters
        assertFalse(LessonName.isValidLessonName("what?*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(LessonName.isValidLessonName("Lecture")); // alphabets only
        assertTrue(LessonName.isValidLessonName("Tutorial")); // numbers only
        assertTrue(LessonName.isValidLessonName("Laboratory")); // alphanumeric characters
        assertTrue(LessonName.isValidLessonName("Recitation")); // with capital letters
        assertTrue(LessonName.isValidLessonName("Sectional Teachings")); // long names
    }
}
