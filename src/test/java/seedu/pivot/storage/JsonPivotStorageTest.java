package seedu.pivot.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.pivot.testutil.Assert.assertThrows;
import static seedu.pivot.testutil.TypicalCases.ALICE_PAULINE_ASSAULT;
import static seedu.pivot.testutil.TypicalCases.HOON_MEIER_ARSON;
import static seedu.pivot.testutil.TypicalCases.IDA_MUELLER_STABBING;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.pivot.commons.exceptions.DataConversionException;
import seedu.pivot.model.Pivot;
import seedu.pivot.model.ReadOnlyPivot;

public class JsonPivotStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPivotStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPivot_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPivot(null));
    }

    private java.util.Optional<ReadOnlyPivot> readPivot(String filePath) throws Exception {
        return new JsonPivotStorage(Paths.get(filePath)).readPivot(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPivot("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readPivot("notJsonFormatPivot.json"));
    }

    @Test
    public void readPivot_invalidPersonPivot_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPivot("invalidPersonPivot.json"));
    }

    @Test
    public void readPivot_invalidAndValidPersonPivot_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPivot("invalidAndValidPersonPivot.json"));
    }

    @Test
    public void readAndSavePivot_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempPivot.json");
        Pivot original = getTypicalPivot();
        JsonPivotStorage jsonPivotStorage = new JsonPivotStorage(filePath);

        // Save in new file and read back
        jsonPivotStorage.savePivot(original, filePath);
        ReadOnlyPivot readBack = jsonPivotStorage.readPivot(filePath).get();
        assertEquals(original, new Pivot(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addCase(HOON_MEIER_ARSON);
        original.removeCase(ALICE_PAULINE_ASSAULT);
        jsonPivotStorage.savePivot(original, filePath);
        readBack = jsonPivotStorage.readPivot(filePath).get();
        assertEquals(original, new Pivot(readBack));

        // Save and read without specifying file path
        original.addCase(IDA_MUELLER_STABBING);
        jsonPivotStorage.savePivot(original); // file path not specified
        readBack = jsonPivotStorage.readPivot().get(); // file path not specified
        assertEquals(original, new Pivot(readBack));

    }

    @Test
    public void savePivot_nullPivot_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePivot(null, "SomeFile.json"));
    }

    /**
     * Saves {@code pivot} at the specified {@code filePath}.
     */
    private void savePivot(ReadOnlyPivot pivot, String filePath) {
        try {
            new JsonPivotStorage(Paths.get(filePath))
                    .savePivot(pivot, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savePivot_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePivot(new Pivot(), null));
    }
}
