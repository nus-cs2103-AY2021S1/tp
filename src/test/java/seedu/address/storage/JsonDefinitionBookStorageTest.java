package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFlashcards.BUBBLE_SORT;
import static seedu.address.testutil.TypicalFlashcards.HOON;
import static seedu.address.testutil.TypicalFlashcards.IDA;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashcardBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.FlashcardBook;
import seedu.address.model.ReadOnlyFlashcardBook;

public class JsonDefinitionBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyFlashcardBook> readAddressBook(String filePath) throws Exception {
        return new JsonFlashcardBookStorage(Paths.get(filePath))
            .readFlashcardBook(addToTestDataPathIfNotNull(filePath));
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
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidPersonAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        FlashcardBook original = getTypicalFlashcardBook();
        JsonFlashcardBookStorage jsonAddressBookStorage = new JsonFlashcardBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveFlashcardBook(original, filePath);
        ReadOnlyFlashcardBook readBack = jsonAddressBookStorage.readFlashcardBook(filePath).get();
        assertEquals(original, new FlashcardBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addFlashcard(HOON);
        original.removeFlashcard(BUBBLE_SORT);
        jsonAddressBookStorage.saveFlashcardBook(original, filePath);
        readBack = jsonAddressBookStorage.readFlashcardBook(filePath).get();
        assertEquals(original, new FlashcardBook(readBack));

        // Save and read without specifying file path
        original.addFlashcard(IDA);
        jsonAddressBookStorage.saveFlashcardBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readFlashcardBook().get(); // file path not specified
        assertEquals(original, new FlashcardBook(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyFlashcardBook addressBook, String filePath) {
        try {
            new JsonFlashcardBookStorage(Paths.get(filePath))
                    .saveFlashcardBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new FlashcardBook(), null));
    }
}
