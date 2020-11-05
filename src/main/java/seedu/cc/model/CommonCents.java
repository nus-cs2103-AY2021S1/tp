package seedu.cc.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.cc.model.account.Account;
//import seedu.address.model.account.Name;
import seedu.cc.model.account.UniqueAccountList;

/**
 * Wraps all data at the money-tracker level
 * Duplicate accounts are not allowed (by .isSameAccount comparison)
 */
public class CommonCents implements ReadOnlyCommonCents {
    //private static final String ACCOUNT_NAME = "General account";
    //private static final Account GENERAL_ACCOUNT = new Account(new Name(ACCOUNT_NAME));
    private final UniqueAccountList accounts;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        accounts = new UniqueAccountList();
        //accounts.add(GENERAL_ACCOUNT);
    }

    public CommonCents() {}

    /**
     * Creates a CommonCents using the Accounts in the {@code toBeCopied}
     */
    public CommonCents(ReadOnlyCommonCents toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the account list with {@code accounts}.
     * {@code accounts} must not contain duplicate accounts.
     */
    public void setAccounts(List<Account> accounts) {
        this.accounts.setAccounts(accounts);
    }

    /**
     * Resets the existing data of this {@code CommonCents} with {@code newData}.
     */
    public void resetData(ReadOnlyCommonCents newData) {
        requireNonNull(newData);

        setAccounts(newData.getAccountList());
    }

    //// account-level operations

    /**
     * Returns true if an account with the same identity as {@code account} exists in the Common Cents.
     */
    public boolean hasAccount(Account account) {
        requireNonNull(account);
        return accounts.contains(account);
    }

    /**
     * Adds an account to the money-tracker.
     * The account must not already exist in the money-tracker.
     */
    public void addAccount(Account a) {
        accounts.add(a);
    }

    /**
     * Replaces the given account {@code target} in the list with {@code editedAccount}.
     * {@code target} must exist in the money-tracker.
     * The account identity of {@code editedAccount} must not be the same as another
     * existing account in the money-tracker.
     */
    public void setAccount(Account target, Account editedAccount) {
        requireNonNull(editedAccount);

        accounts.setAccount(target, editedAccount);
    }

    /**
     * Replaces the account in the list with the same name as {@code editedAccount}.
     * The account with the same name must exist in the money-tracker.
     */
    public void setAccount(Account editedAccount) {
        requireNonNull(editedAccount);

        accounts.setAccount(editedAccount);
    }



    /**
     * Removes {@code key} from this {@code CommonCents}.
     * {@code key} must exist in the money-tracker.
     */
    public void removeAccount(Account key) {
        accounts.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return accounts.asUnmodifiableObservableList().size() + " accounts";
        // TODO: refine later
    }

    @Override
    public ObservableList<Account> getAccountList() {
        return accounts.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommonCents // instanceof handles nulls
                && accounts.equals(((CommonCents) other).accounts));
    }

    @Override
    public int hashCode() {
        return accounts.hashCode();
    }

}
