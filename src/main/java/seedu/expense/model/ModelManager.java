package seedu.expense.model;

import static java.util.Objects.requireNonNull;
import static seedu.expense.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.expense.commons.core.GuiSettings;
import seedu.expense.commons.core.LogsCenter;
import seedu.expense.model.alias.AliasEntry;
import seedu.expense.model.alias.AliasMap;
import seedu.expense.model.budget.Budget;
import seedu.expense.model.budget.CategoryBudget;
import seedu.expense.model.expense.Amount;
import seedu.expense.model.expense.Expense;
import seedu.expense.model.expense.exceptions.CategoryNotFoundException;
import seedu.expense.model.tag.Tag;

/**
 * Represents the in-memory model of the expense book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ExpenseBook expenseBook;
    private final UserPrefs userPrefs;
    private final AliasMap aliasMap;

    /**
     * Initializes a ModelManager with the given expenseBook and userPrefs.
     */
    public ModelManager(ReadOnlyExpenseBook expenseBook, ReadOnlyUserPrefs userPrefs,
                        AliasMap aliasMap) {
        super();
        requireAllNonNull(expenseBook, userPrefs);

        logger.fine("Initializing with expense book: " + expenseBook + " and user prefs " + userPrefs);

        this.expenseBook = new ExpenseBook(expenseBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.aliasMap = new AliasMap(aliasMap);
    }

    public ModelManager() {
        this(new ExpenseBook(), new UserPrefs(), new AliasMap());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getExpenseBookFilePath() {
        return userPrefs.getExpenseBookFilePath();
    }

    @Override
    public void setExpenseBookFilePath(Path expenseBookFilePath) {
        requireNonNull(expenseBookFilePath);
        userPrefs.setExpenseBookFilePath(expenseBookFilePath);
    }

    //=========== ExpenseBook ================================================================================

    @Override
    public void setExpenseBook(ReadOnlyExpenseBook expenseBook) {
        this.expenseBook.resetData(expenseBook);
    }

    @Override
    public ReadOnlyExpenseBook getExpenseBook() {
        return expenseBook;
    }

    @Override
    public Statistics getStatistics() {
        return expenseBook;
    }

    @Override
    public boolean hasExpense(Expense expense) {
        requireNonNull(expense);
        return expenseBook.hasExpense(expense);
    }

    @Override
    public void deleteExpense(Expense target) {
        expenseBook.removeExpense(target);
    }

    @Override
    public void addExpense(Expense expense) {
        expenseBook.addExpense(expense);
        updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
        updateFilteredBudgetList(PREDICATE_SHOW_ALL_BUDGETS);
    }

    @Override
    public void setExpense(Expense target, Expense editedExpense) {
        requireAllNonNull(target, editedExpense);

        expenseBook.setExpense(target, editedExpense);
    }

    @Override
    public Budget getTotalBudget() {
        return expenseBook.getBudgets();
    }

    @Override
    public void topupBudget(Amount amount) {
        expenseBook.topupBudget(amount);
    }

    @Override
    public void topupCategoryBudget(Tag category, Amount amount) throws CategoryNotFoundException {
        if (!expenseBook.containsCategory(category)) {
            throw new CategoryNotFoundException();
        }

        expenseBook.topupCategoryBudget(category, amount);
    }

    //=========== AliasMap ================================================================================

    @Override
    public void setAliasMap(AliasMap aliasMap) {
        this.aliasMap.resetData(aliasMap);
    }

    @Override
    public AliasMap getAliasMap() {
        return aliasMap;
    }

    @Override
    public boolean hasAlias(AliasEntry alias) {
        requireNonNull(alias);
        return aliasMap.hasAlias(alias);
    }

    @Override
    public void deleteAlias(AliasEntry alias) {
        aliasMap.removeAlias(alias);
    }

    @Override
    public void addAlias(AliasEntry alias) {
        aliasMap.addAlias(alias);
    }

    @Override
    public void setAlias(AliasEntry target, AliasEntry editedExpense) throws IllegalArgumentException {
        requireAllNonNull(target, editedExpense);
        aliasMap.setAlias(target, editedExpense);
    }

    //=========== Sorted Expense List Accessors ==============================================================

    @Override
    public void sortExpenseList(Comparator<Expense> expenseComparator) {
        expenseBook.sortExpenses(expenseComparator);
    }

    //=========== Filtered Expense List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Expense} backed by the internal list of
     * {@code versionedExpenseBook}
     */
    @Override
    public ObservableList<Expense> getFilteredExpenseList() {
        return expenseBook.getFilteredExpenseList();
    }

    @Override
    public void updateFilteredExpenseList(Predicate<Expense> predicate) {
        requireNonNull(predicate);
        expenseBook.updateFilteredExpenses(predicate);
    }

    @Override
    public void updateFilteredBudgetList(Predicate<CategoryBudget> predicate) {
        requireNonNull(predicate);
        expenseBook.updateFilteredBudgets(predicate);
    }

    /**
     * Updates the expense book to one that matches the given {@code category}.
     */
    @Override
    public void updateExpenseBookCategory(Tag category) {
        requireNonNull(category);
        updateFilteredBudgetList(budget -> budget.getTag().equals(category));
        updateFilteredExpenseList(expense -> expense.getTag().equals(category));
    }

    /**
     * Checks if the given Tag is present in the expense book.
     * @see ExpenseBook#containsCategory(Tag)
     */
    @Override
    public boolean hasCategory(Tag toCheck) {
        return expenseBook.containsCategory(toCheck);
    }

    @Override
    public void addCategory(Tag tag) {
        expenseBook.addCategory(tag);
        updateFilteredBudgetList(PREDICATE_SHOW_ALL_BUDGETS);
    }

    @Override
    public void deleteCategory(Tag tag) {
        expenseBook.deleteCategory(tag);
        updateFilteredBudgetList(PREDICATE_SHOW_ALL_BUDGETS);
        updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
    }

    /**
     * Switches the expense book to one that matches the given {@code category}.
     */
    @Override
    public void switchCategory(Tag category) {
        requireNonNull(category);
        if (hasCategory(category)) {
            updateExpenseBookCategory(category);
        }
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return expenseBook.equals(other.expenseBook)
                && userPrefs.equals(other.userPrefs);
    }
}
