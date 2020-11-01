package seedu.stock.model.stock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Note(null));
    }

    @Test
    public void constructor_invalidNote_throwsIllegalArgumentException() {
        String invalidNote = "";
        assertThrows(IllegalArgumentException.class, () -> new Note(invalidNote));
    }

    @Test
    public void isValidNote() {
        // null note
        assertThrows(NullPointerException.class, () -> Note.isValidNote(null));

        // invalid note
        assertFalse(Note.isValidNote("")); // empty string
        assertFalse(Note.isValidNote(" ")); // spaces only

        // valid Note
        assertTrue(Note.isValidNote("apple")); // alphabets only
        assertTrue(Note.isValidNote("134324")); // numbers only
        assertTrue(Note.isValidNote("fan 1234")); // alphanumeric characters
        assertTrue(Note.isValidNote("Apple Banana")); // with capital letters
        assertTrue(Note.isValidNote("^@")); // with non-alphanumeric characters
        assertTrue(Note.isValidNote("fruit*")); // with non-alphanumeric characters
        assertTrue(Note.isValidNote("Apple Banana Orange Citrus fruit")); // long Notes
    }

    @Test
    public void equals() {
        final Note note = new Note("default note");

        // same values -> returns true
        assertTrue(note.equals(new Note("default note")));

        // same object -> returns true
        assertTrue(note.equals(note));

        // null -> returns false
        assertFalse(note.equals(null));

        // different types -> returns false
        assertFalse(note.equals(5));

        // different note -> returns false
        assertFalse(note.equals(new Note("different note")));
    }

}
