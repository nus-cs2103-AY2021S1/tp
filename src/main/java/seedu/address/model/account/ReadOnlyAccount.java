package seedu.address.model.account;

import javafx.collections.ObservableList;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.Profit;

public interface ReadOnlyAccount {
    /**
     * Returns an unmodifiable view of the profit list.
     */
    ObservableList<Expense> getExpenseList();

    /**
     * Returns an unmodifiable view of the profit list.
     */
    ObservableList<Profit> getProfitList();

}
