package seedu.stock.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.stock.testutil.Assert.assertThrows;
import static seedu.stock.testutil.TypicalSerialNumberSets.BENGAWAN_SOLO;
import static seedu.stock.testutil.TypicalSerialNumberSets.NTUC;
import static seedu.stock.testutil.TypicalSerialNumberSets.getTypicalSerialNumberSetsBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.stock.commons.exceptions.DataConversionException;
import seedu.stock.model.ReadOnlySerialNumberSetsBook;
import seedu.stock.model.SerialNumberSetsBook;

public class JsonSerialNumberSetsBookStorageTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerialNumberSetsBookStorageTest");

    @TempDir
    public Path testFolder;

    private Optional<ReadOnlySerialNumberSetsBook> readSerialNumberSetsBook(String filePath) throws Exception {
        return new JsonSerialNumberSetsBookStorage(Paths.get(filePath))
                    .readSerialNumberSetsBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }
    @Test
    public void readSerialNumberSetsBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSerialNumberSetsBook(null));
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readSerialNumberSetsBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () ->
                readSerialNumberSetsBook("notJsonFormatSerialNumberSetsBook.json"));
    }

    @Test
    public void readSerialNumberSetsBook_invalidSerialNumberSet_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readSerialNumberSetsBook("invalidSerialNumberSet.json"));
    }

    @Test
    public void readSerialNumberSetsBook_invalidAndValidSerialNumberSet_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readSerialNumberSetsBook("invalidAndValidSerialNumberSet.json"));
    }

    @Test
    public void readAndSaveSerialNumberSetsBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempSerialNumberSetsBook.json");
        SerialNumberSetsBook original = getTypicalSerialNumberSetsBook();
        JsonSerialNumberSetsBookStorage jsonSerialNumberSetsBookStorage =
                new JsonSerialNumberSetsBookStorage(filePath);

        // Save in new file and read back
        jsonSerialNumberSetsBookStorage.saveSerialNumberSetsBook(original, filePath);
        ReadOnlySerialNumberSetsBook readBack = jsonSerialNumberSetsBookStorage
                                                        .readSerialNumberSetsBook(filePath).get();
        assertEquals(original, new SerialNumberSetsBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addSerialNumberSet(BENGAWAN_SOLO);
        original.removeSerialNumberSet(NTUC);
        jsonSerialNumberSetsBookStorage.saveSerialNumberSetsBook(original, filePath);
        readBack = jsonSerialNumberSetsBookStorage.readSerialNumberSetsBook(filePath).get();
        assertEquals(original, new SerialNumberSetsBook(readBack));

        // Save and read without specifying file path
        original.addSerialNumberSet(NTUC);
        jsonSerialNumberSetsBookStorage.saveSerialNumberSetsBook(original); // file path not specified
        readBack = jsonSerialNumberSetsBookStorage.readSerialNumberSetsBook().get(); // file path not specified
        assertEquals(original, new SerialNumberSetsBook(readBack));
    }

    @Test
    public void saveSerialNumberSetsBook_nullSerialNumberSetsBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSerialNumberSetsBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code serialNumberSetsBook} at the specified {@code filePath}.
     */
    private void saveSerialNumberSetsBook(ReadOnlySerialNumberSetsBook serialNumberSetsBook, String filePath) {
        try {
            new JsonSerialNumberSetsBookStorage(Paths.get(filePath))
                    .saveSerialNumberSetsBook(serialNumberSetsBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveSerialNumberSetsBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSerialNumberSetsBook(new SerialNumberSetsBook(), null));
    }
}
