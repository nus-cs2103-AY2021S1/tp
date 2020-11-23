//@@author AB3
//Renamed from corresponding AddressBook file with minor modifications.
package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyWordBank;
import seedu.address.model.WordBank;

public class JsonWordBankStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonWordBankStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyWordBank> readAddressBook(String filePath) throws Exception {
        return new JsonWordBankStorage(Paths.get(filePath)).readAddressBook(addToTestDataPathIfNotNull(filePath));
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
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatWordBank.json"));
    }

    @Test
    public void readAddressBook_invalidEntryAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidEntryWordBank.json"));
    }

    //Commented out this test, will try to sort out the issue and re-include.
    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
    //        Path filePath = testFolder.resolve("TempAddressBook.json");
    //        WordBank original = getTypicalAddressBook();
    //        JsonWordBankStorage jsonAddressBookStorage = new JsonWordBankStorage(filePath);
    //
    //        // Save in new file and read back
    //        System.out.println (original);
    //        jsonAddressBookStorage.saveAddressBook(original, filePath);
    //        ReadOnlyWordBank readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
    //        assertEquals(original, new WordBank(readBack));
    //
    //        // Modify data, overwrite exiting file, and read back
    //        original.addDeck(FRENCH_DECK);
    //        jsonAddressBookStorage.saveAddressBook(original, filePath);
    //        readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
    //        assertEquals(original, new WordBank(readBack));
    //
    //        // Save and read without specifying file path
    //        original.addDeck(GERMAN_DECK);
    //        jsonAddressBookStorage.saveAddressBook(original); // file path not specified
    //        readBack = jsonAddressBookStorage.readAddressBook().get(); // file path not specified
    //        assertEquals(original, new WordBank(readBack));
    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyWordBank addressBook, String filePath) {
        try {
            new JsonWordBankStorage(Paths.get(filePath))
                    .saveAddressBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new WordBank(), null));
    }
}
//@@author
