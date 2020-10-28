package seedu.expense.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.expense.commons.core.GuiSettings;
import seedu.expense.model.alias.AliasEntry;
import seedu.expense.model.alias.AliasMap;
import seedu.expense.model.budget.Budget;
import seedu.expense.model.budget.CategoryBudget;
import seedu.expense.model.expense.Amount;
import seedu.expense.model.expense.Expense;
import seedu.expense.model.expense.exceptions.CategoryNotFoundException;
import seedu.expense.model.tag.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Expense> PREDICATE_SHOW_ALL_EXPENSES = unused -> true;

    Predicate<CategoryBudget> PREDICATE_SHOW_ALL_BUDGETS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' expense book file path.
     */
    Path getExpenseBookFilePath();

    /**
     * Sets the user prefs' expense book file path.
     */
    void setExpenseBookFilePath(Path expenseBookFilePath);

    /**
     * Replaces expense book data with the data in {@code expenseBook}.
     */
    void setExpenseBook(ReadOnlyExpenseBook expenseBook);

    /**
     * Returns the ExpenseBook
     */
    ReadOnlyExpenseBook getExpenseBook();

    /**
     * Returns the Statistics
     */
    Statistics getStatistics();

    /**
     * Returns true if an expense with the same identity as {@code expense} exists in the expense book.
     */
    boolean hasExpense(Expense expense);

    /**
     * Deletes the given expense.
     * The expense must exist in the expense book.
     */
    void deleteExpense(Expense target);

    /**
     * Adds the given expense.
     * {@code expense} must not already exist in the expense book.
     */
    void addExpense(Expense expense);

    /**
     * Replaces the given expense {@code target} with {@code editedExpense}.
     * {@code target} must exist in the expense book.
     * The expense identity of {@code editedExpense} must not be the same as another existing expense
     * in the expense book.
     */
    void setExpense(Expense target, Expense editedExpense);

    void setAliasMap(AliasMap aliasMap);

    AliasMap getAliasMap();

    boolean hasAlias(AliasEntry alias);

    void deleteAlias(AliasEntry alias);

    void addAlias(AliasEntry alias);

    void setAlias(AliasEntry target, AliasEntry editedExpense);

    /**
     * Returns an unmodifiable view of the sorted expense list
     */
    void sortExpenseList(Comparator<Expense> expenseComparator);

    /**
     * Returns an unmodifiable view of the filtered expense list
     */
    ObservableList<Expense> getFilteredExpenseList();

    /**
     * Updates the filter of the filtered expense list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExpenseList(Predicate<Expense> predicate);

    /**
     * Updates the filter of the filtered budget list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBudgetList(Predicate<CategoryBudget> predicate);

    /**
     * Updates the category expense book by the given {@code category}.
     *
     * @throws NullPointerException if {@code category} is null.
     */
    void updateExpenseBookCategory(Tag category);

    /**
     * Returns the budget.
     */
    Budget getTotalBudget();

    /**
     * Adds the given amount to the budget.
     */
    void topupBudget(Amount amount);

    /**
     * Adds the given amount to the {@code CategoryBudget} that corresponding to the specified {@code category}.
     *
     */
    void topupCategoryBudget(Tag category, Amount amount) throws CategoryNotFoundException;

    /**
     * Checks if the given Tag is present in any of the category budget.
     */
    boolean hasCategory(Tag toCheck);

    /**
     * Adds the given Tag to the expense book.
     */
    void addCategory(Tag tag);

    /**
     * Deletes the given Tag from the expense book.
     */
    void deleteCategory(Tag tag);

    /**
     * Switches the category expense book into the one that matches the given Tag.
     */
    void switchCategory(Tag category);
}
