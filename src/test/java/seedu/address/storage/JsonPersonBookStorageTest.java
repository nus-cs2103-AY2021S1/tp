package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.person.PersonBook;
import seedu.address.model.person.ReadOnlyPersonBook;

public class JsonPersonBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPersonBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyPersonBook> readAddressBook(String filePath) throws Exception {
        return new JsonPersonBookStorage(Paths.get(filePath)).readAddressBook(addToTestDataPathIfNotNull(filePath));
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
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatPersonBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidPersonPersonBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidPersonPersonBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        PersonBook original = getTypicalAddressBook();
        JsonPersonBookStorage jsonPersonBookStorage = new JsonPersonBookStorage(filePath);

        // Save in new file and read back
        jsonPersonBookStorage.savePersonBook(original, filePath);
        ReadOnlyPersonBook readBack = jsonPersonBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new PersonBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonPersonBookStorage.savePersonBook(original, filePath);
        readBack = jsonPersonBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new PersonBook(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonPersonBookStorage.savePersonBook(original); // file path not specified
        readBack = jsonPersonBookStorage.readAddressBook().get(); // file path not specified
        assertEquals(original, new PersonBook(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePersonBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code personBook} at the specified {@code filePath}.
     */
    private void savePersonBook(ReadOnlyPersonBook personBook, String filePath) {
        try {
            new JsonPersonBookStorage(Paths.get(filePath))
                    .savePersonBook(personBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePersonBook(new PersonBook(), null));
    }
}
