package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.account.Account;

/**
 * Unmodifiable view of a Common Cents
 */
public interface ReadOnlyCommonCents {
    /**
     * Returns an unmodifiable view of the accounts list.
     * This list will not contain any duplicate accounts.
     */
    ObservableList<Account> getAccountList();
}
