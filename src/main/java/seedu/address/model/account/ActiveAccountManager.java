package seedu.address.model.account;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.account.entry.Entry;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.Revenue;

/**
 * Represents the in-memory model of the active account data.
 */
public class ActiveAccountManager implements ActiveAccount {
    private final Account activeAccount;
    private final FilteredList<Expense> filteredExpenses;
    private final FilteredList<Revenue> filteredRevenues;

    /**
     * Initializes an ActiveAccountManager with the given account.
     */
    public ActiveAccountManager(ReadOnlyAccount account) {
        super();
        requireNonNull(account);

        this.activeAccount = new Account(account);
        filteredExpenses = new FilteredList<>(this.activeAccount.getExpenseList());
        filteredRevenues = new FilteredList<>(this.activeAccount.getRevenueList());
    }

    //=========== Account ================================================================================

    @Override
    public void setActiveAccount(ReadOnlyAccount newActiveAccount) {
        this.activeAccount.resetData(newActiveAccount);
    }

    @Override
    public ReadOnlyAccount getAccount() {
        return activeAccount;
    }

    @Override
    public boolean hasEntry(Entry entry) {
        requireNonNull(entry);

        return activeAccount.hasEntry(entry);
    }

    @Override
    public boolean hasExpense(Expense expense) {
        requireNonNull(expense);

        return activeAccount.hasExpense(expense);
    }

    @Override
    public boolean hasRevenue(Revenue revenue) {
        requireNonNull(revenue);

        return activeAccount.hasRevenue(revenue);
    }

    @Override
    public void deleteExpense(Expense target) {
        activeAccount.removeExpense(target);
    }

    @Override
    public void deleteRevenue(Revenue target) {
        activeAccount.removeRevenue(target);
    }

    @Override
    public void addExpense(Expense expense) {
        activeAccount.addExpense(expense);

        updateFilteredRevenueList(PREDICATE_SHOW_ALL_REVENUE);
    }

    @Override
    public void addRevenue(Revenue revenue) {
        activeAccount.addRevenue(revenue);

        updateFilteredRevenueList(PREDICATE_SHOW_ALL_REVENUE);
    }

    @Override
    public void setExpense(Expense target, Expense editedExpense) {
        requireAllNonNull(target, editedExpense);

        activeAccount.setExpense(target, editedExpense);
    }

    @Override
    public void setRevenue(Revenue target, Revenue editedRevenue) {
        requireAllNonNull(target, editedRevenue);

        activeAccount.setRevenue(target, editedRevenue);
    }

    //=========== Filtered Account List Accessors =============================================================

    @Override
    public ObservableList<Expense> getFilteredExpenseList() {
        return filteredExpenses;
    }

    @Override
    public ObservableList<Revenue> getFilteredRevenueList() {
        return filteredRevenues;
    }

    @Override
    public void updateFilteredExpenseList(Predicate<Expense> predicate) {
        this.filteredExpenses.setPredicate(predicate);
    }

    @Override
    public void updateFilteredRevenueList(Predicate<Revenue> predicate) {
        this.filteredRevenues.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ActiveAccountManager)) {
            return false;
        }

        // state check
        ActiveAccountManager other = (ActiveAccountManager) obj;
        return activeAccount.equals(other.activeAccount)
                && filteredExpenses.equals(other.filteredExpenses)
                && filteredRevenues.equals(other.filteredRevenues);
    }
}
