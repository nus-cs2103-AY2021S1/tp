package seedu.expense.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.expense.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.expense.model.expense.exceptions.DuplicateExpenseException;
import seedu.expense.model.expense.exceptions.ExpenseNotFoundException;

/**
 * A list of expenses that enforces uniqueness between its elements and does not allow nulls.
 * A expense is considered unique by comparing using {@code Expense#isSameExpense(Expense)}.
 * As such, adding and updating of expenses uses Expense#isSameExpense(Expense) for equality so as
 * to ensure that the expense being added or updated is unique in terms of identity in the UniqueExpenseList.
 * However, the removal of an expense uses Expense#equals(Object) so
 * as to ensure that the expense with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Expense#isSameExpense(Expense)
 */
public class UniqueExpenseList implements Iterable<Expense> {

    private final ObservableList<Expense> internalList = FXCollections.observableArrayList();
    private final ObservableList<Expense> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private final FilteredList<Expense> filteredList =
            new FilteredList<>(asUnmodifiableObservableList());

    /**
     * Returns true if the list contains an equivalent expense as the given argument.
     */
    public boolean contains(Expense toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameExpense);
    }

    /**
     * Adds a expense to the list.
     * The expense must not already exist in the list.
     */
    public void add(Expense toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateExpenseException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the expense {@code target} in the list with {@code editedExpense}.
     * {@code target} must exist in the list.
     * The expense identity of {@code editedExpense} must not be the same as another existing expense in the list.
     */
    public void setExpense(Expense target, Expense editedExpense) {
        requireAllNonNull(target, editedExpense);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ExpenseNotFoundException();
        }

        if (!target.isSameExpense(editedExpense) && contains(editedExpense)) {
            throw new DuplicateExpenseException();
        }

        internalList.set(index, editedExpense);
    }

    /**
     * Resets the category tag of expenses that passes the {@code predicate} to Default tag
     * @param predicate condition for the expenses to be met
     */
    public void resetExpenseCategory(Predicate<Expense> predicate) {
        requireNonNull(predicate);
        internalList.stream().filter(predicate).forEach(expense ->
            setExpense(expense, expense.resetTag()));
    }

    /**
     * Removes the equivalent expense from the list.
     * The expense must exist in the list.
     */
    public void remove(Expense toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ExpenseNotFoundException();
        }
    }

    public void setExpenses(UniqueExpenseList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code expenses}.
     * {@code expenses} must not contain duplicate expenses.
     */
    public void setExpenses(List<Expense> expenses) {
        requireAllNonNull(expenses);
        if (!expensesAreUnique(expenses)) {
            throw new DuplicateExpenseException();
        }

        internalList.setAll(expenses);
    }

    /**
     * Replaces the contents of this list's filtered list with {@code expenses}.
     * {@code expenses} must not contain duplicate expenses.
     */
    public void setFilteredExpenses(List<Expense> expenses) {
        requireAllNonNull(expenses);
        if (!expensesAreUnique(expenses)) {
            throw new DuplicateExpenseException();
        }

        filteredList.setAll(expenses);
    }

    /**
     * Returns the filtered list of this object.
     */
    public ObservableList<Expense> getFilteredExpenses() {
        return filteredList;
    }

    /**
     * Filters the contents of this list's filtered list with {@code predicate}.
     */
    public void filterExpenses(Predicate<Expense> predicate) {
        requireAllNonNull(predicate);
        filteredList.setPredicate(predicate);
    }

    /**
     * Calculates the sum of the expenses in the expense list.
     * @return sum of expenses.
     */
    public double tallyExpenses() {
        double sum = 0;
        Iterator<Expense> i = iterator();
        while (i.hasNext()) {
            sum += i.next().getAmount().asDouble();
        }
        return sum;
    }

    /**
     * Sort expenses in Expense List according to comparator provided.
     * @param comparator
     */
    public void sortExpenses(Comparator<Expense> comparator) {
        internalList.setAll(new SortedList<Expense>(this.internalList, comparator));
    }
    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Expense> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public FilteredList<Expense> getFilteredList() {
        return filteredList;
    }

    @Override
    public Iterator<Expense> iterator() {
        return filteredList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueExpenseList // instanceof handles nulls
                && internalList.equals(((UniqueExpenseList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code expenses} contains only unique expenses.
     */
    private boolean expensesAreUnique(List<Expense> expenses) {
        for (int i = 0; i < expenses.size() - 1; i++) {
            for (int j = i + 1; j < expenses.size(); j++) {
                if (expenses.get(i).isSameExpense(expenses.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
