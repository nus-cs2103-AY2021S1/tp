package seedu.pivot.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.pivot.testutil.Assert.assertThrows;
import static seedu.pivot.testutil.TypicalCases.ALICE;
import static seedu.pivot.testutil.TypicalCases.HOON;
import static seedu.pivot.testutil.TypicalCases.IDA;
import static seedu.pivot.testutil.TypicalCases.getTypicalAddressBook;

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
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyPivot> readAddressBook(String filePath) throws Exception {
        return new JsonPivotStorage(Paths.get(filePath)).readPivot(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatPivot.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidPersonPivot.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidPersonPivot.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        Pivot original = getTypicalAddressBook();
        JsonPivotStorage jsonAddressBookStorage = new JsonPivotStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.savePivot(original, filePath);
        ReadOnlyPivot readBack = jsonAddressBookStorage.readPivot(filePath).get();
        assertEquals(original, new Pivot(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addCase(HOON);
        original.removeCase(ALICE);
        jsonAddressBookStorage.savePivot(original, filePath);
        readBack = jsonAddressBookStorage.readPivot(filePath).get();
        assertEquals(original, new Pivot(readBack));

        // Save and read without specifying file path
        original.addCase(IDA);
        jsonAddressBookStorage.savePivot(original); // file path not specified
        readBack = jsonAddressBookStorage.readPivot().get(); // file path not specified
        assertEquals(original, new Pivot(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyPivot addressBook, String filePath) {
        try {
            new JsonPivotStorage(Paths.get(filePath))
                    .savePivot(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new Pivot(), null));
    }
}
