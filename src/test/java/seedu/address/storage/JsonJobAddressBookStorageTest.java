package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalJobs.FACEBOOK;
import static seedu.address.testutil.TypicalJobs.IRAS;
import static seedu.address.testutil.TypicalJobs.MAYBANK;
import static seedu.address.testutil.TypicalJobs.getTypicalJobAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.JobAddressBook;
import seedu.address.model.ReadOnlyJobAddressBook;

public class JsonJobAddressBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonJobAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readJobAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readJobAddressBook(null));
    }

    private java.util.Optional<ReadOnlyJobAddressBook> readJobAddressBook(String filePath) throws Exception {
        return new JsonJobAddressBookStorage(Paths.get(filePath))
                .readJobAddressBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readJobAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readJobAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readJobAddressBook_invalidJobAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readJobAddressBook("invalidJobAddressBook.json"));
    }

    @Test
    public void readJobAddressBook_invalidAndValidJobAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readJobAddressBook("invalidAndValidJobAddressBook.json"));
    }

    @Test
    public void readAndSaveJobAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        JobAddressBook original = getTypicalJobAddressBook();
        JsonJobAddressBookStorage jsonJobAddressBookStorage = new JsonJobAddressBookStorage(filePath);

        // Save in new file and read back
        jsonJobAddressBookStorage.saveJobAddressBook(original, filePath);
        ReadOnlyJobAddressBook readBack = jsonJobAddressBookStorage.readJobAddressBook(filePath).get();
        assertEquals(original, new JobAddressBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addJob(MAYBANK);
        original.removeJob(FACEBOOK);
        jsonJobAddressBookStorage.saveJobAddressBook(original, filePath);
        readBack = jsonJobAddressBookStorage.readJobAddressBook(filePath).get();
        assertEquals(original, new JobAddressBook(readBack));

        // Save and read without specifying file path
        original.addJob(IRAS);
        jsonJobAddressBookStorage.saveJobAddressBook(original); // file path not specified
        readBack = jsonJobAddressBookStorage.readJobAddressBook().get(); // file path not specified
        assertEquals(original, new JobAddressBook(readBack));

    }

    @Test
    public void saveJobAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveJobAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveJobAddressBook(ReadOnlyJobAddressBook addressBook, String filePath) {
        try {
            new JsonJobAddressBookStorage(Paths.get(filePath))
                    .saveJobAddressBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveJobAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveJobAddressBook(new JobAddressBook(), null));
    }
}
