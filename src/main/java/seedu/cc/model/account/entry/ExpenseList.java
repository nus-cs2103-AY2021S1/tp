package seedu.cc.model.account.entry;

import static java.util.Objects.requireNonNull;
import static seedu.cc.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.cc.model.account.entry.exceptions.EntryNotFoundException;

/**
 * A list that stores the expense entries.
 * Supports a minimal set of list operations.
 */
public class ExpenseList implements Iterable<Expense> {
    private final ObservableList<Expense> expenses = FXCollections.observableArrayList();
    private final ObservableList<Expense> unmodifiedExpenses =
            FXCollections.unmodifiableObservableList(expenses);

    /**
     * Returns true if the list contains an equivalent expense as the given argument.
     */
    public boolean contains(Expense toCheck) {
        requireNonNull(toCheck);
        return expenses.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds an expense to the list.
     */
    public void add(Expense toAdd) {
        requireNonNull(toAdd);
        expenses.add(toAdd);
    }

    /**
     * Replaces the expense {@code target} in the list with {@code editedExpense}.
     * {@code target} must exist in the list.
     */
    public void setExpense(Expense target, Expense editedExpense) {
        requireAllNonNull(target, editedExpense);

        int index = expenses.indexOf(target);
        if (index == -1) {
            throw new EntryNotFoundException();
        }

        expenses.set(index, editedExpense);
    }

    /**
     * Removes the equivalent expense from the list.
     * The expense entry must exist in the list.
     */
    public void remove(Expense toRemove) {
        requireNonNull(toRemove);
        if (!expenses.remove(toRemove)) {
            throw new EntryNotFoundException();
        }
    }

    public void setExpenses(ExpenseList replacement) {
        requireNonNull(replacement);
        expenses.setAll(replacement.expenses);
    }

    /**
     * Replaces the contents of this list with {@code expenses}.
     */
    public void setExpenses(List<Expense> expenses) {
        requireAllNonNull(expenses);
        this.expenses.setAll(expenses);
    }

    /**
     * Clears all contents of this list.
     */
    public void clearExpenses() {
        expenses.clear();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Expense> asUnmodifiableObservableList() {
        return unmodifiedExpenses;
    }

    @Override
    public Iterator<Expense> iterator() {
        return expenses.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenseList // instanceof handles nulls
                && expenses.equals(((ExpenseList) other).expenses));
    }

    @Override
    public int hashCode() {
        return expenses.hashCode();
    }

}
