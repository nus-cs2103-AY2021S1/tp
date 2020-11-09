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
    public void readLogBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readLogBook(null));
    }

    private java.util.Optional<ReadOnlyLogBook> readLogBook(String filePath) throws Exception {
        return new JsonLogBookStorage(Paths.get(filePath)).readLogBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readLogBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readLogBook("notJsonFormatLogBook.json"));
    }

    @Test
    public void readLogBook_invalidLogBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readLogBook("invalidEntryLogBook.json"));
    }

    @Test
    public void readLogBook_invalidAndValidEntryLogBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readLogBook("invalidAndValidEntryLogBook.json"));
    }

    @Test
    public void readAndSaveLogBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempLogBook.json");
        LogBook original = getTypicalLogBook();
        JsonLogBookStorage jsonLogBookStorage = new JsonLogBookStorage(filePath);

        // Save in new file and read back
        jsonLogBookStorage.saveLogBook(original, filePath);
        ReadOnlyLogBook readBack = jsonLogBookStorage.readLogBook(filePath).get();
        assertEquals(original, new LogBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addLog(LOG_E);
        original.removeLog(LOG_A);
        jsonLogBookStorage.saveLogBook(original, filePath);
        readBack = jsonLogBookStorage.readLogBook(filePath).get();
        assertEquals(original, new LogBook(readBack));

        // Save and read without specifying file path
        original.addLog(LOG_F);
        jsonLogBookStorage.saveLogBook(original); // file path not specified
        readBack = jsonLogBookStorage.readLogBook().get(); // file path not specified
        assertEquals(original, new LogBook(readBack));

    }

    @Test
    public void saveLogBook_nullLogBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveLogBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code logBook} at the specified {@code filePath}.
     */
    private void saveLogBook(ReadOnlyLogBook logBook, String filePath) {
        try {
            new JsonLogBookStorage(Paths.get(filePath))
                    .saveLogBook(logBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveLogBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveLogBook(new LogBook(), null));
    }
}
