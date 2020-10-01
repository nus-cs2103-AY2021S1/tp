package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLocations.getTypicalLocationsList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.LocationList;
import seedu.address.model.ReadOnlyLocationList;

public class JsonLocationListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonLocationListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readLocationList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readLocationList(null));
    }

    private java.util.Optional<ReadOnlyLocationList> readLocationList(String filePath) throws Exception {
        return new JsonLocationListStorage(Paths.get(filePath)).readLocationList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readLocationList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readLocationList("notJsonFormatLocationList.json"));
    }

    @Test
    public void readItemList_invalidLocationLocationList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readLocationList("invalidLocationLocationList.json"));
    }

    @Test
    public void readItemList_invalidAndValidLocationLocationList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readLocationList("invalidAndValidLocationLocationList.json"));
    }

    /**
     * Tests reading and saving.
     */
    @Test
    public void readAndSaveLocationList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempLocationList.json");
        LocationList original = getTypicalLocationsList();
        JsonLocationListStorage jsonLocationListStorage = new JsonLocationListStorage(filePath);

        // Save in new file and read back
        jsonLocationListStorage.saveLocationList(original, filePath);
        ReadOnlyLocationList readBack = jsonLocationListStorage.readLocationList(filePath).get();
        assertEquals(original, new LocationList(readBack));
    }

    @Test
    public void saveLocationList_nullLocationList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveLocationList(null, "SomeFile.json"));
    }

    /**
     * Tests saving {@code locationList} at the specified {@code filePath}.
     */
    private void saveLocationList(ReadOnlyLocationList locationList, String filePath) {
        try {
            new JsonLocationListStorage(Paths.get(filePath))
                    .saveLocationList(locationList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveLocationList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveLocationList(new LocationList(), null));
    }
}
