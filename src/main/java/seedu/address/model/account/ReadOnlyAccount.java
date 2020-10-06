package seedu.address.model.account;

import javafx.collections.ObservableList;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.Revenue;

public interface ReadOnlyAccount {
    /**
     * Returns an unmodifiable view of the expense list.
     */
    ObservableList<Expense> getExpenseList();

    /**
     * Returns an unmodifiable view of the revenue list.
     */
    ObservableList<Revenue> getRevenueList();

}
