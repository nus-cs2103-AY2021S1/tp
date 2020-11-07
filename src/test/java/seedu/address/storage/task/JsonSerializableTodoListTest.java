package seedu.address.storage.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.TodoList;
import seedu.address.storage.JsonSerializableTodoList;
import seedu.address.testutil.todolist.TypicalTasks;

public class JsonSerializableTodoListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableTodoListTest");
    private static final Path TYPICAL_TASKS_FILE = TEST_DATA_FOLDER.resolve("typicalTaskTodoList.json");
    private static final Path INVALID_TASKS_FILE = TEST_DATA_FOLDER.resolve("invalidTaskTodoList.json");
    private static final Path DUPLICATE_TASKS_FILE = TEST_DATA_FOLDER.resolve("duplicateTaskTodoList.json");

    @Test
    public void toModelType_typicalTasksFile_success() throws Exception {
        JsonSerializableTodoList dataFromFile = JsonUtil.readJsonFile(TYPICAL_TASKS_FILE,
                JsonSerializableTodoList.class).get();
        TodoList todoListFromFile = dataFromFile.toModelType();
        TodoList typicalTaskTodoList = TypicalTasks.getTypicalTodoList();
        assertEquals(todoListFromFile, typicalTaskTodoList);
    }

    @Test
    public void toModelType_invalidTaskFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTodoList dataFromFile = JsonUtil.readJsonFile(INVALID_TASKS_FILE,
                JsonSerializableTodoList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateContact_throwsIllegalValueException() throws Exception {
        JsonSerializableTodoList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TASKS_FILE,
                JsonSerializableTodoList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTodoList.MESSAGE_DUPLICATE_TASK,
                 dataFromFile::toModelType);
    }
}
