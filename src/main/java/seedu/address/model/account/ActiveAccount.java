package seedu.address.model.account;

import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.account.entry.Entry;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.Revenue;

public interface ActiveAccount {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Expense> PREDICATE_SHOW_ALL_EXPENSES = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Revenue> PREDICATE_SHOW_ALL_REVENUE = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Entry> PREDICATE_SHOW_ALL_ENTRY = unused -> true;

    /**
     * Returns a copy of this {@code ActiveAccount}.
     */
    ActiveAccount getCopy();

    /**
     * Returns the most recent previous state of this {@code ActiveAccount}.
     */
    Optional<ActiveAccount> getPreviousState();

    /**
     * Returns true if this {@code ActiveAccount} does not have a previous state.
     */
    boolean hasNoPreviousState();

    /**
     * Sets the existing data of this {@code ActiveAccount} {@code ActiveAccount} to be the previous state.
     */
    void setPreviousState();

    /**
     * Removes the previous state of this {@code ActiveAccount}.
     */
    void removePreviousState();

    /**
     * Resets the existing data of this {@code ActiveAccount} with the data of the previous state.
     */
    void returnToPreviousState();

    /**
     * Replaces account data with the data in {@code newActiveAccount}.
     */
    void setActiveAccount(ReadOnlyAccount newActiveAccount);

    /**
     * Replaces the name of the account with {@code name}.
     */
    void setName(Name name);

    /** Returns a copy of the Active Account */
    Account getAccount();

    /**
     * Returns true if an expense/revenue entry with the same identity as {@code entry} exists in the account.
     */
    boolean hasEntry(Entry entry);

    /**
     * Returns true if a expense with the same identity as {@code expense} exists in the account.
     */
    boolean hasExpense(Expense expense);

    /**
     * Returns true if a revenue with the same identity as {@code revenue} exists in the account.
     */
    boolean hasRevenue(Revenue revenue);

    /**
     * Deletes the given expense.
     * The expense must exist in the account.
     */
    void deleteExpense(Expense target);

    /**
     * Deletes the given revenue.
     * The revenue must exist in the account.
     */
    void deleteRevenue(Revenue target);

    /**
     * Adds the given expense.
     */
    void addExpense(Expense expense);

    /**
     * Adds the given revenue.
     */
    void addRevenue(Revenue revenue);

    /**
     * Replaces the given expense {@code target} with {@code editedExpense}.
     * {@code target} must exist in the account.
     */
    void setExpense(Expense target, Expense editedExpense);

    /**
     * Replaces the given revenue {@code target} with {@code editedRevenue}.
     * {@code target} must exist in the account.
     */
    void setRevenue(Revenue target, Revenue editedRevenue);

    /**
     * Clears all entries in the expense list.
     */
    void clearExpenses();

    /**
     * Clears all entries in the revenues list.
     */
    void clearRevenues();

    /** Returns an unmodifiable view of the filtered expense list */
    public ObservableList<Expense> getFilteredExpenseList();

    /** Returns an unmodifiable view of the filtered revenue list */
    public ObservableList<Revenue> getFilteredRevenueList();

    /**
     * Updates the filter of the filtered expense list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExpenseList(Predicate<Expense> predicate);

    /**
     * Updates the filter of the filtered revenue list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRevenueList(Predicate<Revenue> predicate);

    /**
     * Returns the total sum of the expenses.
     */
    public double getTotalExpenses();

    /**
     * Returns the total sum of the revenues.
     */
    public double getTotalRevenue();

    public double getProfits();

}
