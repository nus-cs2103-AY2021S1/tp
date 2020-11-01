package seedu.stock.model.stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteIndexTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NoteIndex(null));
    }

    @Test
    public void constructor_invalidNoteIndex_throwsIllegalArgumentException() {
        String invalidNoteIndex = "";
        String secondInvalidNoteIndex = "invalid";
        assertThrows(IllegalArgumentException.class, () -> new NoteIndex(invalidNoteIndex));
        assertThrows(IllegalArgumentException.class, () -> new NoteIndex(secondInvalidNoteIndex));
    }

    @Test
    public void isValidNoteIndex() {
        // null note index
        assertThrows(NullPointerException.class, () -> NoteIndex.isValidNoteIndex(null));

        // invalid note index
        assertFalse(NoteIndex.isValidNoteIndex("")); // empty string
        assertFalse(NoteIndex.isValidNoteIndex("   ")); // spaces only
        assertFalse(NoteIndex.isValidNoteIndex("^")); // only non-alphanumeric characters
        assertFalse(NoteIndex.isValidNoteIndex("fdfs")); // alphabets only
        assertFalse(NoteIndex.isValidNoteIndex("-199")); // negative number
        assertFalse(NoteIndex.isValidNoteIndex(Integer.toString(Integer.MAX_VALUE + 1))); //Overflow

        // valid note index
        assertTrue(NoteIndex.isValidNoteIndex("0")); // zero
        assertTrue(NoteIndex.isValidNoteIndex("14")); // numbers only
        assertTrue(NoteIndex.isValidNoteIndex("2324254")); // large number
        assertTrue(NoteIndex.isValidNoteIndex(Integer.toString(Integer.MAX_VALUE))); //Max integer value
    }

    @Test
    public void createOneBasedIndex() {

        // check equality using the same base
        assertEquals(1, NoteIndex.fromOneBased("1").getOneBased());
        assertEquals(5, NoteIndex.fromOneBased("5").getOneBased());

        // convert from one-based index to zero-based index
        assertEquals(0, NoteIndex.fromOneBased("1").getZeroBased());
        assertEquals(4, NoteIndex.fromOneBased("5").getZeroBased());
    }

    @Test
    public void createZeroBasedIndex() {

        // check equality using the same base
        assertEquals(0, NoteIndex.fromZeroBased("0").getZeroBased());
        assertEquals(5, NoteIndex.fromZeroBased("5").getZeroBased());

        // convert from zero-based index to one-based index
        assertEquals(1, NoteIndex.fromZeroBased("0").getOneBased());
        assertEquals(6, NoteIndex.fromZeroBased("5").getOneBased());
    }

    @Test
    public void equals() {
        final NoteIndex fifthNoteIndex = NoteIndex.fromOneBased("5");

        // same values -> returns true
        assertTrue(fifthNoteIndex.equals(NoteIndex.fromOneBased("5")));
        assertTrue(fifthNoteIndex.equals(NoteIndex.fromZeroBased("4")));

        // same object -> returns true
        assertTrue(fifthNoteIndex.equals(fifthNoteIndex));

        // null -> returns false
        assertFalse(fifthNoteIndex.equals(null));

        // different types -> returns false
        assertFalse(fifthNoteIndex.equals(5.0f));

        // different index -> returns false
        assertFalse(fifthNoteIndex.equals(NoteIndex.fromZeroBased("1")));
    }
}
