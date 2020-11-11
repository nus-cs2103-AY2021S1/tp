package seedu.bookmark.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(AssertionError.class, () -> new Note("Hello", null));
        assertThrows(AssertionError.class, () -> new Note(null, "Hello"));
        assertThrows(AssertionError.class, () -> new Note(null, null));
    }

    @Test
    public void constructor_invalidTitle_throwsIllegalArgumentException() {
        String invalidTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new Note(invalidTitle, "Valid Text."));
    }

    @Test
    public void constructor_invalidText_throwsIllegalArgumentException() {
        String invalidText = "";
        assertThrows(IllegalArgumentException.class, () -> new Note("Valid Title.", invalidText));
    }

    @Test
    public void isValidNote() {
        // null text
        assertThrows(NullPointerException.class, () -> Note.isValidNote("Hello", null));
        // null title
        assertThrows(NullPointerException.class, () -> Note.isValidNote(null, "Hello"));
        // null title and text
        assertThrows(NullPointerException.class, () -> Note.isValidNote(null, null));

        // invalid note
        assertFalse(Note.isValidNote("", "")); // empty string
        assertFalse(Note.isValidNote(" ", " ")); // spaces only

        // valid note
        assertTrue(Note.isValidNote("peter jack", "peter jack")); // alphabets only
        assertTrue(Note.isValidNote("12345", "12345")); // numbers only
        assertTrue(Note.isValidNote("peter the 2nd", "peter the 2nd")); // alphanumeric characters
        assertTrue(Note.isValidNote("Capital Tan", "Capital Tan")); // with capital letters
        assertTrue(Note.isValidNote("David Roger Jackson Ray Jr 2nd", "David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Note.isValidNote("^", "^")); // only non-alphanumeric characters
        assertTrue(Note.isValidNote("peter*", "peter*")); // contains non-alphanumeric characters
    }
}
