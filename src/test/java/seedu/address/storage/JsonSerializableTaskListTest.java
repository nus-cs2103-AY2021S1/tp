package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.TaskList;
import seedu.address.testutil.TypicalEvents;
import seedu.address.testutil.TypicalTodos;

public class JsonSerializableTaskListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTaskListTest");
    private static final Path TYPICAL_TODOS_FILE = TEST_DATA_FOLDER.resolve("typicalTodosTaskList.json");
    private static final Path TYPICAL_EVENTS_FILE = TEST_DATA_FOLDER.resolve("typicalEventsTaskList.json");
    private static final Path INVALID_TODOS_FILE = TEST_DATA_FOLDER.resolve("invalidTodoTaskList.json");
    private static final Path INVALID_EVENTS_FILE = TEST_DATA_FOLDER.resolve("invalidEventTaskList.json");
    private static final Path DUPLICATE_TODOS_FILE = TEST_DATA_FOLDER.resolve("duplicateTodoTaskList.json");
    private static final Path DUPLICATE_EVENTS_FILE = TEST_DATA_FOLDER.resolve("duplicateEventTaskList.json");

    @Test
    public void toModelType_typicalTodosFile_success() throws Exception {
        JsonSerializableTaskList dataFromFile = JsonUtil.readJsonFile(TYPICAL_TODOS_FILE,
                JsonSerializableTaskList.class).get();
        TaskList taskListFromFile = dataFromFile.toModelType();
        TaskList typicalTasksTaskList = TypicalTodos.getTypicalTodosTaskList();
        assertEquals(taskListFromFile, typicalTasksTaskList);
    }

    @Test
    public void toModelType_invalidTodoFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskList dataFromFile = JsonUtil.readJsonFile(INVALID_TODOS_FILE,
                JsonSerializableTaskList.class).get();
        assertThrows(IllegalArgumentException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTodos_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TODOS_FILE,
                JsonSerializableTaskList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTaskList.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }
    @Test
    public void toModelType_typicalEventsFile_success() throws Exception {
        JsonSerializableTaskList dataFromFile = JsonUtil.readJsonFile(TYPICAL_EVENTS_FILE,
                JsonSerializableTaskList.class).get();
        TaskList taskListFromFile = dataFromFile.toModelType();
        TaskList typicalTasksTaskList = TypicalEvents.getTypicalEventsTaskList();
        assertEquals(taskListFromFile, typicalTasksTaskList);
    }
    @Test
    public void toModelType_invalidEventFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskList dataFromFile = JsonUtil.readJsonFile(INVALID_EVENTS_FILE,
                JsonSerializableTaskList.class).get();
        assertThrows(IllegalArgumentException.class, dataFromFile::toModelType);
    }
    @Test
    public void toModelType_duplicateEvents_throwsIllegalValueException() throws Exception {
        JsonSerializableTaskList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EVENTS_FILE,
                JsonSerializableTaskList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTaskList.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }
}
