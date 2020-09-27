package seedu.address.model.account;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.AddressBook;
import seedu.address.model.account.entry.Entry;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.ExpenseList;
import seedu.address.model.account.entry.Profit;
import seedu.address.model.account.entry.ProfitList;

/**
 * Wraps all data that represents one business Common Cents.
 */
public class Account implements ReadOnlyAccount {
    // identity field
    private final Name name;

    // data field
    private final ExpenseList expenses;
    private final ProfitList profits;

    Account(Name name) {
        requireNonNull(name);
        this.name = name;
        this.expenses = new ExpenseList();
        this.profits = new ProfitList();
    }

    Account(Name name, ReadOnlyAccount toBeCopied) {
        this(name);
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the expense list with {@code expenses}.
     */
    public void setExpenses(List<Expense> expenses) {
        this.expenses.setExpenses(expenses);
    }

    /**
     * Replaces the contents of the profit list with {@code profits}.
     */
    public void setProfits(List<Profit> profits) {
        this.profits.setProfits(profits);
    }

    /**
     * Resets the existing data of this {@code Account} with {@code newData}.
     */
    public void resetData(ReadOnlyAccount newData) {
        requireNonNull(newData);

        setExpenses(newData.getExpenseList());
        setProfits(newData.getProfitList());
    }

    //// entry-level operations

    /**
     * Returns true if an expense entry with the same identity as {@code expense} exists in the ExpenseList.
     */
    public boolean hasExpense(Expense expense) {
        requireNonNull(expense);
        return expenses.contains(expense);
    }

    /**
     * Returns true if a profit entry with the same identity as {@code profit} exists in the ProfitList.
     */
    public boolean hasProfit(Profit profit) {
        requireNonNull(profit);
        return profits.contains(profit);
    }

    /**
     * Returns true if an entry with the same identity as {@code entry} exists in the Account.
     */
    public boolean hasEntry(Entry entry) {
        requireNonNull(entry);
        return expenses.contains(entry) || profits.contains(entry);
    }

    /**
     * Adds an expense entry to the account.
     */
    public void addExpense(Expense e) {
        expenses.add(e);
    }

    /**
     * Adds a profit entry to the account.
     */
    public void addProfit(Profit p) {
        profits.add(p);
    }

    /**
     * Replaces the given expense entry {@code target} in the list with {@code editedExpense}.
     * {@code target} must exist in the account.
     */
    public void setExpense(Expense target, Expense editedExpense) {
        requireNonNull(editedExpense);

        expenses.setExpense(target, editedExpense);
    }

    /**
     * Replaces the given profit entry {@code target} in the list with {@code editedProfit}.
     * {@code target} must exist in the account.
     */
    public void setProfit(Profit target, Profit editedProfit) {
        requireNonNull(editedProfit);

        profits.setProfit(target, editedProfit);
    }

    /**
     * Removes {@code key} from this {@code Account}.
     * {@code key} must exist in the account.
     */
    public void removeExpense(Expense key) {
        expenses.remove(key);
    }

    /**
     * Removes {@code key} from this {@code Account}.
     * {@code key} must exist in the account.
     */
    public void removeProfit(Profit key) {
        profits.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return expenses.asUnmodifiableObservableList().size() + " expenses\n"
                + profits.asUnmodifiableObservableList().size() + " profits";
        // TODO: refine later
    }

    public Name getName() {
        return name;
    }

    @Override
    public ObservableList<Expense> getExpenseList() {
        return expenses.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Profit> getProfitList() {
        return profits.asUnmodifiableObservableList();
    }

    /**
     * Returns true if both Account have identity fields that are the same.
     * This defines a weaker notion of equality between two entries.
     */
    public boolean isSameAccount(Account otherAccount) {
        if (otherAccount == this) {
            return true;
        }

        return otherAccount != null
                && otherAccount.getName().equals(getName());
    }

    /**
     * Returns true if both expenses have the same identity and data fields.
     * This defines a stronger notion of equality between two accounts.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && (name.equals(((Account) other).name)
                && expenses.equals(((Account) other).expenses)
                && profits.equals(((Account) other).profits)));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, expenses, profits);
    }
}
