package seedu.homerce.storage.revenue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.revenue.TypicalRevenues.HAIR_TREATMENT;
import static seedu.homerce.testutil.revenue.TypicalRevenues.LASH_LIFT;
import static seedu.homerce.testutil.revenue.TypicalRevenues.MANICURE;
import static seedu.homerce.testutil.revenue.TypicalRevenues.getTypicalRevenueTracker;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.homerce.commons.exceptions.DataConversionException;
import seedu.homerce.model.manager.ReadOnlyRevenueTracker;
import seedu.homerce.model.manager.RevenueTracker;

public class JsonRevenueStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "Revenue", "JsonRevenueStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readRevenueTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readRevenueTracker(null));
    }

    private java.util.Optional<ReadOnlyRevenueTracker> readRevenueTracker(String filePath) throws Exception {
        return new JsonRevenueStorage(Paths.get(filePath)).readRevenueTracker(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readRevenueTracker("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readRevenueTracker("notJsonFormatRevenue.json"));
    }

    @Test
    public void readRevenueTracker_invalidRevenueTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRevenueTracker("invalidRevenueTracker.json"));
    }

    @Test
    public void readRevenueTracker_invalidAndValidRevenueTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRevenueTracker("invalidAndValidRevenueTracker.json"));
    }

    @Test
    public void readAndSaveRevenueTracker_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempRevenue.json");
        RevenueTracker original = getTypicalRevenueTracker();
        JsonRevenueStorage jsonRevenueStorage = new JsonRevenueStorage(filePath);

        // Save in new file and read back
        jsonRevenueStorage.saveRevenueTracker(original, filePath);
        ReadOnlyRevenueTracker readBack = jsonRevenueStorage.readRevenueTracker(filePath).get();
        assertEquals(original, new RevenueTracker(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addRevenue(LASH_LIFT);
        original.removeRevenue(HAIR_TREATMENT);
        jsonRevenueStorage.saveRevenueTracker(original, filePath);
        readBack = jsonRevenueStorage.readRevenueTracker(filePath).get();
        assertEquals(original, new RevenueTracker(readBack));

        // Save and read without specifying file path
        original.addRevenue(MANICURE);
        jsonRevenueStorage.saveRevenueTracker(original); // file path not specified
        readBack = jsonRevenueStorage.readRevenueTracker().get(); // file path not specified
        assertEquals(original, new RevenueTracker(readBack));
    }

    @Test
    public void saveRevenueTracker_nullRevenueTracker_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRevenueTracker(null, "SomeFile.json"));
    }

    /**
     * Saves {@code RevenueTracker} at the specified {@code filePath}.
     */
    private void saveRevenueTracker(ReadOnlyRevenueTracker revenueTracker, String filePath) {
        try {
            new JsonRevenueStorage(Paths.get(filePath))
                .saveRevenueTracker(revenueTracker, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveRevenueTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRevenueTracker(new RevenueTracker(), null));
    }
}
