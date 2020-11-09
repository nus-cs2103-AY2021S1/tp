package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.PersonAddressBook;
import seedu.address.model.ReadOnlyPersonAddressBook;

public class JsonPersonAddressBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPersonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPersonAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPersonAddressBook(null));
    }

    private java.util.Optional<ReadOnlyPersonAddressBook> readPersonAddressBook(String filePath) throws Exception {
        return new JsonPersonAddressBookStorage(Paths.get(filePath))
                .readPersonAddressBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPersonAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readPersonAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readPersonAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPersonAddressBook("invalidPersonAddressBook.json"));
    }

    @Test
    public void readPersonAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readPersonAddressBook("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSavePersonAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        PersonAddressBook original = getTypicalPersonAddressBook();
        JsonPersonAddressBookStorage jsonAddressBookStorage = new JsonPersonAddressBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.savePersonAddressBook(original, filePath);
        ReadOnlyPersonAddressBook readBack = jsonAddressBookStorage.readPersonAddressBook(filePath).get();
        assertEquals(original, new PersonAddressBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonAddressBookStorage.savePersonAddressBook(original, filePath);
        readBack = jsonAddressBookStorage.readPersonAddressBook(filePath).get();
        assertEquals(original, new PersonAddressBook(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonAddressBookStorage.savePersonAddressBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readPersonAddressBook().get(); // file path not specified
        assertEquals(original, new PersonAddressBook(readBack));

    }

    @Test
    public void savePersonAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePersonAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void savePersonAddressBook(ReadOnlyPersonAddressBook addressBook, String filePath) {
        try {
            new JsonPersonAddressBookStorage(Paths.get(filePath))
                    .savePersonAddressBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savePersonAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePersonAddressBook(new PersonAddressBook(), null));
    }
}
