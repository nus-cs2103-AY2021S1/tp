package seedu.jarvis.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.storage.JsonAdaptedTask.DATETIME_FIELD;
import static seedu.jarvis.storage.JsonAdaptedTask.DESCRIPTION_FIELD;
import static seedu.jarvis.storage.JsonAdaptedTask.ID_FIELD;
import static seedu.jarvis.storage.JsonAdaptedTask.INVALID_DATE_TIME_FORMAT;
import static seedu.jarvis.storage.JsonAdaptedTask.INVALID_TASK_TYPE;
import static seedu.jarvis.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalTasks.TEST_DEADLINE_FOUR;
import static seedu.jarvis.testutil.TypicalTasks.TEST_EVENT_FOUR;
import static seedu.jarvis.testutil.TypicalTasks.TEST_TODO_FOUR;

import org.junit.jupiter.api.Test;

import seedu.jarvis.commons.exceptions.IllegalValueException;

public class JsonAdaptedTaskTest {

    private static String invalidDate = "2019-05-32T20:20";
    private static String invalidId = "A1101";

    private static String validDate = "2020-02-02T20:20";
    private static String validDescription = "Clean the bowl";
    private static String validIdTodo = "T2000";
    private static String validIdEvent = "E777";
    private static String validIdDeadline = "D1101";

    @Test
    public void toModelType_validTodoDetails_returnsTodo() throws Exception {
        JsonAdaptedTask todo = new JsonAdaptedTask(TEST_TODO_FOUR);
        assertEquals(TEST_TODO_FOUR, todo.toModelType());

        JsonAdaptedTask event = new JsonAdaptedTask(TEST_EVENT_FOUR);
        assertEquals(TEST_EVENT_FOUR, event.toModelType());

        JsonAdaptedTask deadline = new JsonAdaptedTask(TEST_DEADLINE_FOUR);
        assertEquals(TEST_DEADLINE_FOUR, deadline.toModelType());
    }

    @Test
    public void toModelType_nullId_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, validDescription, validDate);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ID_FIELD);
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(validIdTodo, null, validDate);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DESCRIPTION_FIELD);
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(validIdTodo, validDescription, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DATETIME_FIELD);
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedTask event = new JsonAdaptedTask(validIdEvent, validDescription, invalidDate);
        assertThrows(IllegalValueException.class, INVALID_DATE_TIME_FORMAT, event::toModelType);

        JsonAdaptedTask deadline = new JsonAdaptedTask(validIdDeadline, validDescription, invalidDate);
        assertThrows(IllegalValueException.class, INVALID_DATE_TIME_FORMAT, deadline::toModelType);
    }

    @Test
    public void toModelType_invalidTaskType_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(invalidId, validDescription, validDate);
        assertThrows(IllegalValueException.class, INVALID_TASK_TYPE, task::toModelType);
    }

}
