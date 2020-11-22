package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalBudget;

public class SetBudgetCommandTest {
    @Test
    public void equals() {
        SetBudgetCommand budget500 = new SetBudgetCommand(TypicalBudget.BUDGET_500);
        SetBudgetCommand budget1000 = new SetBudgetCommand(TypicalBudget.BUDGET_1000);

        // same object -> returns true
        assertTrue(budget500.equals(budget500));

        // same values -> returns true
        SetBudgetCommand budget500Copy = new SetBudgetCommand(TypicalBudget.BUDGET_500);
        assertTrue(budget500.equals(budget500Copy));

        // different types -> returns false
        assertFalse(budget500.equals(500));

        // null -> returns false
        assertFalse(budget500.equals(null));

        // different currencies -> returns false
        assertFalse(budget500.equals(budget1000));
    }

}
