package seedu.homerce.storage.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.homerce.storage.expense.JsonAdaptedExpense.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.expense.TypicalExpenses.CONDITIONER;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.exceptions.IllegalValueException;
import seedu.homerce.model.expense.IsFixed;
import seedu.homerce.model.util.attributes.Amount;
import seedu.homerce.model.util.attributes.Date;
import seedu.homerce.model.util.attributes.Tag;
import seedu.homerce.storage.JsonAdaptedTag;

public class JsonAdaptedExpenseTest {
    private static final String INVALID_ISFIXED = "a";
    private static final Double INVALID_AMOUNT = -100.00;
    private static final String INVALID_DATE = "10-30-2020";
    private static final JsonAdaptedTag INVALID_TAG = new JsonAdaptedTag("*/");

    private static final String VALID_DESC = CONDITIONER.getDescription().value;
    private static final String VALID_ISFIXED = CONDITIONER.getIsFixed().value ? "y" : "n";
    private static final Double VALID_AMOUNT = CONDITIONER.getValue().value.doubleValue();
    private static final String VALID_DATE = CONDITIONER.getDate().toString();
    private static final JsonAdaptedTag VALID_TAG = new JsonAdaptedTag(CONDITIONER.getTag().tagName);

    @Test
    public void toModelType_validExpenseDetails_returnsExpense() throws Exception {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(CONDITIONER);
        assertEquals(CONDITIONER, expense.toModelType());
    }

    @Test
    public void toModelType_invalidIsFixed_throwsIllegalValueException() throws IllegalValueException {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_AMOUNT, VALID_DATE, VALID_DESC, INVALID_ISFIXED, VALID_TAG, false);
        String expectedMessage = IsFixed.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_nullIsFixed_throwsIllegalValueException() throws IllegalValueException {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_AMOUNT, VALID_DATE, VALID_DESC, null, VALID_TAG, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, IsFixed.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() throws IllegalValueException {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_AMOUNT, INVALID_DATE, VALID_DESC, VALID_ISFIXED, VALID_TAG, false);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() throws IllegalValueException {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_AMOUNT, null, VALID_DESC, VALID_ISFIXED, VALID_TAG, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() throws IllegalValueException {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(INVALID_AMOUNT, VALID_DATE, VALID_DESC, VALID_ISFIXED, VALID_TAG, false);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_nullAmount_throwsIllegalValueException() {
        assertThrows(IllegalValueException.class, (
                )-> new JsonAdaptedExpense(null, VALID_DATE, VALID_DESC, VALID_ISFIXED, VALID_TAG, false));
    }

    @Test
    public void toModelType_invalidTag_throwsIllegalValueException() throws IllegalValueException {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_AMOUNT, VALID_DATE, VALID_DESC, VALID_ISFIXED, INVALID_TAG, false);
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_nullTag_throwsIllegalValueException() throws IllegalValueException {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_AMOUNT, VALID_DATE, VALID_DESC, VALID_ISFIXED, null, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Tag.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }
}

