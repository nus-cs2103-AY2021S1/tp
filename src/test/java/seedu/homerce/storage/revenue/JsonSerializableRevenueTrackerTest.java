package seedu.homerce.storage.revenue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.homerce.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.exceptions.IllegalValueException;
import seedu.homerce.commons.util.JsonUtil;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.testutil.revenue.TypicalRevenues;

public class JsonSerializableRevenueTrackerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "Revenue",
        "JsonSerializableRevenueTrackerTest");
    private static final Path TYPICAL_REVENUES_FILE = TEST_DATA_FOLDER.resolve("typicalRevenueTracker.json");
    private static final Path INVALID_REVENUES_FILE = TEST_DATA_FOLDER.resolve("invalidRevenueTracker.json");

    @Test
    public void toModelType_typicalRevenuesFile_success() throws Exception {
        JsonSerializableRevenueTracker dataFromFile = JsonUtil.readJsonFile(TYPICAL_REVENUES_FILE,
            JsonSerializableRevenueTracker.class).get();
        RevenueTracker revenueTrackerFromFile = dataFromFile.toModelType();
        RevenueTracker typicalRevenuesRevenueTracker = TypicalRevenues.getTypicalRevenueTracker();
        assertEquals(revenueTrackerFromFile, typicalRevenuesRevenueTracker);
    }

    @Test
    public void toModelType_invalidRevenuesFile_throwsIllegalValueException() throws Exception {
        JsonSerializableRevenueTracker dataFromFile = JsonUtil.readJsonFile(INVALID_REVENUES_FILE,
            JsonSerializableRevenueTracker.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }
}
