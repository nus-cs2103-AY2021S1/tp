package seedu.homerce.testutil.expense;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.manager.ExpenseTracker;

/**
 * A utility class containing a list of {@code Expense} objects to be used in tests.
 */
public class TypicalExpenses {

    public static final Expense CONDITIONER = new ExpenseBuilder().withDescription("Conditioner")
            .withAmount(15.00)
            .withIsFixed("n")
            .withDate("10-12-2020")
            .withTag("Hairsupplies").build();

    public static final Expense CHAIR = new ExpenseBuilder().withDescription("Chair")
            .withAmount(50.00)
            .withIsFixed("n")
            .withDate("10-12-2019")
            .withTag("others").build();

    public static final Expense AIRCON = new ExpenseBuilder().withDescription("Aircon")
            .withAmount(100.00)
            .withIsFixed("y")
            .withDate("30-10-2020")
            .withTag("utility").build();

    private TypicalExpenses() {
    } // prevents instantiation

    /**
     * Returns an {@code ExpenseTracker} with all the typical expenses.
     */
    public static ExpenseTracker getTypicalExpenseTracker() {
        ExpenseTracker et = new ExpenseTracker();
        for (Expense expense: getTypicalExpenses()) {
            et.addExpense(expense);
        }
        return et;
    }

    public static List<Expense> getTypicalExpenses() {
        return new ArrayList<>(Arrays.asList(CONDITIONER, CHAIR, AIRCON));
    }
}

