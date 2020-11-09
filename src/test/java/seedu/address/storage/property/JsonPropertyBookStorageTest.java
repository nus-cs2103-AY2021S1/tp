package seedu.address.storage.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_A;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_D;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.propertybook.PropertyBook;
import seedu.address.model.propertybook.ReadOnlyPropertyBook;

public class JsonPropertyBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonPropertyBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPropertyBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPropertyBook(null));
    }

    private java.util.Optional<ReadOnlyPropertyBook> readPropertyBook(String filePath) throws Exception {
        return new JsonPropertyBookStorage(Paths.get(filePath))
                .readPropertyBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPropertyBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () ->
                readPropertyBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPropertyBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readPropertyBook("invalidPropertyBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPropertyBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readPropertyBook("invalidAndValidPropertyBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        PropertyBook original = getTypicalPropertyBook();
        JsonPropertyBookStorage jsonPropertyBookStorage = new JsonPropertyBookStorage(filePath);

        // Save in new file and read back
        jsonPropertyBookStorage.savePropertyBook(original, filePath);
        ReadOnlyPropertyBook readBack = jsonPropertyBookStorage.readPropertyBook(filePath).get();
        assertEquals(original, new PropertyBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.removeProperty(PROPERTY_A);
        original.addProperty(PROPERTY_A);
        jsonPropertyBookStorage.savePropertyBook(original, filePath);
        readBack = jsonPropertyBookStorage.readPropertyBook(filePath).get();
        assertEquals(original, new PropertyBook(readBack));

        // Save and read without specifying file path
        original.addProperty(PROPERTY_D);
        jsonPropertyBookStorage.savePropertyBook(original); // file path not specified
        readBack = jsonPropertyBookStorage.readPropertyBook().get(); // file path not specified
        assertEquals(original, new PropertyBook(readBack));

    }

    @Test
    public void savePropertyBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                savePropertyBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code propertyAddressBook} at the specified {@code filePath}.
     */
    private void savePropertyBook(ReadOnlyPropertyBook propertyAddressBook, String filePath) {
        try {
            new JsonPropertyBookStorage(Paths.get(filePath))
                    .savePropertyBook(propertyAddressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savePropertyBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePropertyBook(new PropertyBook(), null));
    }
}
