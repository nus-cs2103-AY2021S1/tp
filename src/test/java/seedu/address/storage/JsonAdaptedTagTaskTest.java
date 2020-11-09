package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.TagTask;

public class JsonAdaptedTagTaskTest {
    private static final String VALID_TAG_TASK_DESC = "CS2103T tutorial";
    private static final TagTask VALID_TAG_TASK = new TagTask(VALID_TAG_TASK_DESC, false);

    private static final String INVALID_TAG_TASK_DESC = " ";

    @Test
    public void toModelType_validTaTaskDetails_returnsTagTask() throws Exception {
        JsonAdaptedTagTask tagTask = new JsonAdaptedTagTask(VALID_TAG_TASK);
        assertEquals(VALID_TAG_TASK, tagTask.toModelType());
    }

    @Test
    public void toModelType_invalidTaskDetails_throwsIllegalValueException() {
        JsonAdaptedTagTask tagTask = new JsonAdaptedTagTask(INVALID_TAG_TASK_DESC, false);
        String expectedMessage = TagTask.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tagTask::toModelType);
    }

    @Test
    public void toModelType_nullTaskDetails_throwsIllegalValueException() {
        JsonAdaptedTagTask tagTask = new JsonAdaptedTagTask(null, false);
        String expectedMessage = String.format(JsonAdaptedTag.MISSING_FIELD_MESSAGE_FORMAT,
                TagTask.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tagTask::toModelType);
    }
}
