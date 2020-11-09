package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItineraries.JAPAN_TRIP;
import static seedu.address.testutil.TypicalItineraries.PARIS_TRIP;
import static seedu.address.testutil.TypicalItineraries.getTypicalItineraryList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ItineraryList;
import seedu.address.model.ReadOnlyItineraryList;

public class JsonItineraryListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTrackPadStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readItineraryList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readItineraryList(null));
    }

    private java.util.Optional<ReadOnlyItineraryList> readItineraryList(String filePath) throws Exception {
        return new JsonItineraryListStorage(Paths.get(filePath))
                .readItineraryList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readItineraryList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readItineraryList("notJsonFormatTrackPad.json"));
    }

    @Test
    public void readItineraryList_invalidItineraryTrackPad_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readItineraryList("invalidItineraryTrackPad.json"));
    }

    @Test
    public void readItineraryList_invalidAndValidItineraryTrackPad_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readItineraryList("invalidAndValidItineraryTrackPad.json"));
    }

    @Test
    public void readAndSaveItineraryList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempItineraryList.json");
        ItineraryList original = getTypicalItineraryList();
        JsonItineraryListStorage jsonItineraryListStorage = new JsonItineraryListStorage(filePath);

        // Save in new file and read back
        jsonItineraryListStorage.saveItineraryList(original, filePath);
        ReadOnlyItineraryList readBack = jsonItineraryListStorage.readItineraryList(filePath).get();
        assertEquals(original, new ItineraryList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addItinerary(JAPAN_TRIP);
        original.removeItinerary(PARIS_TRIP);
        jsonItineraryListStorage.saveItineraryList(original, filePath);
        readBack = jsonItineraryListStorage.readItineraryList(filePath).get();
        assertEquals(original, new ItineraryList(readBack));

        // Save and read without specifying file path
        original.addItinerary(PARIS_TRIP);
        jsonItineraryListStorage.saveItineraryList(original); // file path not specified
        readBack = jsonItineraryListStorage.readItineraryList().get(); // file path not specified
        assertEquals(original, new ItineraryList(readBack));

    }

    @Test
    public void saveItineraryList_nullItineraryList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveItineraryList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code itineraryList} at the specified {@code filePath}.
     */
    private void saveItineraryList(ReadOnlyItineraryList itineraryList, String filePath) {
        try {
            new JsonItineraryListStorage(Paths.get(filePath))
                    .saveItineraryList(itineraryList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveItineraryList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveItineraryList(new ItineraryList(), null));
    }
}
