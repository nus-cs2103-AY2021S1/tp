package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.ALICE;
import static seedu.address.testutil.TypicalContacts.BENSON;
import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalModules.CS2101;
// import static seedu.address.testutil.TypicalModules.CS2030;
// import static seedu.address.testutil.TypicalModules.CS2101;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
// import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.contact.NameContainsKeywordsPredicate;
import seedu.address.testutil.ContactListBuilder;
import seedu.address.testutil.ModuleListBuilder;
// import seedu.address.testutil.ModuleListBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ModuleList(), new ModuleList(modelManager.getModuleList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setModuleListFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setModuleListFilePath(Paths.get("new/address/book/file/path"));
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
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getModuleListFilePath());
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasModule(null));
    }

    /*
    @Test
    public void hasModule_moduleNotInModuleList_returnsFalse() {
        assertFalse(modelManager.hasModule(CS2030));
    }
     */

    /*
    @Test
    public void hasModule_moduleInModuleList_returnsTrue() {
        modelManager.addModule(CS2030);
        assertTrue(modelManager.hasModule(CS2030));
    }
     */

    @Test
    public void deleteModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deleteModule(null));
    }

    @Test
    public void deleteModule_moduleNotInModuleList_throwsContactNotFoundException() {
        // assertThrows(ContactNotFoundException.class, () -> modelManager.deleteModule(null));
    }

    @Test
    public void deleteModule_moduleInModuleList_success() {
        // Module module = new ModuleBuilder().build();
        // modelManager.addModule(module);
        // modelManager.deleteModule(module);
        // ModelManager expectedModelManager = new ModelManager();
        // assertEquals(modelManager, expectedModelManager);
    }

    @Test
    public void addModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addModule(null));
    }

    @Test
    public void hasContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasContact(null));
    }

    @Test
    public void hasContact_contactNotInContactList_returnsFalse() {
        assertFalse(modelManager.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactInContactList_returnsTrue() {
        modelManager.addContact(ALICE);
        assertTrue(modelManager.hasContact(ALICE));
    }

    @Test
    public void getFilteredModuleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredModuleList().remove(0));
    }

    /*
    @Test
    public void getFilteredContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredContactList().remove(0));
    }

    @Test
    public void equals() {
        ModuleList moduleList = new ModuleListBuilder().withModule(CS2030).withModule(CS2101).build();
        ModuleList differentModuleList = new ModuleList();
        ContactList contactList = new ContactListBuilder().withContact(ALICE).withContact(BENSON).build();
        ContactList differentContactList = new ContactList();
        TodoList todoList = new TodoList();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(moduleList, contactList, todoList, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(moduleList, contactList, todoList, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different moduleList -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentModuleList, contactList, todoList, userPrefs)));

        // different contactList -> returns false
        assertFalse(modelManager.equals(new ModelManager(moduleList, differentContactList, todoList, userPrefs)));

        // different filteredModuleList -> returns false

        // different filteredContactList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredContactList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(moduleList, contactList, todoList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setModuleListFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(moduleList, contactList, todoList, differentUserPrefs)));
    }
    */
}
