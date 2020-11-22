package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.expense.Budget;
import seedu.address.model.expense.Category;
import seedu.address.model.expense.Currency;
import seedu.address.model.expense.ExchangeRate;
import seedu.address.model.expense.Expense;
import seedu.address.model.util.SampleDataUtil;

/**
 * Represents the in-memory model of the address book data.
 */
public class ExpenseModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ExpenseModelManager.class);

    private final ExpenseBook expenseBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Expense> filteredExpenses;


    /**
     * Initializes a ModelManager with the given expenseBook and userPrefs.
     */
    public ExpenseModelManager(ReadOnlyExpenseBook expenseBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(expenseBook, userPrefs);

        logger.fine("Initializing with expense book: " + expenseBook + " and user prefs " + userPrefs);

        this.expenseBook = new ExpenseBook(expenseBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredExpenses = new FilteredList<>(this.expenseBook.getExpenseList());
    }

    public ExpenseModelManager() {
        this(new ExpenseBook(), new UserPrefs());
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

    @Override
    public void setExpenseBook(ReadOnlyExpenseBook expenseBook) {
        this.expenseBook.resetData(expenseBook);
    }

    @Override
    public ReadOnlyExpenseBook getExpenseBook() {
        return expenseBook;
    }

    /**
     * Returns an unmodifiable view of expense list with sample removed.
     */
    @Override
    public ReadOnlyExpenseBook getExpenseBookWithoutSample() {
        return SampleDataUtil.getExpenseBookWithoutSample(expenseBook);
    }

    //=========== ExpenseBook ================================================================================

    @Override
    public boolean hasExpense(Expense expense) {
        requireNonNull(expense);
        return expenseBook.hasExpense(expense);
    }

    @Override
    public void deleteExpense(Expense targetExpense) {
        expenseBook.removeExpense(targetExpense);
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

    //=========== Filtered Expense List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Expense} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Expense> getFilteredExpenseList() {
        return filteredExpenses;
    }

    /**
     * Returns an updated Expense List
     * @param predicate predicate
     */
    @Override
    public void updateFilteredExpenseList(Predicate<Expense> predicate) {
        requireNonNull(predicate);
        filteredExpenses.setPredicate(predicate);
    }

    // end of edit

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ExpenseModelManager)) {
            return false;
        }

        // state check
        ExpenseModelManager other = (ExpenseModelManager) obj;
        return expenseBook.equals(other.expenseBook)
                && userPrefs.equals(other.userPrefs)
                && filteredExpenses.equals(other.filteredExpenses);
    }

    @Override
    public Budget getExpenseBookBudget() {
        return expenseBook.getBudget();
    }

    @Override
    public double getExpenseBookSpending() {
        return expenseBook.totalSpending();
    }

    @Override
    public Budget getExpenseBookRemaining() {
        return expenseBook.getRemainingBudget();
    }

    @Override
    public void setExpenseBookBudget(Budget budget) {
        expenseBook.setBudget(budget);
    }

    @Override
    public void setExpenseBookCurrency(Currency dollarSign) {
        expenseBook.setCurrency(dollarSign);
    }

    @Override
    public void expenseBookExchange(ExchangeRate exchangeRate) {
        expenseBook.exchange(exchangeRate);
    }

    @Override
    public Currency getExpenseBookCurrency() {
        return expenseBook.getCurrency();
    }

    @Override
    public List<Category> getCategoryLabels() {
        return expenseBook.getCategoryLabels();
    }

    @Override
    public int getExpenseSumByCategory(String categoryName) {
        return expenseBook.getExpenseSumByCategory(categoryName);
    }

    @Override
    public int getTotalExpense() {
        return expenseBook.getTotalExpense();
    }

    @Override
    public double getExpenseSum() {
        return expenseBook.getExpenseSum();
    }

    @Override
    public HashMap<String, Double> getExpenseSumCategory() {
        return expenseBook.getExpenseSumCategory();
    }

    @Override
    public HashMap<String, Double> getExpensePercentageCategory() {
        return expenseBook.getExpensePercentageCategory();
    }

    @Override
    public double getExpenseSumOfCategory(String categoryName) {
        return expenseBook.getExpenseSumOfCategory(categoryName);
    }

    @Override
    public void sortByDescendingAmount() {
        expenseBook.sortByDescendingAmount();
        updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
    }

    @Override
    public void sortByAscendingAmount() {
        expenseBook.sortByAscendingAmount();
        updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
    }

    @Override
    public void sortByDescendingTime() {
        expenseBook.sortByDescendingTime();
        updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
    }

    @Override
    public void sortByAscendingTime() {
        expenseBook.sortByAscendingTime();
        updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
    }

}
