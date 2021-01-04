package seedu.homerce.model.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.expense.TypicalExpenses.CHAIR;
import static seedu.homerce.testutil.expense.TypicalExpenses.CONDITIONER;

import org.junit.jupiter.api.Test;

import seedu.homerce.testutil.expense.ExpenseBuilder;

public class ExpenseTest {
    @Test
    public void equals() {
        //same values -> returns true
        Expense conditionerCopy = new ExpenseBuilder(CONDITIONER).build();
        assertTrue(CONDITIONER.equals(conditionerCopy));

        //same object -> returns true
        assertTrue(CONDITIONER.equals(CONDITIONER));

        //null -> returns false
        assertFalse(CONDITIONER.equals(null));

        //different type -> returns false
        assertFalse(CONDITIONER.equals(5));

        //different expenses -> returns false
        assertFalse(CONDITIONER.equals(CHAIR));
    }

    @Test
    public void testToString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Conditioner")
                .append("\n Is Fixed: ")
                .append("No")
                .append(" Value: ")
                .append("15.00")
                .append(" Date: ")
                .append("10-12-2020")
                .append(" Tag: ")
                .append("[hairsupplies]");
        assertEquals(builder.toString(), CONDITIONER.toString());
    }
}
