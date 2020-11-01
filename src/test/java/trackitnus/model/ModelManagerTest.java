package trackitnus.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackitnus.model.Model.PREDICATE_SHOW_ALL_CONTACTS;
import static trackitnus.testutil.Assert.assertThrows;
import static trackitnus.testutil.typical.TypicalContacts.ALICE;
import static trackitnus.testutil.typical.TypicalContacts.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import trackitnus.commons.core.GuiSettings;
import trackitnus.commons.core.index.Index;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.model.contact.NameContainsKeywordsPredicate;
import trackitnus.testutil.builder.TrackIterBuilder;
import trackitnus.testutil.typical.TypicalTask;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TrackIter(), new TrackIter(modelManager.getTrackIter()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTrackIterFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTrackIterFilePath(Paths.get("new/address/book/file/path"));
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
    public void setTrackIterFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTrackIterFilePath(null));
    }

    @Test
    public void setTrackIterFilePath_validPath_setsTrackIterFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setTrackIterFilePath(path);
        assertEquals(path, modelManager.getTrackIterFilePath());
    }

    @Test
    public void hasContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasContact(null));
    }

    @Test
    public void hasContact_contactNotInTrackIter_returnsFalse() {
        assertFalse(modelManager.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactInTrackIter_returnsTrue() {
        modelManager.addContact(ALICE);
        assertTrue(modelManager.hasContact(ALICE));
    }

    @Test
    public void getFilteredContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredContactList().remove(0));
    }

    @Test
    public void getTaskIndex_invalidIndex_throwsCommandException() {
        assertThrows(CommandException.class, () -> modelManager.getTaskIndex(TypicalTask.TASK_FULL));
    }

    @Test
    public void getTaskIndex_validIndex_success() {
        try {
            modelManager.addTask(TypicalTask.TASK_FULL);
            assertEquals(Index.fromOneBased(1), modelManager.getTaskIndex(TypicalTask.TASK_FULL));
        } catch (CommandException e) {
            Assertions.fail();
        }
    }

    @Test
    public void getTaskIndex_rightIndex_success() {
        try {
            modelManager.addTask(TypicalTask.TASK_FULL);
            modelManager.addTask(TypicalTask.TASK_FULL_ANOTHER);
            assertEquals(Index.fromOneBased(2), modelManager.getTaskIndex(TypicalTask.TASK_FULL_ANOTHER));
        } catch (CommandException e) {
            Assertions.fail();
        }
    }

    @Test
    public void getTaskIndex_wrongIndex_failure() {
        try {
            modelManager.addTask(TypicalTask.TASK_FULL);
            modelManager.addTask(TypicalTask.TASK_FULL_ANOTHER);
            assertNotEquals(Index.fromOneBased(1), modelManager.getTaskIndex(TypicalTask.TASK_FULL_ANOTHER));
        } catch (CommandException e) {
            Assertions.fail();
        }
    }

    @Test
    public void equals() {
        TrackIter trackIter = new TrackIterBuilder().withContact(ALICE).withContact(BENSON).build();
        TrackIter differentTrackIter = new TrackIter();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(trackIter, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(trackIter, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different trackIter -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTrackIter, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().value.split("\\s+");
        modelManager.updateFilteredContactList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(trackIter, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTrackIterFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(trackIter, differentUserPrefs)));
    }
}
