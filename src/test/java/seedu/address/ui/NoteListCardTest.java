package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TestUtil.basicEqualsTests;

import org.junit.jupiter.api.Test;

import guitests.guihandles.NoteListCardHandle;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;

public class NoteListCardTest extends GuiUnitTest {

    private static final String DUMMY_NOTE_CONTENT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do"
            + " eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud"
            + " exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in"
            + " reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint"
            + " occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    private static final String DUMMY_NOTE_CONTENT_2 = "note content 2 here";

    private final Country country = new Country("SG");

    @Test
    public void display() {
        CountryNote countryNote = new CountryNote(DUMMY_NOTE_CONTENT, country);
        NoteListCard countryNoteCard = new NoteListCard(countryNote, 1);
        uiPartExtension.setUiPart(countryNoteCard);
        assertCardDisplay(countryNoteCard, countryNote, 1);
    }

    private void assertCardDisplay(NoteListCard countryNoteCard, CountryNote expectedCard, int expectedId) {
        guiRobot.pauseForHuman();

        NoteListCardHandle noteListCardHandle = new NoteListCardHandle(countryNoteCard.getRoot());
        assertEquals(expectedCard.getNoteContent(), noteListCardHandle.getNoteContent());
        assertEquals("#" + expectedId, noteListCardHandle.getNoteId());
    }

    @Test
    public void equals() {
        CountryNote countryNote = new CountryNote(DUMMY_NOTE_CONTENT, country);
        NoteListCard countryNoteCard = new NoteListCard(countryNote, 1);

        // basic equals tests
        basicEqualsTests(countryNoteCard);

        // different id -> returns false
        NoteListCard countryNoteCard2 = new NoteListCard(countryNote, 2);
        assertFalse(countryNoteCard.equals(countryNoteCard2));

        // different country note -> returns false
        CountryNote countryNote2 = new CountryNote(DUMMY_NOTE_CONTENT_2, country);
        NoteListCard countryNoteCard3 = new NoteListCard(countryNote2, 1);
        assertFalse(countryNoteCard.equals(countryNoteCard3));

        // same id same country note -> returns true
        NoteListCard countryNoteCardCopy = new NoteListCard(countryNote, 1);
        assertTrue(countryNoteCard.equals(countryNoteCardCopy));
    }

}
