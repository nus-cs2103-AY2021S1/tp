package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalTasks.CALL_EMBASSY;
import static seedu.address.testutil.TypicalTasks.REMIND_PATIENT;
import static seedu.address.testutil.TypicalTasks.RESTOCK_SUPPLY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.testutil.Assert;

/**
 * Test cases for JsonAdaptedTask
 */
class JsonAdaptedTaskTest {
    public static final String VALID_DESCRIPTION = REMIND_PATIENT.getDescription().value;
    public static final String INVALID_DUE_DATE = "two thirty";

    @Test
    public void toModelType_success_remindPatient() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(REMIND_PATIENT);
        task.toModelType();
        assertEquals(REMIND_PATIENT, task.toModelType());
    }

    @Test
    public void toModelType_success_restockSupply() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(RESTOCK_SUPPLY);
        assertEquals(RESTOCK_SUPPLY, task.toModelType());
    }

    @Test
    public void toModelType_success_callEmbassy() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(CALL_EMBASSY);
        assertEquals(CALL_EMBASSY, task.toModelType());
    }

    @Test
    public void toModelType_invalidDueDate_throwsIllegalValueException() throws IllegalValueException {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_DESCRIPTION, INVALID_DUE_DATE);
        String expectedMessage = JsonAdaptedTask.DATE_WRONG_FORMAT;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
