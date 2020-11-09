package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Library;
import seedu.address.model.ReadOnlyLibrary;

public class JsonLibraryStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonLibraryStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readLibrary_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readLibrary(null));
    }

    private java.util.Optional<ReadOnlyLibrary> readLibrary(String filePath) throws Exception {
        return new JsonLibraryStorage(Paths.get(filePath)).readLibrary(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readLibrary("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readLibrary("notJsonFormatLibrary.json"));
    }

    @Test
    public void readLibrary_invalidBookLibrary_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readLibrary("invalidBookLibrary.json"));
    }

    @Test
    public void readLibrary_invalidAndValidBookLibrary_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readLibrary("invalidAndValidBookLibrary.json"));
    }

    @Test
    public void saveLibrary_nullLibrary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveLibrary(null, "SomeFile.json"));
    }

    /**
     * Saves {@code library} at the specified {@code filePath}.
     */
    private void saveLibrary(ReadOnlyLibrary library, String filePath) {
        try {
            new JsonLibraryStorage(Paths.get(filePath))
                    .saveLibrary(library, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveLibrary_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveLibrary(new Library(), null));
    }
}
