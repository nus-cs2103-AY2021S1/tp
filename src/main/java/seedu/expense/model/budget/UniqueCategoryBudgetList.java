package seedu.expense.model.budget;

import static java.util.Objects.requireNonNull;
import static seedu.expense.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.expense.model.ExpenseBook.DEFAULT_TAG;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.expense.model.budget.exceptions.DuplicateCategoryBudgetException;
import seedu.expense.model.expense.Amount;

/**
 * A list of category-budgets that enforces uniqueness between its elements and does not allow nulls.
 * A category-budget is considered unique by comparing using
 * {@code CategoryBudget#isSameCategoryBudget(CategoryBudget)}.
 * As such, adding and updating of expenses uses Expense#isSameExpense(Expense) for equality so as
 * to ensure that the expense being added or updated is unique in terms of identity in the UniqueExpenseList.
 * However, the removal of an expense uses Expense#equals(Object) so
 * as to ensure that the expense with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see CategoryBudget#isSameCategoryBudget(CategoryBudget)
 */
public class UniqueCategoryBudgetList implements Budget, Iterable<CategoryBudget> {

    private final CategoryBudget defaultCategory = new CategoryBudget(DEFAULT_TAG);
    private final ObservableList<CategoryBudget> internalList = FXCollections.observableArrayList();
    private final ObservableList<CategoryBudget> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);
    private final FilteredList<CategoryBudget> filteredList =
        new FilteredList<>(asUnmodifiableObservableList());

    /**
     * Returns true if the list contains an equivalent category-budget as the given argument.
     */
    public boolean contains(CategoryBudget toCheck) {
        requireNonNull(toCheck);
        return defaultCategory.isSameCategoryBudget(toCheck)
                || internalList.stream().anyMatch(toCheck::isSameCategoryBudget);
    }

    /**
     * Adds a category-budget to the list.
     * The category-budget must not already exist in the list.
     */
    public void add(CategoryBudget toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateCategoryBudgetException();
        }
        internalList.add(toAdd);
    }

    /**
     * Calculates the sum of the budgets in the category-budgets list.
     * @return sum of budgets.
     */
    public double tallyAmounts() {
        double sum = defaultCategory.getAmount().asDouble();
        Iterator<CategoryBudget> i = iterator();
        while (i.hasNext()) {
            sum += i.next().getAmount().asDouble();
        }
        return sum;
    }

    /**
     * Calculates the sum of the budgets in the category-budgets list that matches {@code predicate}.
     * @return sum of filtered budgets.
     */
    public double tallyAmounts(Predicate<CategoryBudget> predicate) {
        return filteredList.stream()
            .map(budget -> budget.getAmount().asDouble())
            .reduce(0.0, (partialSum, amount) -> partialSum + amount);
    }

    public void setBudgets(UniqueCategoryBudgetList replacement) {
        requireNonNull(replacement);

        defaultCategory.copyAmount(replacement.defaultCategory.getAmount());
        internalList.setAll(replacement.internalList);
    }

    public void setBudgets(List<CategoryBudget> budgets) {
        requireAllNonNull(budgets);
        internalList.setAll(budgets);
        setFilteredBudgets(budgets);
    }

    public void setFilteredBudgets(List<CategoryBudget> budgets) {
        requireAllNonNull(budgets);
        filteredList.setAll(budgets);
    }

    public void filterCategoryBudget(Predicate<CategoryBudget> predicate) {
        requireNonNull(predicate);
        filteredList.setPredicate(predicate);
    }

    public List<CategoryBudget> getCategoryBudgets() {
        return internalList;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<CategoryBudget> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public FilteredList<CategoryBudget> getFilteredList() {
        return filteredList;
    }

    public CategoryBudget getDefaultCategory() {
        return defaultCategory;
    }

    @Override
    public Amount getAmount() {
        return new Amount(tallyAmounts());
    }

    @Override
    public void topupBudget(Amount toAdd) {
        defaultCategory.topupBudget(toAdd);
    }

    @Override
    public Iterator<CategoryBudget> iterator() {
        return filteredList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueCategoryBudgetList // instanceof handles nulls
                && internalList.equals(((UniqueCategoryBudgetList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public void reset() {
        partialReset();
        Iterator<CategoryBudget> i = iterator();
        while (i.hasNext()) {
            i.next().reset();
        }
    }

    /**
     * Resets only the default category-budget.
     */
    public void partialReset() {
        defaultCategory.reset();
    }
}
