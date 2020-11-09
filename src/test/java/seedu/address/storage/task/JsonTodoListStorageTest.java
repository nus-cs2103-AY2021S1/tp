package seedu.address.storage.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_01;
import static seedu.address.testutil.todolist.TypicalTasks.TUTORIAL_01;
import static seedu.address.testutil.todolist.TypicalTasks.TUTORIAL_02;
import static seedu.address.testutil.todolist.TypicalTasks.getTypicalTodoList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTodoList;
import seedu.address.model.TodoList;
import seedu.address.storage.JsonTodoListStorage;

public class JsonTodoListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonTodoListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTodoList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTodoList(null));
    }

    private java.util.Optional<ReadOnlyTodoList> readTodoList(String filePath) throws Exception {
        return new JsonTodoListStorage(Paths.get(filePath)).readTodoList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTodoList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTodoList("notJsonFormatTodoList.json"));
    }

    @Test
    public void readTodoList_invalidTaskTodoList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTodoList("invalidTaskTodoList.json"));
    }

    @Test
    public void readTodoList_invalidAndValidTaskTodoList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readTodoList("invalidAndValidTaskTodoList.json"));
    }

    @Test
    public void readAndSaveTodoList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTodoList.json");
        TodoList original = getTypicalTodoList();
        JsonTodoListStorage jsonTodoListStorage = new JsonTodoListStorage(filePath);

        // Save in new file and read back
        jsonTodoListStorage.saveTodoList(original, filePath);
        ReadOnlyTodoList readBack = jsonTodoListStorage.readTodoList(filePath).get();
        assertEquals(original, new TodoList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addTask(TUTORIAL_01);
        original.removeTask(LAB_01);
        jsonTodoListStorage.saveTodoList(original, filePath);
        readBack = jsonTodoListStorage.readTodoList(filePath).get();
        assertEquals(original, new TodoList(readBack));

        // Save and read without specifying file path
        original.addTask(TUTORIAL_02);
        jsonTodoListStorage.saveTodoList(original); // file path not specified
        readBack = jsonTodoListStorage.readTodoList().get(); // file path not specified
        assertEquals(original, new TodoList(readBack));
    }

    @Test
    public void saveTodoList_nullTodoList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTodoList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code TodoList} at the specified {@code filePath}.
     */
    private void saveTodoList(ReadOnlyTodoList todoList, String filePath) {
        try {
            new JsonTodoListStorage(Paths.get(filePath))
                    .saveTodoList(todoList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTodoList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTodoList(new TodoList(), null));
    }
}
