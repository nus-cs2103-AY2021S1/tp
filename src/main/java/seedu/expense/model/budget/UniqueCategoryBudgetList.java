package seedu.expense.model.budget;

import static java.util.Objects.requireNonNull;
import static seedu.expense.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.expense.model.ExpenseBook.DEFAULT_TAG;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.expense.model.budget.exceptions.CategoryBudgetNotFoundException;
import seedu.expense.model.budget.exceptions.DuplicateCategoryBudgetException;
import seedu.expense.model.expense.Amount;
import seedu.expense.model.tag.Tag;

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
     * Removes a category-budget to the list.
     * The category-budget must already exist in the list.
     */
    public void remove(CategoryBudget toDelete) {
        requireNonNull(toDelete);
        if (!contains(toDelete)) {
            throw new CategoryBudgetNotFoundException();
        }
        internalList.remove(toDelete);
    }

    /**
     * Calculates the sum of the budgets in the category-budgets list.
     * @return sum of budgets.
     */
    public Amount tallyAmounts() {
        int size = filteredList.size();
        Amount sum = internalList.size() == size && size != 1 || isAllDefaultCategory()
            ? defaultCategory.getAmount()
            : Amount.zeroAmount();
        assert sum.greaterThanEquals(Amount.zeroAmount());
        Iterator<CategoryBudget> i = iterator();
        while (i.hasNext()) {
            sum = sum.add(i.next().getAmount());
        }
        return sum;
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

    /**
     * Filters this list's filtered list by {@code predicate}
     */
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

    /**
     * Returns the {@code CategoryBudget} corresponding to the specified category.
     *
     * @throws CategoryBudgetNotFoundException if the supplied category does not exist in the list of category-budgets.
     */
    public CategoryBudget getCategoryBudget(Tag category) {
        requireNonNull(category);

        if (category.equals(DEFAULT_TAG)) {
            return getDefaultCategory();
        }

        List<CategoryBudget> categoryBudgets = internalList.stream()
                .filter(categoryBudget -> categoryBudget.getTag().equals(category))
                .collect(Collectors.toList());

        if (categoryBudgets.isEmpty()) {
            throw new CategoryBudgetNotFoundException();
        }

        return categoryBudgets.get(0);
    }

    /**
     * Tops up the {@code CategoryBudget} that matches the specified category by the given amount {@code toAdd}.
     */
    public void topupCategoryBudget(Tag category, Amount toAdd) {
        requireAllNonNull(category, toAdd);

        if (category.equals(DEFAULT_TAG)) {
            topupBudget(toAdd);
        }

        internalList.stream()
                .filter(categoryBudget -> categoryBudget.getTag().equals(category))
                .forEach(categoryBudget -> categoryBudget.topupBudget(toAdd));
    }

    /**
     * Reduces the {@code CategoryBudget} that matches the specified category by the given amount {@code toSubtract}.
     */
    public void reduceCategoryBudget(Tag category, Amount toSubtract) {
        requireAllNonNull(category, toSubtract);

        if (category.equals(DEFAULT_TAG)) {
            reduceBudget(toSubtract);
        }

        internalList.stream()
                .filter(categoryBudget -> categoryBudget.getTag().equals(category))
                .forEach(categoryBudget -> categoryBudget.reduceBudget(toSubtract));
    }

    @Override
    public Amount getAmount() {
        return tallyAmounts();
    }

    @Override
    public void topupBudget(Amount toAdd) {
        defaultCategory.topupBudget(toAdd);
    }

    @Override
    public void reduceBudget(Amount toSubtract) {
        defaultCategory.reduceBudget(toSubtract);
    }

    /**
     * Returns true if the default budget contains the specified {@code amount} or more.
     */
    @Override
    public boolean hasAmount(Amount amount) {
        return defaultCategory.hasAmount(amount);
    }

    /**
     * Returns true if the {@code CategoryBudget} that matches the specified category contains the given {@code amount}
     * or more.
     */
    public boolean categoryBudgetHasAmount(Tag category, Amount amount) {
        return getCategoryBudget(category).hasAmount(amount);
    }

    private boolean isAllDefaultCategory() {
        Tag defaultTag = new Tag("Default");
        return filteredList.stream().allMatch(budget -> budget.getTag().equals(defaultTag));
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
