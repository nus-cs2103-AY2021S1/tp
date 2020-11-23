package seedu.tr4cker.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tr4cker.storage.JsonAdaptedDaily.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.tr4cker.testutil.Assert.assertThrows;
import static seedu.tr4cker.testutil.TypicalTasks.TODO2;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.commons.exceptions.IllegalValueException;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;

/**
 * Contains integration tests for {@code JsonAdaptedDaily}.
 */
public class JsonAdaptedDailyTest {

    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DEADLINE = "2020+";

    private static final String VALID_NAME = TODO2.getName().toString();
    private static final String VALID_DEADLINE = TODO2.getDeadline().toString();

    @Test
    public void toModelType_validDailyDetails_returnsDaily() throws Exception {
        JsonAdaptedDaily todo = new JsonAdaptedDaily(TODO2);
        assertEquals(TODO2, todo.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedDaily todo =
                new JsonAdaptedDaily(INVALID_NAME, VALID_DEADLINE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, todo::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedDaily todo = new JsonAdaptedDaily(null, VALID_DEADLINE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, todo::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedDaily todo = new JsonAdaptedDaily(
                VALID_NAME, INVALID_DEADLINE);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, todo::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedDaily todo = new JsonAdaptedDaily(VALID_NAME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, todo::toModelType);
    }
}
