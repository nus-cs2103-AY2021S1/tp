package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.TravelPlanner;
import seedu.address.testutil.typicals.TypicalTravelPlans;

public class JsonSerializableTravelPlannerTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableTravelPlannerTest");
    private static final Path TYPICAL_TRAVEL_PLANS_FILE = TEST_DATA_FOLDER
            .resolve("typicalTravelPlansTravelPlanner.json");
    private static final Path INVALID_TRAVEL_PLAN_FILE = TEST_DATA_FOLDER
            .resolve("invalidTravelPlanTravelPlanner.json");
    private static final Path DUPLICATE_TRAVEL_PLAN_FILE = TEST_DATA_FOLDER
            .resolve("duplicateTravelPlanTravelPlanner.json");

    @Test
    public void toModelType_typicalTravelPlansFile_success() throws Exception {
        JsonSerializableTravelPlanner dataFromFile = JsonUtil.readJsonFile(TYPICAL_TRAVEL_PLANS_FILE,
                JsonSerializableTravelPlanner.class).get();
        TravelPlanner travelPlannerFromFile = dataFromFile.toModelType();
        TravelPlanner typicalTravelPlansTravelPlanner = TypicalTravelPlans.getTypicalTravelPlanner();
        assertEquals(travelPlannerFromFile, typicalTravelPlansTravelPlanner);
    }

    @Test
    public void toModelType_invalidTravelPlanFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTravelPlanner dataFromFile = JsonUtil.readJsonFile(INVALID_TRAVEL_PLAN_FILE,
                JsonSerializableTravelPlanner.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableTravelPlanner dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TRAVEL_PLAN_FILE,
                JsonSerializableTravelPlanner.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTravelPlanner.MESSAGE_DUPLICATE_TRAVEL_PLAN,
                dataFromFile::toModelType);
    }
}
