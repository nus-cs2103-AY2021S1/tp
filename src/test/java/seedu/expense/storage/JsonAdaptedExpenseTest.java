package seedu.expense.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.expense.storage.JsonAdaptedExpense.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.expense.testutil.Assert.assertThrows;
import static seedu.expense.testutil.TypicalExpenses.GRAB_HOME;

import org.junit.jupiter.api.Test;

import seedu.expense.commons.exceptions.IllegalValueException;
import seedu.expense.model.expense.Amount;
import seedu.expense.model.expense.Date;
import seedu.expense.model.expense.Description;

public class JsonAdaptedExpenseTest {
    private static final String INVALID_DESCRIPTION = " ";
    private static final String INVALID_AMOUNT = "+3a";
    private static final String INVALID_DATE = "23 June 2020";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_DESCRIPTION = GRAB_HOME.getDescription().toString();
    private static final String VALID_AMOUNT = GRAB_HOME.getAmount().toString();
    private static final String VALID_DATE = GRAB_HOME.getDate().toString();
    private static final String VALID_REMARK = GRAB_HOME.getRemark().toString();
    private static final JsonAdaptedTag VALID_TAG = new JsonAdaptedTag(GRAB_HOME.getTag());

    @Test
    public void toModelType_validExpenseDetails_returnsExpense() throws Exception {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(GRAB_HOME);
        assertEquals(GRAB_HOME, expense.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(INVALID_DESCRIPTION, VALID_AMOUNT, VALID_DATE, VALID_REMARK,
                        VALID_TAG);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(null, VALID_AMOUNT, VALID_DATE,
                        VALID_REMARK, VALID_TAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_DESCRIPTION, INVALID_AMOUNT, VALID_DATE, VALID_REMARK,
                        VALID_TAG);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_nullAmount_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_DESCRIPTION, null, VALID_DATE,
                        VALID_REMARK, VALID_TAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_DESCRIPTION, VALID_AMOUNT, INVALID_DATE, VALID_REMARK,
                        VALID_TAG);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_DESCRIPTION, VALID_AMOUNT, null,
                        VALID_REMARK, VALID_TAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_invalidTag_throwsIllegalValueException() {
        JsonAdaptedTag invalidTag = new JsonAdaptedTag(INVALID_TAG);
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_DESCRIPTION, VALID_AMOUNT, VALID_DATE, VALID_REMARK,
                        invalidTag);
        assertThrows(IllegalValueException.class, expense::toModelType);
    }

}
