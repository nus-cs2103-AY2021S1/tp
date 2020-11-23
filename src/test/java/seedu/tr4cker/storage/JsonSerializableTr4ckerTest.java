package seedu.tr4cker.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tr4cker.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.commons.exceptions.IllegalValueException;
import seedu.tr4cker.commons.util.JsonUtil;
import seedu.tr4cker.model.Tr4cker;
import seedu.tr4cker.testutil.TypicalTasks;

public class JsonSerializableTr4ckerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTr4ckerTest");
    private static final Path TYPICAL_TASKS_FILE = TEST_DATA_FOLDER.resolve("typicalTasksTr4cker.json");
    private static final Path INVALID_TASK_FILE = TEST_DATA_FOLDER.resolve("invalidTaskTr4cker.json");
    private static final Path DUPLICATE_TASK_FILE = TEST_DATA_FOLDER.resolve("duplicateTaskTr4cker.json");

    @Test
    public void toModelType_typicalTasksFile_success() throws Exception {
        JsonSerializableTr4cker dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASKS_FILE,
                JsonSerializableTr4cker.class).get();
        Tr4cker tr4ckerFromFile = dataFromFile.toModelType();
        Tr4cker typicalTasksTr4cker = TypicalTasks.getTypicalTr4cker();
        assertEquals(tr4ckerFromFile, typicalTasksTr4cker);
    }

    @Test
    public void toModelType_invalidTaskFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTr4cker dataFromFile = JsonUtil.readJsonFile(INVALID_TASK_FILE,
                JsonSerializableTr4cker.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTasks_throwsIllegalValueException() throws Exception {
        JsonSerializableTr4cker dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TASK_FILE,
                JsonSerializableTr4cker.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTr4cker.MESSAGE_DUPLICATE_TASK,
                dataFromFile::toModelType);
    }

}
