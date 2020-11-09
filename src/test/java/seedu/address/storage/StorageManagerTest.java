package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;
import static seedu.address.testutil.contact.TypicalContacts.getTypicalContactList;
import static seedu.address.testutil.todolist.TypicalTasks.getTypicalTodoList;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.ContactList;
import seedu.address.model.ModuleList;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.ReadOnlyTodoList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonModuleListStorage moduleListStorage = new JsonModuleListStorage(getTempFilePath("ab"));
        JsonModuleListStorage archivedModuleListStorage = new JsonModuleListStorage(getTempFilePath("ar"));
        JsonContactListStorage contactListStorage = new JsonContactListStorage(getTempFilePath("cd"));
        JsonTodoListStorage todoListStorage = new JsonTodoListStorage(getTempFilePath("ef"));
        JsonEventListStorage eventListStorage = new JsonEventListStorage(getTempFilePath("gh"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(moduleListStorage, archivedModuleListStorage, contactListStorage,
                todoListStorage, eventListStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void moduleListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonModuleListStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonModuleListStorageTest} class.
         */
        ModuleList original = getTypicalModuleList();
        storageManager.saveModuleList(original);
        ReadOnlyModuleList retrieved = storageManager.readModuleList().get();
        assertEquals(original, new ModuleList(retrieved));
    }
    @Test
    public void getModuleListFilePath() {
        assertNotNull(storageManager.getModuleListFilePath());
    }

    @Test
    public void contactListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonContactListStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonContactListStorageTest} class.
         */
        ContactList original = getTypicalContactList();
        storageManager.saveContactList(original);
        ReadOnlyContactList retrieved = storageManager.readContactList().get();
        assertEquals(original, new ContactList(retrieved));
    }
    @Test
    public void getContactListFilePath() {
        assertNotNull(storageManager.getContactListFilePath());
    }

    @Test
    public void todoListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonTodoListStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonTodoListStorageTest} class.
         */
        TodoList original = getTypicalTodoList();
        storageManager.saveTodoList(original);
        ReadOnlyTodoList retrieved = storageManager.readTodoList().get();
        assertEquals(original, new TodoList(retrieved));
    }
    @Test
    public void getTodoListFilePath() {
        assertNotNull(storageManager.getTodoListFilePath());
    }
}
