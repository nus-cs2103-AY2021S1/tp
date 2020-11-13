package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.meeting.ModelMeeting.PREDICATE_SHOW_ALL_MEETINGS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.MEETING_A;
import static seedu.address.testutil.TypicalMeetings.MEETING_B;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.meeting.ModelMeetingManager;
import seedu.address.model.meeting.meeting.TitleDescriptionContainsKeywordsPredicate;
import seedu.address.testutil.MeetingBookBuilder;

public class ModelMeetingManagerTest {

    private ModelMeetingManager modelManager = new ModelMeetingManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new MeetingBook(), new MeetingBook(modelManager.getMeetingBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setMeetingBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setMeetingBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setMeetingBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setMeetingBookFilePath(null));
    }

    @Test
    public void setMeetingBookFilePath_validPath_setsMeetingBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setMeetingBookFilePath(path);
        assertEquals(path, modelManager.getMeetingBookFilePath());
    }

    @Test
    public void hasMeeting_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasMeeting(null));
    }

    @Test
    public void hasMeeting_meetingNotInMeetingBook_returnsFalse() {
        assertFalse(modelManager.hasMeeting(MEETING_A));
    }

    @Test
    public void hasMeeting_meetingInMeetingBook_returnsTrue() {
        modelManager.addMeeting(MEETING_A);
        assertTrue(modelManager.hasMeeting(MEETING_A));
    }

    @Test
    public void getFilteredMeetingList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredMeetingList().remove(0));
    }

    @Test
    public void equals() {
        MeetingBook addressBook = new MeetingBookBuilder().withMeeting(MEETING_A).withMeeting(MEETING_B).build();
        MeetingBook differentMeetingBook = new MeetingBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelMeetingManager(addressBook, userPrefs);
        ModelMeetingManager modelManagerCopy = new ModelMeetingManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelMeetingManager(differentMeetingBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = MEETING_A.getTitle().value.split("\\s+");
        modelManager.updateFilteredMeetingList(new TitleDescriptionContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelMeetingManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setMeetingBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelMeetingManager(addressBook, differentUserPrefs)));
    }
}
