package seedu.homerce.model.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.homerce.testutil.expense.TypicalExpenses.CHAIR;
import static seedu.homerce.testutil.expense.TypicalExpenses.CONDITIONER;

import org.junit.jupiter.api.Test;

public class ExpenseDefaultComparatorTest {
    @Test
    public void compare() {
        ExpenseDefaultComparator comparator = new ExpenseDefaultComparator();

        // first expense has a date before second expense -> return 1
        assertEquals(1, comparator.compare(CHAIR, CONDITIONER));

        // first expense has a date after second expense -> return 1
        assertEquals(-1, comparator.compare(CONDITIONER, CHAIR));

        // first expense has same date as second expense -> return 0
        assertEquals(0, comparator.compare(CONDITIONER, CONDITIONER));
    }
}
