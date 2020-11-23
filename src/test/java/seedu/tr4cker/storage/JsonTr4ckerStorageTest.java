package seedu.tr4cker.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.tr4cker.testutil.Assert.assertThrows;
import static seedu.tr4cker.testutil.TypicalTasks.TASK1;
import static seedu.tr4cker.testutil.TypicalTasks.TASK_H;
import static seedu.tr4cker.testutil.TypicalTasks.TASK_I;
import static seedu.tr4cker.testutil.TypicalTasks.getTypicalTr4cker;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.tr4cker.commons.exceptions.DataConversionException;
import seedu.tr4cker.model.ReadOnlyTr4cker;
import seedu.tr4cker.model.Tr4cker;

public class JsonTr4ckerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTr4ckerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTr4cker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTr4cker(null));
    }

    private java.util.Optional<ReadOnlyTr4cker> readTr4cker(String filePath) throws Exception {
        return new JsonTr4cker(Paths.get(filePath)).readTr4cker(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTr4cker("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTr4cker("notJsonFormatTr4cker.json"));
    }

    @Test
    public void readTr4cker_invalidTaskTr4cker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTr4cker("invalidTaskTr4cker.json"));
    }

    @Test
    public void readTr4cker_invalidAndValidTaskTr4cker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTr4cker(
                "invalidAndValidTaskTr4cker" + ".json"));
    }

    @Test
    public void readAndSaveTr4cker_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTr4cker.json");
        Tr4cker original = getTypicalTr4cker();
        JsonTr4cker jsonTr4ckerStorage = new JsonTr4cker(filePath);

        // Save in new file and read back
        jsonTr4ckerStorage.saveTr4cker(original, filePath);
        ReadOnlyTr4cker readBack = jsonTr4ckerStorage.readTr4cker(filePath).get();
        assertEquals(original, new Tr4cker(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addTask(TASK_H);
        original.removeTask(TASK1);
        jsonTr4ckerStorage.saveTr4cker(original, filePath);
        readBack = jsonTr4ckerStorage.readTr4cker(filePath).get();
        assertEquals(original, new Tr4cker(readBack));

        // Save and read without specifying file path
        original.addTask(TASK_I);
        jsonTr4ckerStorage.saveTr4cker(original); // file path not specified
        readBack = jsonTr4ckerStorage.readTr4cker().get(); // file path not specified
        assertEquals(original, new Tr4cker(readBack));

    }

    @Test
    public void saveTr4cker_nullTr4cker_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTr4cker(null, "SomeFile.json"));
    }

    /**
     * Saves {@code tr4cker} at the specified {@code filePath}.
     */
    private void saveTr4cker(ReadOnlyTr4cker tr4cker, String filePath) {
        try {
            new JsonTr4cker(Paths.get(filePath))
                    .saveTr4cker(tr4cker, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTr4cker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTr4cker(new Tr4cker(), null));
    }
}
