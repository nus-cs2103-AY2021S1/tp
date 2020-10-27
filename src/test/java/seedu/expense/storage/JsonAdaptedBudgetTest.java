package seedu.expense.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.expense.model.budget.CategoryBudget;
import seedu.expense.model.expense.Amount;
import seedu.expense.model.tag.Tag;

class JsonAdaptedBudgetTest {

    @Test
    void toModelType_validAmount_returnsBudgetWithCorrectAmount() throws Exception {
        CategoryBudget expectedBudget = new CategoryBudget(new Tag("Category"));
        expectedBudget.topupBudget(new Amount("1.01"));
        JsonAdaptedBudget budget = new JsonAdaptedBudget(expectedBudget);
        assertEquals(expectedBudget.toString(), budget.toModelType().toString());
    }
}
