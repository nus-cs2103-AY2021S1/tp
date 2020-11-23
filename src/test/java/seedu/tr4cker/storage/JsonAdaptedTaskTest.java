package seedu.tr4cker.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tr4cker.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.tr4cker.testutil.Assert.assertThrows;
import static seedu.tr4cker.testutil.TypicalTasks.TASK2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.commons.exceptions.IllegalValueException;
import seedu.tr4cker.model.task.CompletionStatus;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.TaskDescription;

public class JsonAdaptedTaskTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DEADLINE = "2020+";
    private static final String INVALID_DESCRIPTION = " ";
    private static final int INVALID_COMPLETION_STATUS = -1;
    private static final String INVALID_MODULE_CODE = "@code";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = TASK2.getName().toString();
    private static final String VALID_DEADLINE = TASK2.getDeadline().toString();
    private static final int VALID_COMPLETION_STATUS = TASK2.getCompletionStatus().value;
    private static final String VALID_DESCRIPTION = TASK2.getTaskDescription().toString();
    private static final List<JsonAdaptedModuleCode> VALID_MODULE_CODE = TASK2.getModuleCode()
            .stream().map(JsonAdaptedModuleCode::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = TASK2.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(TASK2);
        assertEquals(TASK2, task.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_NAME, VALID_DEADLINE, VALID_COMPLETION_STATUS,
                        VALID_DESCRIPTION, VALID_MODULE_CODE, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_DEADLINE, VALID_COMPLETION_STATUS,
                VALID_DESCRIPTION, VALID_MODULE_CODE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(
                VALID_NAME, INVALID_DEADLINE, VALID_COMPLETION_STATUS, VALID_DESCRIPTION,
                VALID_MODULE_CODE, VALID_TAGS);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, null, VALID_COMPLETION_STATUS,
                VALID_DESCRIPTION, VALID_MODULE_CODE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidCompletionStatus_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(
                VALID_NAME, VALID_DEADLINE, INVALID_COMPLETION_STATUS, VALID_DESCRIPTION,
                VALID_MODULE_CODE, VALID_TAGS);
        String expectedMessage = CompletionStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTaskDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(
                VALID_NAME, VALID_DEADLINE, VALID_COMPLETION_STATUS, INVALID_DESCRIPTION,
                VALID_MODULE_CODE, VALID_TAGS);
        String expectedMessage = TaskDescription.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_DEADLINE, VALID_COMPLETION_STATUS,
                null, VALID_MODULE_CODE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidModuleCode_throwsIllegalValueException() {
        List<JsonAdaptedModuleCode> invalidModuleCode = new ArrayList<>();
        invalidModuleCode.add(new JsonAdaptedModuleCode(INVALID_MODULE_CODE));
        JsonAdaptedTask task = new JsonAdaptedTask(
                VALID_NAME, VALID_DEADLINE, VALID_COMPLETION_STATUS, VALID_DESCRIPTION,
                invalidModuleCode, VALID_TAGS);
        assertThrows(IllegalValueException.class, task::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTask task = new JsonAdaptedTask(
                VALID_NAME, VALID_DEADLINE, VALID_COMPLETION_STATUS, VALID_DESCRIPTION,
                VALID_MODULE_CODE, invalidTags);
        assertThrows(IllegalValueException.class, task::toModelType);
    }

}
