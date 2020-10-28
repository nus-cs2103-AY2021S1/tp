package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedDeadline.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.DEADLINE1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;
import seedu.address.model.task.deadline.Deadline;

public class JsonAdaptedDeadlineTest {
    private static final String INVALID_TITLE = "R@te movie";
    private static final String INVALID_DATE_TIME = "1-1-2020 12:00";
    private static final String INVALID_DESCRIPTION = "@do homework";
    private static final String INVALID_TAG = "#CS";

    private static final String VALID_TITLE = DEADLINE1.getTitle().toString();
    private static final String VALID_DATE_TIME = DEADLINE1.getDeadlineDateTime().toString();
    private static final String VALID_DESCRIPTION = DEADLINE1.getDescription().toString();
    private static final String VALID_TAG = DEADLINE1.getTag().toString();
    private static final String VALID_STATUS = ((Deadline) DEADLINE1).getStatus().toString();

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedDeadline deadline = new JsonAdaptedDeadline(DEADLINE1);
        assertEquals(DEADLINE1, deadline.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedDeadline deadline =
                new JsonAdaptedDeadline(INVALID_TITLE, VALID_DATE_TIME, VALID_DESCRIPTION, VALID_DATE_TIME, 1,
                        VALID_TAG, VALID_STATUS);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, deadline::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedDeadline task =
                new JsonAdaptedDeadline(null, VALID_DATE_TIME, VALID_DESCRIPTION, VALID_DATE_TIME, 1,
                        VALID_TAG, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedDeadline task =
                new JsonAdaptedDeadline(VALID_TITLE, INVALID_DATE_TIME, VALID_DESCRIPTION, VALID_DATE_TIME, 1,
                        VALID_TAG, VALID_STATUS);
        String expectedMessage = DateUtil.DATE_TIME_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedDeadline task =
                new JsonAdaptedDeadline(VALID_TITLE, VALID_DATE_TIME, INVALID_DESCRIPTION,
                        VALID_DATE_TIME, 1, VALID_TAG, VALID_STATUS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedDeadline task =
                new JsonAdaptedDeadline(VALID_TITLE, VALID_DATE_TIME, null, VALID_DATE_TIME, 1,
                        VALID_TAG, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTag_throwsIllegalValueException() {
        JsonAdaptedDeadline task =
                new JsonAdaptedDeadline(VALID_TITLE, VALID_DATE_TIME, null, VALID_DATE_TIME, 1,
                        INVALID_TAG, VALID_STATUS);
        assertThrows(IllegalValueException.class, task::toModelType);
    }

}
