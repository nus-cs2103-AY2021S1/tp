package seedu.cc.model.account;

import javafx.collections.ObservableList;
import seedu.cc.model.account.entry.Expense;
import seedu.cc.model.account.entry.Revenue;

public interface ReadOnlyAccount {

    Name getName();

    /**
     * Returns an unmodifiable view of the expense list.
     */
    ObservableList<Expense> getExpenseList();

    /**
     * Returns an unmodifiable view of the revenue list.
     */
    ObservableList<Revenue> getRevenueList();

}
