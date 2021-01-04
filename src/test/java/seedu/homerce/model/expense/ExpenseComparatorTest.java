package seedu.homerce.model.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.homerce.testutil.expense.TypicalExpenses.CHAIR;
import static seedu.homerce.testutil.expense.TypicalExpenses.CONDITIONER;

import org.junit.jupiter.api.Test;

public class ExpenseComparatorTest {
    @Test
    public void compare() {
        ExpenseComparator comparator = new ExpenseComparator();

        // Value of first expense less than value of second expense -> return -1
        assertEquals(-1, comparator.compare(CONDITIONER, CHAIR));

        // Value of first expense more than value of second expense -> return 1
        assertEquals(1, comparator.compare(CHAIR, CONDITIONER));

        // Value of first expense equal value of second expense -> return 0
        assertEquals(0, comparator.compare(CONDITIONER, CONDITIONER));
    }
}

