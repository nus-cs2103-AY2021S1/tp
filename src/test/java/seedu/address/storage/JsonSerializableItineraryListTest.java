package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ItineraryList;
import seedu.address.testutil.TypicalItineraries;

public class JsonSerializableItineraryListTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTrackPadTest");
    private static final Path TYPICAL_ITINERARIES_FILE = TEST_DATA_FOLDER.resolve("typicalItinerariesTrackPad.json");
    private static final Path INVALID_ITINERARY_FILE = TEST_DATA_FOLDER.resolve("invalidItineraryTrackPad.json");
    private static final Path DUPLICATE_ITINERARY_FILE = TEST_DATA_FOLDER.resolve("duplicateItineraryTrackPad.json");

    @Test
    public void toModelType_typicalItinerariesFile_success() throws Exception {
        JsonSerializableItineraryList dataFromFile = JsonUtil.readJsonFile(TYPICAL_ITINERARIES_FILE,
                JsonSerializableItineraryList.class).get();
        ItineraryList itineraryListFromFile = dataFromFile.toModelType();
        ItineraryList typicalItinerariesItineraryList = TypicalItineraries.getTypicalItineraryList();
        assertEquals(itineraryListFromFile, typicalItinerariesItineraryList);
    }

    @Test
    public void toModelType_invalidItineraryFile_throwsIllegalValueException() throws Exception {
        JsonSerializableItineraryList dataFromFile = JsonUtil.readJsonFile(INVALID_ITINERARY_FILE,
                JsonSerializableItineraryList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateItineraries_throwsIllegalValueException() throws Exception {
        JsonSerializableItineraryList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ITINERARY_FILE,
                JsonSerializableItineraryList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableItineraryList.MESSAGE_DUPLICATE_ITINERARY,
                dataFromFile::toModelType);
    }
}
