package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.project.Deadline;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.TypicalTasks;

public class JsonAdaptedTaskTest {

    private static final String INVALID_TASK_NAME = "";
    private static final String INVALID_DEADLINE = "29022020000000";
    private static final String INVALID_PROGRESS = "101";
    private static final String INVALID_IS_DONE = "partially done";

    private static final String VALID_TASK_NAME = SampleDataUtil.getValidTask().get(0);
    private static final String VALID_DEADLINE = SampleDataUtil.getValidTask().get(2);
    private static final String VALID_TASK_DESCRIPTION = SampleDataUtil.getValidTask().get(1);
    private static final String VALID_PROGRESS = SampleDataUtil.getValidTask().get(3);
    private static final String VALID_IS_DONE = SampleDataUtil.getValidTask().get(4);
    private static final String VALID_PUBLISH_DATE = "2020-10-10";
    private static final Set<String> VALID_ASSIGNEES = SampleDataUtil.getAssigneeSet("niaz", "lucas");

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {

        JsonAdaptedTask task = new JsonAdaptedTask(TypicalTasks.TASK_A);
        assertEquals(TypicalTasks.TASK_A, task.toModelType());
    }

    @Test
    public void toModelType_invalidTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_TASK_NAME, VALID_TASK_DESCRIPTION, VALID_PUBLISH_DATE, VALID_DEADLINE,
                        VALID_PROGRESS, VALID_IS_DONE, VALID_ASSIGNEES);
        String expectedMessage = Task.NAME_MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(null, VALID_TASK_DESCRIPTION, VALID_PUBLISH_DATE, VALID_DEADLINE,
                        VALID_PROGRESS, VALID_IS_DONE, VALID_ASSIGNEES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Task name");
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_NAME, VALID_TASK_DESCRIPTION, VALID_PUBLISH_DATE, INVALID_DEADLINE,
                        VALID_PROGRESS, VALID_IS_DONE, VALID_ASSIGNEES);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidProgress_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_NAME, VALID_TASK_DESCRIPTION, VALID_PUBLISH_DATE, VALID_DEADLINE,
                        INVALID_PROGRESS, VALID_IS_DONE, VALID_ASSIGNEES);
        String expectedMessage = Task.PROGRESS_MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullProgress_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_NAME, VALID_TASK_DESCRIPTION, VALID_PUBLISH_DATE, VALID_DEADLINE,
                        null, VALID_IS_DONE, VALID_ASSIGNEES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Progress");
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidIsDone_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_NAME, VALID_TASK_DESCRIPTION, VALID_PUBLISH_DATE, VALID_DEADLINE,
                        VALID_PROGRESS, INVALID_IS_DONE, VALID_ASSIGNEES);
        String expectedMessage = Task.IS_DONE_MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullIsDone_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_NAME, VALID_TASK_DESCRIPTION, VALID_PUBLISH_DATE, VALID_DEADLINE,
                        VALID_PROGRESS, null, VALID_ASSIGNEES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Is done");
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
