package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.DEADLINE1;
import static seedu.address.testutil.TypicalTasks.DEADLINE5;
import static seedu.address.testutil.TypicalTasks.EVENT4;
import static seedu.address.testutil.TypicalTasks.getTypicalPlanus;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Planus;
import seedu.address.model.ReadOnlyPlanus;

public class JsonPlanusStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPlanusStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPlanus_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPlanus(null));
    }

    private java.util.Optional<ReadOnlyPlanus> readPlanus(String filePath) throws Exception {
        return new JsonPlanusStorage(Paths.get(filePath)).readPlanus(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPlanus("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readPlanus("notJsonFormatPlanus.json"));
    }

    @Test
    public void readPlanus_invalidTaskPlanus_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPlanus("invalidTaskPlanus.json"));
    }

    @Test
    public void readPlanus_invalidAndValidTaskPlanus_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPlanus("invalidAndValidTaskPlanus.json"));
    }

    @Test
    public void readAndSavePlanus_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempPlanus.json");
        Planus original = getTypicalPlanus();
        JsonPlanusStorage jsonPlanusStorage = new JsonPlanusStorage(filePath);

        // Save in new file and read back
        jsonPlanusStorage.savePlanus(original, filePath);
        ReadOnlyPlanus readBack = jsonPlanusStorage.readPlanus(filePath).get();
        assertEquals(original, new Planus(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addTask(DEADLINE5);
        original.removeTask(DEADLINE1);
        jsonPlanusStorage.savePlanus(original, filePath);
        readBack = jsonPlanusStorage.readPlanus(filePath).get();
        assertEquals(original, new Planus(readBack));

        // Save and read without specifying file path
        original.addTask(EVENT4);
        jsonPlanusStorage.savePlanus(original); // file path not specified
        readBack = jsonPlanusStorage.readPlanus().get(); // file path not specified
        assertEquals(original, new Planus(readBack));

    }

    @Test
    public void savePlanus_nullPlanus_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePlanus(null, "SomeFile.json"));
    }

    /**
     * Saves {@code planus} at the specified {@code filePath}.
     */
    private void savePlanus(ReadOnlyPlanus planus, String filePath) {
        try {
            new JsonPlanusStorage(Paths.get(filePath))
                    .savePlanus(planus, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savePlanus_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePlanus(new Planus(), null));
    }
}
