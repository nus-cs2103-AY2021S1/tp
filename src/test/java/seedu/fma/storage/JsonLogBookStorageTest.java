package seedu.fma.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.fma.testutil.Assert.assertThrows;
import static seedu.fma.testutil.TypicalLogs.LOG_A;
import static seedu.fma.testutil.TypicalLogs.LOG_E;
import static seedu.fma.testutil.TypicalLogs.LOG_F;
import static seedu.fma.testutil.TypicalLogs.getTypicalLogBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.fma.commons.exceptions.DataConversionException;
import seedu.fma.model.LogBook;
import seedu.fma.model.ReadOnlyLogBook;

public class JsonLogBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonLogBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyLogBook> readAddressBook(String filePath) throws Exception {
        return new JsonLogBookStorage(Paths.get(filePath)).readLogBook(addToTestDataPathIfNotNull(filePath));
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
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatLogBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidEntryLogBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidEntryLogBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        LogBook original = getTypicalLogBook();
        JsonLogBookStorage jsonAddressBookStorage = new JsonLogBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveLogBook(original, filePath);
        ReadOnlyLogBook readBack = jsonAddressBookStorage.readLogBook(filePath).get();
        assertEquals(original, new LogBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addLog(LOG_E);
        original.removeLog(LOG_A);
        jsonAddressBookStorage.saveLogBook(original, filePath);
        readBack = jsonAddressBookStorage.readLogBook(filePath).get();
        assertEquals(original, new LogBook(readBack));

        // Save and read without specifying file path
        original.addLog(LOG_F);
        jsonAddressBookStorage.saveLogBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readLogBook().get(); // file path not specified
        assertEquals(original, new LogBook(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyLogBook addressBook, String filePath) {
        try {
            new JsonLogBookStorage(Paths.get(filePath))
                    .saveLogBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new LogBook(), null));
    }
}
