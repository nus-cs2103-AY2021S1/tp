package seedu.address.model.budget;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.expense.Amount;

class BudgetTest {

    @Test
    void topupBudget_amount_changeByEqualAmount() {
        Amount amount = new Amount("1");
        Budget budget = new Budget();
        budget.topupBudget(amount);
        assertEquals(budget.getAmount(), amount);
    }

    @Test
    void getAmount_sameAmount_equalAmount() {
        Amount defaultAmount = new Amount("0");
        Budget budget = new Budget();
        assertEquals(budget.getAmount(), defaultAmount);

        budget.topupBudget(new Amount("1"));
        assertEquals(budget.getAmount(), new Amount("1"));
    }
}
