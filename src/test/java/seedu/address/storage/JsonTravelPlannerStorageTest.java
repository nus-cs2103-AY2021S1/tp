package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typicals.TypicalActivities.ARCHERY;
import static seedu.address.testutil.typicals.TypicalActivities.ZOO;
import static seedu.address.testutil.typicals.TypicalTravelPlans.AUSTRALIA_TRIP;
import static seedu.address.testutil.typicals.TypicalTravelPlans.SINGAPORE_TRIP;
import static seedu.address.testutil.typicals.TypicalTravelPlans.getTypicalTravelPlanner;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTravelPlanner;
import seedu.address.model.TravelPlanner;

public class JsonTravelPlannerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths
            .get("src", "test", "data", "JsonTravelPlannerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTravelPlanner_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTravelPlanner(null));
    }

    private Optional<ReadOnlyTravelPlanner> readTravelPlanner(String filePath) throws Exception {
        return new JsonTravelPlannerStorage(Paths.get(filePath))
                .readTravelPlanner(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTravelPlanner("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTravelPlanner("notJsonFormatTravelPlanner.json"));
    }

    @Test
    public void readTravelPlanner_invalidTravelPlanTravelPlanner_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readTravelPlanner("invalidTravelPlanTravelPlanner.json"));
    }

    @Test
    public void readTravelPlanner_invalidAndValidTravelPlanTravelPlanner_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readTravelPlanner("invalidAndValidTravelPlanTravelPlanner.json"));
    }

    @Test
    public void readAndSaveTravelPlanner_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTravelPlanner.json");
        TravelPlanner original = getTypicalTravelPlanner();
        JsonTravelPlannerStorage jsonTravelPlannerStorage = new JsonTravelPlannerStorage(filePath);

        // Save in new file and read back
        jsonTravelPlannerStorage.saveTravelPlanner(original, filePath);
        ReadOnlyTravelPlanner readBack = jsonTravelPlannerStorage.readTravelPlanner(filePath).get();
        assertEquals(original, new TravelPlanner(readBack));

        // Modify data, overwrite existing file, and read back
        original.addTravelPlan(SINGAPORE_TRIP);
        original.removeTravelPlan(AUSTRALIA_TRIP);
        jsonTravelPlannerStorage.saveTravelPlanner(original, filePath);
        readBack = jsonTravelPlannerStorage.readTravelPlanner(filePath).get();
        assertEquals(original, new TravelPlanner(readBack));

        original.addActivity(ZOO);
        original.removeActivity(ARCHERY);
        jsonTravelPlannerStorage.saveTravelPlanner(original, filePath);
        readBack = jsonTravelPlannerStorage.readTravelPlanner(filePath).get();
        assertEquals(original, new TravelPlanner(readBack));

        // Save and read without specifying file path
        original.addTravelPlan(AUSTRALIA_TRIP);
        jsonTravelPlannerStorage.saveTravelPlanner(original); // file path not specified
        readBack = jsonTravelPlannerStorage.readTravelPlanner().get(); // file path not specified
        assertEquals(original, new TravelPlanner(readBack));

    }

    @Test
    public void saveTravelPlanner_nullTravelPlanner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTravelPlanner(null, "SomeFile.json"));
    }

    /**
     * Saves {@code travelPlanner} at the specified {@code filePath}.
     */
    private void saveTravelPlanner(ReadOnlyTravelPlanner travelPlanner, String filePath) {
        try {
            new JsonTravelPlannerStorage(Paths.get(filePath))
                    .saveTravelPlanner(travelPlanner, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTravelPlanner_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTravelPlanner(new TravelPlanner(), null));
    }
}
