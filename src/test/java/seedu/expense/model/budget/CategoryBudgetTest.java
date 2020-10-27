package seedu.expense.model.budget;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.expense.model.expense.Amount;
import seedu.expense.model.tag.Tag;

class CategoryBudgetTest {

    @Test
    void topupBudget_amount_changeByEqualAmount() {
        Amount amount = new Amount("1");
        CategoryBudget budget = new CategoryBudget(new Tag("Category"));
        budget.topupBudget(amount);
        assertEquals(budget.getAmount(), amount);
    }

    @Test
    void getAmount_sameAmount_equalAmount() {
        Amount defaultAmount = new Amount("0");
        CategoryBudget budget = new CategoryBudget(new Tag("Category"));
        assertEquals(budget.getAmount(), defaultAmount);

        budget.topupBudget(new Amount("1"));
        assertEquals(budget.getAmount(), new Amount("1"));
    }
}
