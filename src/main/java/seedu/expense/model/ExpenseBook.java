package seedu.expense.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.expense.model.budget.CategoryBudget;
import seedu.expense.model.budget.UniqueCategoryBudgetList;
import seedu.expense.model.expense.Amount;
import seedu.expense.model.expense.Expense;
import seedu.expense.model.expense.UniqueExpenseList;
import seedu.expense.model.expense.exceptions.CategoryNotFoundException;
import seedu.expense.model.tag.Tag;
import seedu.expense.model.tag.UniqueTagList;

/**
 * Wraps all data at the expense-book level
 * Duplicates are not allowed (by .isSameExpense comparison)
 */
public class ExpenseBook implements ReadOnlyExpenseBook, Statistics {

    public static final Tag DEFAULT_TAG = new Tag("Default");

    private final UniqueCategoryBudgetList budgets;
    private final UniqueExpenseList expenses;
    private final UniqueTagList tags;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        budgets = new UniqueCategoryBudgetList();
        expenses = new UniqueExpenseList();
        tags = new UniqueTagList();
    }

    public ExpenseBook() {
    }

    /**
     * Creates an ExpenseBook using the Expenses and Budgets in the {@code toBeCopied}
     */
    public ExpenseBook(ReadOnlyExpenseBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the expense list with {@code expenses}.
     * {@code expenses} must not contain duplicate expenses.
     */
    public void setExpenses(List<Expense> expenses) {
        this.expenses.setExpenses(expenses);
    }

    /**
     * Replaces the contents of the category-budgets list with {@code budgets}.
     * {@code expenses} must not contain duplicate expenses.
     */
    public void setBudgets(UniqueCategoryBudgetList budgets) {
        this.budgets.setBudgets(budgets);
    }

    public void setTags(List<Tag> tags) {
        this.tags.setTags(tags);
    }

    /**
     * Resets the existing data of this {@code ExpenseBook} with {@code newData}.
     */
    public void resetData(ReadOnlyExpenseBook newData) {
        requireNonNull(newData);

        setExpenses(newData.getExpenseList());
        setBudgets(newData.getBudgets());
        setTags(newData.getTags());
    }

    //// tag-level operations
    @Override
    public ObservableList<Tag> getTags() {
        return tags.asUnmodifiableObservableList();
    }

    //// budget-level operations

    @Override
    public UniqueCategoryBudgetList getBudgets() {
        return budgets;
    }

    /**
     * {@inheritDoc}
     *
     * @see UniqueCategoryBudgetList#asUnmodifiableObservableList()
     */
    @Override
    public ObservableList<CategoryBudget> getBudgetList() {
        return budgets.asUnmodifiableObservableList();
    }

    public void topupBudget(Amount amount) {
        budgets.topupBudget(amount);
    }

    /**
     * Tops up a category-budget by a given amount.
     *
     * @throws CategoryNotFoundException if requested category does not exist.
     */
    public void topupCategoryBudget(Tag category, Amount amount) {
        if (!tags.contains(category)) {
            throw new CategoryNotFoundException(category);
        }
        budgets.topupCategoryBudget(category, amount);
    }

    public boolean containsCategory(Tag tag) {
        return tags.contains(tag);
    }

    /**
     * Adds a category to the expense book.
     * The tag must not already exist in the expense book.
     */
    public void addCategory(Tag tag) {
        requireNonNull(tag);
        tags.add(tag);
        budgets.add(new CategoryBudget(tag));
    }

    /**
     * Deletes a category from the expense book.
     * The tag must already exist in the expense book.
     */
    public void deleteCategory(Tag tag) {
        requireNonNull(tag);
        if (!tags.contains(tag)) {
            throw new CategoryNotFoundException(tag);
        }
        tags.remove(tag);
        budgets.remove(new CategoryBudget(tag));
        expenses.resetExpenseCategory(expense -> expense.getTag().equals(tag));
    }

    /**
     * Updates the filter of the filtered budget list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    public void updateFilteredBudgets(Predicate<CategoryBudget> predicate) {
        requireNonNull(predicate);
        budgets.filterCategoryBudget(predicate);
    }

    /**
     * Returns true if the {@code CategoryBudget} that matches the specified category contains the given {@code amount}
     * or more.
     * @see UniqueCategoryBudgetList#categoryBudgetHasAmount(Tag, Amount)
     */
    public boolean categoryBudgetHasAmount(Tag category, Amount amount) {
        return budgets.categoryBudgetHasAmount(category, amount);
    }

    /**
     * Reduces a category-budget by a given amount.
     *
     * @throws CategoryNotFoundException if requested category does not exist.
     */
    public void reduceCategoryBudget(Tag category, Amount amount) throws CategoryNotFoundException {
        if (!containsCategory(category)) {
            throw new CategoryNotFoundException(category);
        }
        budgets.reduceCategoryBudget(category, amount);
    }

    //// expense-level operations

    /**
     * Returns true if a expense with the same identity as {@code expense} exists in the expense book.
     */
    public boolean hasExpense(Expense expense) {
        requireNonNull(expense);
        return expenses.contains(expense);
    }

    /**
     * Adds a expense to the expense book.
     * The expense must not already exist in the expense book.
     */
    public void addExpense(Expense p) {
        requireNonNull(p);

        if (!tags.contains(p.getTag())) {
            throw new CategoryNotFoundException(p.getTag());
        }

        expenses.add(p);
    }

    /**
     * Replaces the given expense {@code target} in the list with {@code editedExpense}.
     * {@code target} must exist in the expense book.
     * The expense identity of {@code editedExpense} must not be the same as another existing
     * expense in the expense book.
     */
    public void setExpense(Expense target, Expense editedExpense) {
        requireNonNull(editedExpense);

        if (!tags.contains(editedExpense.getTag())) {
            throw new CategoryNotFoundException(editedExpense.getTag());
        }

        expenses.setExpense(target, editedExpense);
    }

    /**
     * Removes {@code key} from this {@code ExpenseBook}.
     * {@code key} must exist in the expense book.
     */
    public void removeExpense(Expense key) {
        requireNonNull(key);
        expenses.remove(key);
    }

    /**
     * Updates the filter of the filtered expense list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    public void updateFilteredExpenses(Predicate<Expense> predicate) {
        requireNonNull(predicate);
        expenses.filterExpenses(predicate);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Expense} backed by the internal list of
     * {@code versionedExpenseBook}
     */
    public ObservableList<Expense> getFilteredExpenseList() {
        return expenses.getFilteredExpenses();
    }

    // Statistics method
    /**
     * {@inheritDoc}
     *
     * @see UniqueExpenseList#tallyExpenses()
     */
    @Override
    public Amount tallyExpenses() {
        return expenses.tallyExpenses();
    }

    /**
     * Sort expenses in Expense List according to comparator provided.
     * @param comparator
     */
    public void sortExpenses(Comparator<Expense> comparator) {
        expenses.sortExpenses(comparator);
    }

    /**
     * {@inheritDoc}
     *
     * @see UniqueCategoryBudgetList#tallyAmounts()
     */
    @Override
    public Amount tallyBudgets() {
        return budgets.tallyAmounts();
    }

    /**
     * Tallies the balance of budgets and expenses in the expense book.
     *
     * @return tallied balance of the expense book
     */
    @Override
    public Amount tallyBalance() {
        return tallyBudgets().subtract(tallyExpenses());
    }

    @Override
    public String getBudgetBarLabel() {
        ArrayList<Tag> checkedTagList = new ArrayList<>();
        tags.getTags().stream().map(tag -> Expense.getGenericExpenseWithTag(tag))
                .filter(expense -> expenses.getFilteredList().getPredicate().test(expense))
                .map(expense -> expense.getTag()).forEach(checkedTagList::add);
        return getBudgetLabelFromList(checkedTagList);
    }

    //// util methods

    private String getBudgetLabelFromList(ArrayList<Tag> list) {
        if (list.size() > 1) {
            // Show total budget if Expenses of all tags in UniqueTagList passes
            // predicate from expenses.getFilteredList()
            return "Total";
        } else if (list.size() == 1) {
            // Exactly one tag matches
            return list.get(0).toString();
        } else {
            // No match
            return "Default";
        }
    }

    @Override
    public String toString() {
        return expenses.asUnmodifiableObservableList().size() + " expenses";
        // TODO: refine later
    }

    @Override
    public ObservableList<Expense> getExpenseList() {
        return expenses.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenseBook // instanceof handles nulls
                && expenses.equals(((ExpenseBook) other).expenses));
    }

    @Override
    public int hashCode() {
        return expenses.hashCode();
    }
}
