package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.CS2100;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.module.ModuleContainsKeywordsPredicate;
import seedu.address.testutil.TrackrBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    //    @Test
    //    public void constructor() {
    //        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
    //        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
    //        assertEquals(new ModuleList(), new ModuleList(modelManager.getModuleList()));
    //    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTrackrFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setTrackrFilePath(path);
        assertEquals(path, modelManager.getTrackrFilePath());
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasModule(CS2100));
    }

    @Test
    public void hasModule_moduleInAddressBook_returnsTrue() {
        modelManager.addModule(CS2100);
        assertTrue(modelManager.hasModule(CS2100));
    }

    @Test
    public void getFilteredModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredModuleList().remove(0));
    }

    @Test
    public void equals() {
        Trackr trackr = new TrackrBuilder().withModule(CS2100).withModule(CS2103T).build();
        Trackr differentTrackr = new Trackr();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(trackr, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(trackr, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTrackr, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = CS2100.getModuleId().toString().split("\\s+");
        modelManager.updateFilteredModuleList(new ModuleContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(trackr, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredModuleList(Model.PREDICATE_SHOW_ALL_MODULES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(trackr, differentUserPrefs)));
    }

    //    @Test
    //    public void hasPerson_nullPerson_throwsNullPointerException() {
    //        assertThrows(NullPointerException.class, () -> modelManager.hasModule(null));
    //    }
    //
    //    @Test
    //    public void hasPerson_personNotInAddressBook_returnsFalse() {
    //        assertFalse(modelManager.hasModule(ALICE));
    //    }
    //
    //    @Test
    //    public void hasPerson_personInAddressBook_returnsTrue() {
    //        modelManager.addPerson(ALICE);
    //        assertTrue(modelManager.hasPerson(ALICE));
    //    }

    //    @Test
    //    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
    //        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    //    }

    //    @Test
    //    public void equals() {
    //        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
    //        AddressBook differentAddressBook = new AddressBook();
    //        UserPrefs userPrefs = new UserPrefs();
    //
    //        // same values -> returns true
    //        modelManager = new ModelManager(addressBook, userPrefs);
    //        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
    //        assertTrue(modelManager.equals(modelManagerCopy));
    //
    //        // same object -> returns true
    //        assertTrue(modelManager.equals(modelManager));
    //
    //        // null -> returns false
    //        assertFalse(modelManager.equals(null));
    //
    //        // different types -> returns false
    //        assertFalse(modelManager.equals(5));
    //
    //        // different addressBook -> returns false
    //        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));
    //
    //        // different filteredList -> returns false
    //        String[] keywords = ALICE.getName().fullName.split("\\s+");
    //        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
    //        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));
    //
    //        // resets modelManager to initial state for upcoming tests
    //        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    //
    //        // different userPrefs -> returns false
    //        UserPrefs differentUserPrefs = new UserPrefs();
    //        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
    //        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    //    }
}
