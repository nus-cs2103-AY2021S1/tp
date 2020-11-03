package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.CS1231S_HW;
import static seedu.address.testutil.TypicalAssignments.READ;
import static seedu.address.testutil.TypicalAssignments.SLIDE;
import static seedu.address.testutil.TypicalAssignments.getTypicalProductiveNus;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ProductiveNus;
import seedu.address.model.ReadOnlyProductiveNus;

public class JsonProductiveNusStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonProductiveNusStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readProductiveNus_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readProductiveNus(null));
    }

    private java.util.Optional<ReadOnlyProductiveNus> readProductiveNus(String filePath) throws Exception {
        return new JsonProductiveNusStorage(Paths.get(filePath))
                .readProductiveNus(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readProductiveNus("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readProductiveNus("notJsonFormatProductiveNus.json"));
    }

    @Test
    public void readProductiveNus_invalidAssignmentProductiveNus_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readProductiveNus("invalidAssignmentProductiveNus.json"));
    }

    @Test
    public void readProductiveNus_invalidAndValidAssignmentProductiveNus_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readProductiveNus("invalidAndValidAssignmentProductiveNus.json"));
    }

    @Test
    public void readAndSaveProductiveNus_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempProductiveNus.json");
        ProductiveNus original = getTypicalProductiveNus();
        JsonProductiveNusStorage jsonProductiveNusStorage = new JsonProductiveNusStorage(filePath);

        // Save in new file and read back
        jsonProductiveNusStorage.saveProductiveNus(original, filePath);
        ReadOnlyProductiveNus readBack = jsonProductiveNusStorage.readProductiveNus(filePath).get();
        assertEquals(original, new ProductiveNus(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addAssignment(READ);
        original.removeAssignment(CS1231S_HW);
        jsonProductiveNusStorage.saveProductiveNus(original, filePath);
        readBack = jsonProductiveNusStorage.readProductiveNus(filePath).get();
        assertEquals(original, new ProductiveNus(readBack));

        // Save and read without specifying file path
        original.addAssignment(SLIDE);
        jsonProductiveNusStorage.saveProductiveNus(original); // file path not specified
        readBack = jsonProductiveNusStorage.readProductiveNus().get(); // file path not specified
        assertEquals(original, new ProductiveNus(readBack));

    }

    @Test
    public void saveProductiveNus_nullAProductiveNus_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveProductiveNus(null, "SomeFile.json"));
    }

    /**
     * Saves {@code ProductiveNus} at the specified {@code filePath}.
     */
    private void saveProductiveNus(ReadOnlyProductiveNus productiveNus, String filePath) {
        try {
            new JsonProductiveNusStorage(Paths.get(filePath))
                    .saveProductiveNus(productiveNus, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveProductiveNus_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveProductiveNus(new ProductiveNus(), null));
    }
}
