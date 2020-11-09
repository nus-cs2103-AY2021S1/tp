package ay2021s1_cs2103_w16_3.finesse.model;

import static ay2021s1_cs2103_w16_3.finesse.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import ay2021s1_cs2103_w16_3.finesse.commons.core.GuiSettings;
import ay2021s1_cs2103_w16_3.finesse.commons.core.LogsCenter;
import ay2021s1_cs2103_w16_3.finesse.model.bookmark.BookmarkExpense;
import ay2021s1_cs2103_w16_3.finesse.model.bookmark.BookmarkIncome;
import ay2021s1_cs2103_w16_3.finesse.model.budget.MonthlyBudget;
import ay2021s1_cs2103_w16_3.finesse.model.command.history.CommandHistory;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Expense;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Income;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the finance tracker data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private static final int COMMAND_HISTORY_SIZE = 50;

    private final FinanceTracker financeTracker;
    private final UserPrefs userPrefs;
    private final CommandHistory commandHistory;
    private final FilteredList<Transaction> filteredTransactions;
    private final FilteredList<Transaction> filteredExpenses;
    private final FilteredList<Transaction> filteredIncomes;
    private final FilteredList<BookmarkExpense> filteredBookmarkExpenses;
    private final FilteredList<BookmarkIncome> filteredBookmarkIncomes;
    private final ObservableList<Expense> castFilteredExpenses;
    private final ObservableList<Income> castFilteredIncomes;
    private final MonthlyBudget monthlyBudget;

    /**
     * Initializes a ModelManager with the given financeTracker and userPrefs.
     */
    public ModelManager(ReadOnlyFinanceTracker financeTracker, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(financeTracker, userPrefs);

        logger.fine("Initializing with finance tracker: " + financeTracker + " and user prefs " + userPrefs);

        this.financeTracker = new FinanceTracker(financeTracker);
        this.userPrefs = new UserPrefs(userPrefs);
        commandHistory = new CommandHistory(COMMAND_HISTORY_SIZE);
        filteredTransactions = new FilteredList<>(this.financeTracker.getTransactionList());
        filteredExpenses = new FilteredList<>(this.financeTracker.getTransactionList(), PREDICATE_SHOW_ALL_EXPENSES);
        filteredIncomes = new FilteredList<>(this.financeTracker.getTransactionList(), PREDICATE_SHOW_ALL_INCOMES);
        filteredBookmarkExpenses = new FilteredList<>(this.financeTracker.getBookmarkExpenseList());
        filteredBookmarkIncomes = new FilteredList<>(this.financeTracker.getBookmarkIncomeList());
        castFilteredExpenses = FXCollections.observableArrayList(castFilteredList(filteredExpenses, Expense.class));
        castFilteredIncomes = FXCollections.observableArrayList(castFilteredList(filteredIncomes, Income.class));
        monthlyBudget = this.financeTracker.getMonthlyBudget();
    }

    public ModelManager() {
        this(new FinanceTracker(), new UserPrefs());
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
    public Path getFinanceTrackerFilePath() {
        return userPrefs.getFinanceTrackerFilePath();
    }

    @Override
    public void setFinanceTrackerFilePath(Path financeTrackerFilePath) {
        requireNonNull(financeTrackerFilePath);
        userPrefs.setFinanceTrackerFilePath(financeTrackerFilePath);
    }

    //=========== Command History ================================================================================

    @Override
    public CommandHistory getCommandHistory() {
        return commandHistory;
    }


    //=========== FinanceTracker ================================================================================

    @Override
    public void setFinanceTracker(ReadOnlyFinanceTracker financeTracker) {
        this.financeTracker.resetData(financeTracker);
        refreshTransactionLists();
    }

    @Override
    public ReadOnlyFinanceTracker getFinanceTracker() {
        return financeTracker;
    }

    @Override
    public void deleteTransaction(Transaction target) {
        financeTracker.removeTransaction(target);
        refreshTransactionLists();
    }

    @Override
    public void addExpense(Expense expense) {
        financeTracker.addTransaction(expense);
        updateFilteredExpenseList(PREDICATE_SHOW_ALL_TRANSACTIONS);
    }

    @Override
    public void addIncome(Income income) {
        financeTracker.addTransaction(income);
        updateFilteredIncomeList(PREDICATE_SHOW_ALL_TRANSACTIONS);
    }

    @Override
    public void setTransaction(Transaction target, Transaction editedTransaction) {
        requireAllNonNull(target, editedTransaction);

        financeTracker.setTransaction(target, editedTransaction);
        refreshTransactionLists();
    }

    //=========== Bookmark Transaction ================================================================================

    @Override
    public void addBookmarkExpense(BookmarkExpense bookmarkExpense) {
        financeTracker.addBookmarkExpense(bookmarkExpense);
    }

    @Override
    public void addBookmarkIncome(BookmarkIncome bookmarkIncome) {
        financeTracker.addBookmarkIncome(bookmarkIncome);
    }

    @Override
    public void deleteBookmarkExpense(BookmarkExpense bookmarkExpense) {
        financeTracker.removeBookmarkExpense(bookmarkExpense);
    }

    @Override
    public void deleteBookmarkIncome(BookmarkIncome bookmarkIncome) {
        financeTracker.removeBookmarkIncome(bookmarkIncome);
    }

    @Override
    public void setBookmarkExpense(BookmarkExpense target, BookmarkExpense editedBookmarkExpense) {
        requireAllNonNull(target, editedBookmarkExpense);

        financeTracker.setBookmarkExpense(target, editedBookmarkExpense);
    }

    @Override
    public void setBookmarkIncome(BookmarkIncome target, BookmarkIncome editedBookmarkIncome) {
        requireAllNonNull(target, editedBookmarkIncome);

        financeTracker.setBookmarkIncome(target, editedBookmarkIncome);
    }

    @Override
    public void updateFilteredBookmarkExpenseList(Predicate<BookmarkExpense> predicate) {
        requireNonNull(predicate);

        filteredBookmarkExpenses.setPredicate(predicate);
    }

    @Override
    public void updateFilteredBookmarkIncomeList(Predicate<BookmarkIncome> predicate) {
        requireNonNull(predicate);

        filteredBookmarkIncomes.setPredicate(predicate);
    }

    //=========== Budget ===========================================================================================
    @Override
    public MonthlyBudget getMonthlyBudget() {
        return monthlyBudget;
    }

    @Override
    public void setExpenseLimit(Amount limit) {
        monthlyBudget.setMonthlyExpenseLimit(limit);
        calculateBudgetInfo();
    }

    @Override
    public void setSavingsGoal(Amount goal) {
        monthlyBudget.setMonthlySavingsGoal(goal);
        calculateBudgetInfo();
    }

    @Override
    public void calculateBudgetInfo() {
        financeTracker.calculateBudgetInfo();
    }

    //=========== Filtered Transaction List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Transaction} backed by the internal transaction list of
     * {@code versionedFinanceTracker}.
     */
    @Override
    public ObservableList<Transaction> getFilteredTransactionList() {
        return filteredTransactions;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Expense} backed by the internal expense list of
     * {@code versionedFinanceTracker}.
     */
    @Override
    public ObservableList<Expense> getFilteredExpenseList() {
        return FXCollections.unmodifiableObservableList(castFilteredExpenses);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Income} backed by the internal income list of
     * {@code versionedFinanceTracker}.
     */
    @Override
    public ObservableList<Income> getFilteredIncomeList() {
        return FXCollections.unmodifiableObservableList(castFilteredIncomes);
    }

    /**
     * Returns an unmodifiable view of the list of {@code BookmarkExpense} backed by the internal bookmark expense
     * list of {@code versionedFinanceTracker}.
     */
    @Override
    public ObservableList<BookmarkExpense> getFilteredBookmarkExpenseList() {
        return filteredBookmarkExpenses;
    }

    /**
     * Returns an unmodifiable view of the list of {@code BookmarkIncome} backed by the internal bookmark income
     * list of {@code versionedFinanceTracker}.
     */
    @Override
    public ObservableList<BookmarkIncome> getFilteredBookmarkIncomeList() {
        return filteredBookmarkIncomes;
    }

    @Override
    public void updateFilteredTransactionList(Predicate<Transaction> predicate) {
        requireNonNull(predicate);
        filteredTransactions.setPredicate(predicate);
    }

    @Override
    public void updateFilteredTransactionList(List<Predicate<Transaction>> predicates) {
        requireNonNull(predicates);
        Predicate<Transaction> combinedPredicate = predicates.stream()
                .reduce(PREDICATE_SHOW_ALL_TRANSACTIONS, Predicate::and);
        updateFilteredTransactionList(combinedPredicate);
    }

    @Override
    public void updateFilteredExpenseList(Predicate<Transaction> predicate) {
        requireNonNull(predicate);
        filteredExpenses.setPredicate(t -> predicate.test(t) && PREDICATE_SHOW_ALL_EXPENSES.test(t));
        refreshTransactionLists();
    }

    @Override
    public void updateFilteredExpenseList(List<Predicate<Transaction>> predicates) {
        requireNonNull(predicates);
        Predicate<Transaction> combinedPredicate = predicates.stream()
                .reduce(PREDICATE_SHOW_ALL_EXPENSES, Predicate::and);
        updateFilteredExpenseList(combinedPredicate);
    }

    @Override
    public void updateFilteredIncomeList(Predicate<Transaction> predicate) {
        requireNonNull(predicate);
        filteredIncomes.setPredicate(t -> predicate.test(t) && PREDICATE_SHOW_ALL_INCOMES.test(t));
        refreshTransactionLists();
    }

    @Override
    public void updateFilteredIncomeList(List<Predicate<Transaction>> predicates) {
        requireNonNull(predicates);
        Predicate<Transaction> combinedPredicate = predicates.stream()
                .reduce(PREDICATE_SHOW_ALL_INCOMES, Predicate::and);
        updateFilteredIncomeList(combinedPredicate);
    }

    /**
     * Casts a FilteredList into a List of the filtered subtype.
     * Precondition: The given FilteredList must be either the expense list or income list.
     * This guarantees that the elements of the FilteredList can successfully be cast to the target type.
     *
     * @param list The FilteredList to be cast.
     * @param toCast The class of the target type to be cast. Required as casting to generic types is not allowed.
     * @param <T> The type of transaction. Can be either Expense or Income, but not Transaction itself.
     * @return A List whose elements are equal to the current state of the FilteredList.
     */
    private <T extends Transaction> List<T> castFilteredList(FilteredList<Transaction> list, Class<T> toCast) {
        assert list == filteredExpenses || list == filteredIncomes
                : "Casting can only be performed on expense or income list";
        assert toCast != Transaction.class : "Casting can only be to Expense or Income, not Transaction";
        return list.stream().map(toCast::cast).collect(Collectors.toUnmodifiableList());
    }

    /**
     * Refreshes the typecasted ObservableLists used by other components.
     * To be called anytime the expense or income list updates.
     */
    private void refreshTransactionLists() {
        castFilteredExpenses.clear();
        castFilteredExpenses.addAll(castFilteredList(filteredExpenses, Expense.class));
        castFilteredIncomes.clear();
        castFilteredIncomes.addAll(castFilteredList(filteredIncomes, Income.class));
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
        return financeTracker.equals(other.financeTracker)
                && userPrefs.equals(other.userPrefs)
                && commandHistory.equals(other.commandHistory)
                && filteredTransactions.equals(other.filteredTransactions)
                && filteredExpenses.equals(other.filteredExpenses)
                && filteredIncomes.equals(other.filteredIncomes);
    }
}
