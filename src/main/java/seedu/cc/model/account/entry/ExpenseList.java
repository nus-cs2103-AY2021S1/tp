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
    private final ObservableList<Expense> internalList = FXCollections.observableArrayList();
    private final ObservableList<Expense> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent expense as the given argument.
     */
    public boolean contains(Entry toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds an expense to the list.
     */
    public void add(Expense toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the expense {@code target} in the list with {@code editedExpense}.
     * {@code target} must exist in the list.
     */
    public void setExpense(Expense target, Expense editedExpense) {
        requireAllNonNull(target, editedExpense);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EntryNotFoundException();
        }

        internalList.set(index, editedExpense);
    }

    /**
     * Removes the equivalent expense from the list.
     * The expense entry must exist in the list.
     */
    public void remove(Expense toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EntryNotFoundException();
        }
    }

    public void setExpenses(ExpenseList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code expenses}.
     */
    public void setExpenses(List<Expense> expenses) {
        requireAllNonNull(expenses);
        internalList.setAll(expenses);
    }

    /**
     * Clears all contents of this list.
     */
    public void clearExpenses() {
        internalList.clear();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Expense> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Expense> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenseList // instanceof handles nulls
                && internalList.equals(((ExpenseList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
