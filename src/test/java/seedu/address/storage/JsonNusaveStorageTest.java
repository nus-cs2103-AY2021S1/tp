package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalBudgets.getTypicalNusave;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Nusave;
import seedu.address.model.ReadOnlyNusave;
import seedu.address.testutil.TypicalBudget;

class JsonNusaveStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonNusaveStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readNusave_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readNusave(null));
    }

    private java.util.Optional<ReadOnlyNusave> readNusave(String filePath) throws Exception {
        return new JsonNusaveStorage(Paths.get(filePath)).readNusave(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readNusave("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readNusave("notJsonFormatNusave.json"));
    }

    @Test
    public void readAddressBook_invalidBudgetNusave_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readNusave("invalidBudgetNusave.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidBudgetNusave_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readNusave("invalidAndValidBudgetNusave.json"));
    }

    @Test
    public void readAndSaveNusave_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempNusave.json");
        Nusave original = getTypicalNusave();
        JsonNusaveStorage jsonNusaveStorage = new JsonNusaveStorage(filePath);

        // Save in new file and read back
        jsonNusaveStorage.saveNusave(original, filePath);
        ReadOnlyNusave readBack = jsonNusaveStorage.readNusave(filePath).get();
        assertEquals(original, new Nusave(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addBudget(TypicalBudget.getMcDonaldsBudget());
        original.deleteBudget(TypicalBudget.getKfcBudget());
        jsonNusaveStorage.saveNusave(original, filePath);
        readBack = jsonNusaveStorage.readNusave(filePath).get();
        assertEquals(original, new Nusave(readBack));

        // Save and read without specifying file path
        original.addBudget(TypicalBudget.getKfcBudget());
        jsonNusaveStorage.saveNusave(original); // file path not specified
        readBack = jsonNusaveStorage.readNusave().get(); // file path not specified
        assertEquals(original, new Nusave(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveNusave(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveNusave(ReadOnlyNusave nusave, String filePath) {
        try {
            new JsonNusaveStorage(Paths.get(filePath))
                    .saveNusave(nusave, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveNusave(new Nusave(), null));
    }
}
