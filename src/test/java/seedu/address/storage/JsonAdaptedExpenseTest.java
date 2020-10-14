package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedExpense.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.account.Name;
import seedu.address.model.account.entry.Description;
import seedu.address.model.account.entry.Expense;
import seedu.address.testutil.ExpenseBuilder;

public class JsonAdaptedExpenseTest {
    private static final String INVALID_DESCRIPTION = " ";
    private static final String INVALID_AMOUNT = "a1";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_CATEGORY = "expense";
    private static final String VALID_DESCRIPTION = "bought cooking utensils";
    private static final String VALID_AMOUNT = "1000.39";
    private static final String VALID_TAG_1 = "cooking";
    private static final String VALID_TAG_2 = "food";
    private static final List<JsonAdaptedTag> VALID_TAGS = new ExpenseBuilder().build().getTags().stream()
        .map(JsonAdaptedTag::new)
        .collect(Collectors.toList());

    private static final String WHITESPACE = " \t\r\n";


    @Test
    public void toModelType_validExpenseDetails_returnsExpense() throws Exception {
        Expense modelExpense = new ExpenseBuilder().build();
        JsonAdaptedExpense adaptedExpense = new JsonAdaptedExpense(modelExpense);
        assertEquals(modelExpense, adaptedExpense.toModelType());
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedExpense adaptedExpense = new JsonAdaptedExpense(INVALID_DESCRIPTION, VALID_AMOUNT, VALID_TAGS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, adaptedExpense::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedExpense adaptedExpense = new JsonAdaptedExpense(null, VALID_AMOUNT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, adaptedExpense::toModelType);
    }
}
