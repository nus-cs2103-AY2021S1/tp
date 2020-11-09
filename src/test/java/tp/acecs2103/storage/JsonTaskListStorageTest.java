package tp.acecs2103.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static tp.acecs2103.testutil.Assert.assertThrows;
import static tp.acecs2103.testutil.TypicalTasks.ADMIN_EXTRA_ONE;
import static tp.acecs2103.testutil.TypicalTasks.ADMIN_EXTRA_TWO;
import static tp.acecs2103.testutil.TypicalTasks.getTypicalTaskList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import tp.acecs2103.commons.exceptions.DataConversionException;
import tp.acecs2103.model.TaskList;

public class JsonTaskListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTaskListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTaskList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTaskList(null));
    }

    private java.util.Optional<TaskList> readTaskList(String filePath) throws Exception {
        return new JsonTaskListStorage(Paths.get(filePath)).readTaskList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTaskList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTaskList("notJsonFormatTaskList.json"));
    }

    @Test
    public void readTaskList_invalidTaskTaskList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTaskList("invalidTaskTaskList.json"));
    }

    @Test
    public void readTaskList_invalidAndValidTaskTaskList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTaskList("invalidAndValidTaskTaskList.json"));
    }

    @Test
    public void readAndSaveTaskList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        TaskList original = getTypicalTaskList();
        JsonTaskListStorage jsonAddressBookStorage = new JsonTaskListStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveTaskList(original, filePath);
        TaskList readBack = jsonAddressBookStorage.readTaskList(filePath).get();
        assertEquals(original, new TaskList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.add(ADMIN_EXTRA_ONE);
        jsonAddressBookStorage.saveTaskList(original, filePath);
        readBack = jsonAddressBookStorage.readTaskList(filePath).get();
        assertEquals(original, new TaskList(readBack));

        // Save and read without specifying file path
        original.add(ADMIN_EXTRA_TWO);
        jsonAddressBookStorage.saveTaskList(original); // file path not specified
        readBack = jsonAddressBookStorage.readTaskList().get(); // file path not specified
        assertEquals(original, new TaskList(readBack));

    }

    @Test
    public void saveAddressBook_nullTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code taskList} at the specified {@code filePath}.
     */
    private void saveTaskList(TaskList taskList, String filePath) {
        try {
            new JsonTaskListStorage(Paths.get(filePath))
                    .saveTaskList(taskList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTaskList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTaskList(new TaskList(), null));
    }
}

