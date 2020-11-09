package seedu.cc.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.cc.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.cc.commons.exceptions.DataConversionException;
import seedu.cc.model.CommonCents;
import seedu.cc.model.ReadOnlyCommonCents;

public class JsonCommonCentsStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonCommonCentsStorageTest");
    private static final String NON_EXISTENT_FILE_STRING = "NonExistentFile.json";
    private static final String NOT_JSON_FORMAT_FILE_STRING = "notJsonFormatCommonCents.json";
    private static final String INVALID_ACCOUNT_NAME_STRING = "invalidAccountNameCommonCents.json";
    private static final String INVALID_AND_VALID_ACCOUNT_NAME_STRING = "invalidAndValidAccountCommonCents.json";
    private static final String SOME_FILE_STRING = "SomeFile.json";
    private static final String ERROR_MESSAGE = "There should not be an error writing to the file.";

    @TempDir
    public Path testFolder;

    @Test
    public void readCommonCents_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readCommonCents(null));
    }

    private Optional<ReadOnlyCommonCents> readCommonCents(String filePath) throws Exception {
        return new JsonCommonCentsStorage(Paths.get(filePath)).readCommonCents(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readCommonCents(NON_EXISTENT_FILE_STRING).isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readCommonCents(NOT_JSON_FORMAT_FILE_STRING));
    }

    @Test
    public void readCommonCents_invalidAccountCommonCents_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCommonCents(INVALID_ACCOUNT_NAME_STRING));
    }

    @Test
    public void readCommonCents_invalidAndValidAccountCommonCents_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCommonCents(
            INVALID_AND_VALID_ACCOUNT_NAME_STRING));
    }

    @Test
    public void saveCommonCents_nullCommonCents_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCommonCents(null, SOME_FILE_STRING));
    }

    /**
     * Saves {@code commonCents} at the specified {@code filePath}.
     */
    private void saveCommonCents(ReadOnlyCommonCents commonCents, String filePath) {
        try {
            new JsonCommonCentsStorage(Paths.get(filePath))
                    .saveCommonCents(commonCents, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError(ERROR_MESSAGE, ioe);
        }
    }

    @Test
    public void saveCommonCents_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCommonCents(new CommonCents(), null));
    }

}
