package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.CommonCents;
import seedu.address.model.ReadOnlyCommonCents;

public class JsonCommonCentsStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonCommonCentsStorageTest");

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
        assertFalse(readCommonCents("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readCommonCents("notJsonFormatCommonCents.json"));
    }

    @Test
    public void readCommonCents_invalidAccountCommonCents_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCommonCents("invalidAccountNameCommonCents.json"));
    }

    @Test
    public void readCommonCents_invalidAndValidAccountCommonCents_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCommonCents(
            "invalidAndValidAccountCommonCents.json"));
    }

    @Test
    public void saveCommonCents_nullCommonCents_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCommonCents(null, "SomeFile.json"));
    }

    /**
     * Saves {@code commonCents} at the specified {@code filePath}.
     */
    private void saveCommonCents(ReadOnlyCommonCents commonCents, String filePath) {
        try {
            new JsonCommonCentsStorage(Paths.get(filePath))
                    .saveCommonCents(commonCents, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveCommonCents_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCommonCents(new CommonCents(), null));
    }

}
