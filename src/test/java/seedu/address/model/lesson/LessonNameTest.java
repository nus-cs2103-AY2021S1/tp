package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.util.Name;

public class LessonNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidLessonName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidLessonName() {
        // null lesson name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid lesson name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("cs1234*")); // contains non-alphanumeric characters

        // valid lesson name
        assertTrue(Name.isValidName("cs")); // alphabets only
        assertTrue(Name.isValidName("1234")); // numbers only
        assertTrue(Name.isValidName("cs1234")); // alphanumeric characters
        assertTrue(Name.isValidName("CS1234")); // with capital letters
        assertTrue(Name.isValidName("CS1234 at Auditorium in the morning")); // long names
    }
}
