package seedu.cc.model.account;

import static java.util.Objects.requireNonNull;
import static seedu.cc.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.cc.model.account.exceptions.AccountNotFoundException;
import seedu.cc.model.account.exceptions.DuplicateAccountException;

/**
 * A list of accounts that enforces uniqueness between its elements and does not allow nulls.
 * An account is considered unique by comparing using {@code Account#isSameAccount(Account)}. As such, adding and
 * updating of accounts uses Account#isSameAccount(Account) for equality so as to ensure that the account being added or
 * updated is unique in terms of identity in the UniqueAccountList. However, the removal of a account uses
 * Account#equals(Object) so as to ensure that the account with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Account#isSameAccount(Account)
 */
public class UniqueAccountList implements Iterable<Account> {
    private static final int ACCOUNT_NOT_FOUND_INDEX = -1;
    private static final String NEW_LINE = "\n";

    private final ObservableList<Account> internalList = FXCollections.observableArrayList();
    private final ObservableList<Account> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent account as the given argument.
     */
    public boolean contains(Account toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAccount);
    }

    /**
     * Adds a account to the list.
     * The account must not already exist in the list.
     */
    public void add(Account toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAccountException();
        }
        internalList.add(toAdd);
    }

    private int getAccountIndex(Name name) {
        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Replaces the account {@code target} in the list with {@code editedAccount}.
     * {@code target} must exist in the list.
     * The account identity of {@code editedAccount} must not be the same as another existing account in the list.
     */
    public void setAccount(Account target, Account editedAccount) {
        requireAllNonNull(target, editedAccount);
        Account copiedEditedAccount = editedAccount.copyData();
        int index = internalList.indexOf(target);

        if (index == ACCOUNT_NOT_FOUND_INDEX) {
            throw new AccountNotFoundException();
        }

        if (!target.isSameAccount(copiedEditedAccount) && contains(copiedEditedAccount)) {
            throw new DuplicateAccountException();
        }

        internalList.set(index, copiedEditedAccount);
    }

    public void setAccount(Account editedAccount) {
        Name accountName = editedAccount.getName();
        int accountIndex = getAccountIndex(accountName);

        if (accountIndex == ACCOUNT_NOT_FOUND_INDEX) {
            throw new AccountNotFoundException();
        }

        Account target = internalList.get(accountIndex);

        if (!target.isSameAccount(editedAccount) && contains(editedAccount)) {
            throw new DuplicateAccountException();
        }

        Account copiedAccount = editedAccount.copyData();
        internalList.set(accountIndex, copiedAccount);
    }

    /**
     * Removes the equivalent account from the list.
     * The account must exist in the list.
     */
    public void remove(Account toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AccountNotFoundException();
        }
    }

    public void setAccounts(UniqueAccountList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code accounts}.
     * {@code accounts} must not contain duplicate accounts.
     */
    public void setAccounts(List<Account> accounts) {
        requireAllNonNull(accounts);
        if (!accountsAreUnique(accounts)) {
            throw new DuplicateAccountException();
        }

        internalList.setAll(accounts);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Account> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Account> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueAccountList // instanceof handles nulls
                && internalList.equals(((UniqueAccountList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code accounts} contains only unique accounts.
     */
    private boolean accountsAreUnique(List<Account> accounts) {
        for (int i = 0; i < accounts.size() - 1; i++) {
            for (int j = i + 1; j < accounts.size(); j++) {
                if (accounts.get(i).isSameAccount(accounts.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
