package seedu.expense.model;

import static java.util.Objects.requireNonNull;
import static seedu.expense.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.expense.commons.core.GuiSettings;
import seedu.expense.commons.core.LogsCenter;
import seedu.expense.model.alias.AliasEntry;
import seedu.expense.model.alias.AliasMap;
import seedu.expense.model.budget.Budget;
import seedu.expense.model.expense.Amount;
import seedu.expense.model.expense.Expense;

/**
 * Represents the in-memory model of the expense book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ExpenseBook expenseBook;
    private final UserPrefs userPrefs;
    private final AliasMap aliasMap;
    private final FilteredList<Expense> filteredExpenses;
    private final Budget budget;

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
        filteredExpenses = new FilteredList<>(this.expenseBook.getExpenseList());
        budget = this.expenseBook.getBudget();
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
    }

    @Override
    public void setExpense(Expense target, Expense editedExpense) {
        requireAllNonNull(target, editedExpense);

        expenseBook.setExpense(target, editedExpense);
    }

    @Override
    public Budget getBudget() {
        return budget;
    }

    @Override
    public void topupBudget(Amount amount) {
        budget.topupBudget(amount);
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

    //=========== Filtered Expense List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Expense} backed by the internal list of
     * {@code versionedExpenseBook}
     */
    @Override
    public ObservableList<Expense> getFilteredExpenseList() {
        return filteredExpenses;
    }

    @Override
    public void updateFilteredExpenseList(Predicate<Expense> predicate) {
        requireNonNull(predicate);
        filteredExpenses.setPredicate(predicate);
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
                && userPrefs.equals(other.userPrefs)
                && filteredExpenses.equals(other.filteredExpenses);
    }

}
