package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestUtil.basicEqualsTests;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.client.Address;
import seedu.address.model.tag.Tag;

class NoteTest {

    private static final String NOTE_CONTENT_1 = "note content 1";
    private static final String NOTE_CONTENT_2 = "note content 2";
    private static final Tag TAG_1 = new Tag("tag1");
    private static final Tag TAG_2 = new Tag("tag2");
    private Note note1;
    private Set<Tag> tags;

    @BeforeEach
    public void setUp() {
        note1 = new Note(NOTE_CONTENT_1);
        tags = new HashSet<>();
        tags.add(TAG_1);
        tags.add(TAG_2);
    }

    @Test
    public void isValidNote() {
        // null note
        assertThrows(NullPointerException.class, () -> Note.isValidNote(null));

        // invalid note
        assertFalse(Address.isValidAddress("")); // empty string
        assertFalse(Address.isValidAddress(" ")); // spaces only

        // valid notes
        assertTrue(Address.isValidAddress("some note 123")); // alphanumeric characters
        assertTrue(Address.isValidAddress("-")); // one character
    }

    @Test
    public void equals_basicTests() {
        // basic equals tests
        basicEqualsTests(note1);
    }

    @Test
    public void equals_sameNoteContents_returnTrue() {
        Note note1Duplicate = new Note(NOTE_CONTENT_1);
        assertEquals(note1, note1Duplicate);
    }

    @Test
    public void equals_differentNoteContents_returnFalse() {
        Note note2 = new Note(NOTE_CONTENT_2);
        assertNotEquals(note1, note2);
    }

    @Test
    public void equals_sameNoteContentSameTags_returnTrue() {
        Note note1Duplicate = new Note(NOTE_CONTENT_1);
        note1.setTags(tags);
        note1Duplicate.setTags(tags);
        assertEquals(note1, note1Duplicate);
    }

    @Test
    public void equals_sameNoteContentDifferentTags_returnFalse() {
        Note note1Duplicate = new Note(NOTE_CONTENT_1);
        note1.setTags(tags);
        note1Duplicate.setTags(tags);
        Set<Tag> rougeTags = new HashSet<>();
        rougeTags.add(new Tag("rogueTag"));
        note1Duplicate.setTags(rougeTags);
        assertNotEquals(note1, note1Duplicate);
    }

}
