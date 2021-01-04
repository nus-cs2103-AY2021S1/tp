package seedu.homerce.model.manager;

import javafx.collections.ObservableList;
import seedu.homerce.model.expense.Expense;

public interface ReadOnlyExpenseTracker {
    /**
     * Returns an unmodifiable view of the expenses list.
     */
    ObservableList<Expense> getExpenseList();

    /**
     * Sorts the expense list.
     * @param isAscending
     */
    public void sortExpenseList(boolean isAscending);

    /**
     * Sorts the expense list by date (default).
     */
    public void sortDefaultExpenseList();
}
