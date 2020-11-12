package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Planus;
import seedu.address.testutil.TypicalPlanus;

public class JsonSerializablePlanusTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializablePlanusTest");
    private static final Path TYPICAL_TASKS_FILE = TEST_DATA_FOLDER.resolve("typicalTaskPlanus.json");
    private static final Path INVALID_TASKS_FILE = TEST_DATA_FOLDER.resolve("invalidTaskPlanus.json");
    private static final Path DUPLICATE_TASK_FILE = TEST_DATA_FOLDER.resolve("duplicateTaskPlanus.json");

    @Test
    public void toModelType_typicalTaskFile_success() throws Exception {
        JsonSerializablePlanus dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASKS_FILE,
                JsonSerializablePlanus.class).get();
        Planus planusFromFile = dataFromFile.toModelType();
        Planus typicalTasksPlanus = TypicalPlanus.getTypicalTaskOnlyPlanus();
        assertEquals(planusFromFile, typicalTasksPlanus);
    }

    @Test
    public void toModelType_invalidTaskFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePlanus dataFromFile = JsonUtil.readJsonFile(INVALID_TASKS_FILE,
                JsonSerializablePlanus.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTask_throwsIllegalValueException() throws Exception {
        JsonSerializablePlanus dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TASK_FILE,
                JsonSerializablePlanus.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePlanus.MESSAGE_DUPLICATE_TASK,
                dataFromFile::toModelType);
    }

}
