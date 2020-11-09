package seedu.address.storage.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_01;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Date;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Status;
import seedu.address.model.task.TaskName;
import seedu.address.storage.JsonAdaptedTag;
import seedu.address.storage.JsonAdaptedTask;

public class JsonAdaptedTaskTest {
    private static final String INVALID_TASK_NAME = "";
    private static final String INVALID_DATE = "2020-11-1111";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_PRIORITY = "AVERAGE";
    private static final String INVALID_STATUS = "_COMPLETED";
    //private static final String DATE_CREATED = "#friend";

    private static final String VALID_TASK_NAME = LAB_01.getName().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = LAB_01.getTags().get().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_PRIORITY = "NORMAL";
    private static final String VALID_DATE = "2020-11-11";
    private static final String VALID_STATUS = "NOT_COMPLETED";
    private static final String VALID_DATE_CREATED = "2020-11-10";
    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(LAB_01);
        assertEquals(LAB_01, task.toModelType());
    }

    @Test
    public void toModelType_invalidTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_TASK_NAME, VALID_TAGS, VALID_PRIORITY,
                        VALID_DATE, VALID_STATUS, VALID_DATE_CREATED);
        String expectedMessage = TaskName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(null, VALID_TAGS, VALID_PRIORITY,
                        VALID_DATE, VALID_STATUS, VALID_DATE_CREATED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_NAME, invalidTags, VALID_PRIORITY,
                        VALID_DATE, VALID_STATUS, VALID_DATE_CREATED);
        assertThrows(IllegalValueException.class, task::toModelType);
    }
    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_NAME, VALID_TAGS, INVALID_PRIORITY,
                        VALID_DATE, VALID_STATUS, VALID_DATE_CREATED);
        String expectedMessage = Priority.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
    /*@Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_NAME, VALID_TAGS, null,
                        VALID_DATE, VALID_STATUS, VALID_DATE_CREATED);
        String expectedMessage = Priority.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }*/
    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_NAME, VALID_TAGS, VALID_PRIORITY,
                        INVALID_DATE, VALID_STATUS, VALID_DATE_CREATED);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
    /*@Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_NAME, VALID_TAGS, VALID_PRIORITY,
                        null, VALID_STATUS, VALID_DATE_CREATED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }*/
    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_NAME, VALID_TAGS, VALID_PRIORITY,
                        VALID_DATE, INVALID_STATUS, VALID_DATE_CREATED);
        String expectedMessage = Status.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_NAME, VALID_TAGS, VALID_PRIORITY,
                        VALID_DATE, null, VALID_DATE_CREATED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidCreatedDate_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_NAME, VALID_TAGS, VALID_PRIORITY,
                        VALID_DATE, VALID_STATUS, INVALID_DATE);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
    /*@Test
    public void toModelType_nullCreatedDate_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_NAME, VALID_TAGS, VALID_PRIORITY,
                        VALID_DATE, VALID_STATUS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }*/
}
