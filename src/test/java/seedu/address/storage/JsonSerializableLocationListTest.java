package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.LocationList;
import seedu.address.testutil.TypicalLocations;

public class JsonSerializableLocationListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src",
            "test", "data", "JsonSerializableLocationListTest");
    private static final Path TYPICAL_LOCATIONS_FILE = TEST_DATA_FOLDER.resolve("typicalLocationsLocationList.json");
    private static final Path INVALID_LOCATION_FILE = TEST_DATA_FOLDER.resolve("invalidLocationLocationList.json");
    private static final Path DUPLICATE_LOCATION_FILE = TEST_DATA_FOLDER.resolve("duplicateLocationLocationList.json");

    @Test
    public void toModelType_typicalLocationsFile_success() throws Exception {
        JsonSerializableLocationList dataFromFile = JsonUtil.readJsonFile(TYPICAL_LOCATIONS_FILE,
                JsonSerializableLocationList.class).get();
        LocationList locationListFromFile = dataFromFile.toModelType();
        LocationList typicalLocationsLocationList = TypicalLocations.getTypicalLocationsList();

        assertEquals(locationListFromFile, typicalLocationsLocationList);
    }

    @Test
    public void toModelType_invalidLocationFile_throwsNullPointerException() throws Exception {
        JsonSerializableLocationList dataFromFile = JsonUtil.readJsonFile(INVALID_LOCATION_FILE,
                JsonSerializableLocationList.class).get();
        assertThrows(NullPointerException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateLocations_throwsIllegalValueException() throws Exception {
        JsonSerializableLocationList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_LOCATION_FILE,
                JsonSerializableLocationList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableLocationList.MESSAGE_DUPLICATE_LOCATION,
                dataFromFile::toModelType);
    }
}
