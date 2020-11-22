package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedExpense.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExpenses.CLOTHES;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Category;
import seedu.address.model.expense.Description;

public class JsonAdaptedExpenseTest {
    private static final String INVALID_AMOUNT = "-10.0";
    private static final String INVALID_CATEGORY = "FOOD-BEVERAGE";
    private static final String INVALID_DATE_DECIMAL = "1.5";
    private static final String INVALID_DATE_NEGATIVE = "1.5";
    private static final String INVALID_DATE = "2020-09-32";
    private static final String INVALID_DESCRIPTION = " ";
    private static final String VALID_AMOUNT = String.format("%.2f", CLOTHES.getAmount().value);
    private static final String VALID_CURRENCY = CLOTHES.getCurrency().toString();
    private static final String VALID_CATEGORY = CLOTHES.getCategory().toString();
    private static final String VALID_DATE = CLOTHES.getDate().toString();
    private static final String VALID_DESCRIPTION = CLOTHES.getDescription().toString();

    @Test
    public void toModelType_validExpenseDetails_returnsExpense() throws Exception {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(CLOTHES);
        assertEquals(CLOTHES, expense.toModelType());
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(INVALID_AMOUNT, VALID_CURRENCY, VALID_DATE, VALID_CATEGORY, VALID_DESCRIPTION);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_nullAmount_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(null, VALID_CURRENCY, VALID_DATE, VALID_CATEGORY, VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_AMOUNT, VALID_CURRENCY, INVALID_DATE, VALID_CATEGORY, VALID_DESCRIPTION);
        assertThrows(IllegalArgumentException.class, expense::toModelType);
    }

    @Test
    public void toModelType_invalidDateDecimal_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_AMOUNT, VALID_CURRENCY, INVALID_DATE_DECIMAL,
                        VALID_CATEGORY, VALID_DESCRIPTION);
        assertThrows(IllegalArgumentException.class, expense::toModelType);
    }

    @Test
    public void toModelType_invalidDateNegative_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_AMOUNT, VALID_CURRENCY, INVALID_DATE_NEGATIVE,
                        VALID_CATEGORY, VALID_DESCRIPTION);
        assertThrows(IllegalArgumentException.class, expense::toModelType);
    }

    @Test
    public void toModelType_nullCategory_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_AMOUNT, VALID_CURRENCY, VALID_DATE, null, VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Category.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_invalidCategory_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_AMOUNT, VALID_CURRENCY, VALID_DATE, INVALID_CATEGORY, VALID_DESCRIPTION);
        String expectedMessage = Category.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, expense::toModelType);
    }



    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_AMOUNT, VALID_CURRENCY, VALID_DATE, VALID_CATEGORY, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_AMOUNT, VALID_CURRENCY, VALID_DATE, VALID_CATEGORY, INVALID_DESCRIPTION);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, expense::toModelType);
    }

}
