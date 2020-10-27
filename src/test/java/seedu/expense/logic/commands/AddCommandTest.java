package seedu.expense.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.expense.model.ExpenseBook.DEFAULT_TAG;
import static seedu.expense.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.expense.commons.core.GuiSettings;
import seedu.expense.logic.commands.exceptions.CommandException;
import seedu.expense.model.ExpenseBook;
import seedu.expense.model.Model;
import seedu.expense.model.ReadOnlyExpenseBook;
import seedu.expense.model.ReadOnlyUserPrefs;
import seedu.expense.model.Statistics;
import seedu.expense.model.alias.AliasEntry;
import seedu.expense.model.alias.AliasMap;
import seedu.expense.model.budget.Budget;
import seedu.expense.model.budget.CategoryBudget;
import seedu.expense.model.expense.Amount;
import seedu.expense.model.expense.Expense;
import seedu.expense.model.tag.Tag;
import seedu.expense.testutil.ExpenseBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_expenseAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingExpenseAdded modelStub = new ModelStubAcceptingExpenseAdded();
        Expense validExpense = new ExpenseBuilder().build();

        CommandResult commandResult = new AddCommand(validExpense).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validExpense), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validExpense), modelStub.expensesAdded);
    }

    @Test
    public void execute_duplicateExpense_throwsCommandException() {
        Expense validExpense = new ExpenseBuilder().build();
        AddCommand addCommand = new AddCommand(validExpense);
        ModelStub modelStub = new ModelStubWithExpense(validExpense);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_EXPENSE, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Expense alice = new ExpenseBuilder().withDescription("Alice").build();
        Expense bob = new ExpenseBuilder().withDescription("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different expense -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getExpenseBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpenseBookFilePath(Path expenseBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExpense(Expense expense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpenseBook(ReadOnlyExpenseBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyExpenseBook getExpenseBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Statistics getStatistics() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExpense(Expense expense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExpense(Expense target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExpense(Expense target, Expense editedExpense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Expense> getFilteredExpenseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredExpenseList(Predicate<Expense> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBudgetList(Predicate<CategoryBudget> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateExpenseBookCategory(Tag category) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Budget getTotalBudget() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void topupBudget(Amount amount) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void topupCategoryBudget(Tag category, Amount amount) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCategory(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCategory(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCategory(Tag toCheck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAlias(AliasEntry prev, AliasEntry curr) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortExpenseList(Comparator<Expense> expenseComparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchCategory(Tag category) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAliasMap(AliasMap map) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAlias(AliasEntry entry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public AliasMap getAliasMap() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAlias(AliasEntry entry) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAlias(AliasEntry entry) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single expense.
     */
    private class ModelStubWithExpense extends ModelStub {
        private final Expense expense;

        ModelStubWithExpense(Expense expense) {
            requireNonNull(expense);
            this.expense = expense;
        }

        @Override
        public boolean hasExpense(Expense expense) {
            requireNonNull(expense);
            return this.expense.isSameExpense(expense);
        }
    }

    /**
     * A Model stub that always accept the expense being added.
     */
    private class ModelStubAcceptingExpenseAdded extends ModelStub {
        final ArrayList<Expense> expensesAdded = new ArrayList<>();
        final Tag tag = DEFAULT_TAG;

        @Override
        public boolean hasExpense(Expense expense) {
            requireNonNull(expense);
            return expensesAdded.stream().anyMatch(expense::isSameExpense);
        }

        @Override
        public void addExpense(Expense expense) {
            requireNonNull(expense);
            expensesAdded.add(expense);
        }

        @Override
        public boolean hasCategory(Tag toCheck) {
            return toCheck.equals(tag);
        }

        @Override
        public ReadOnlyExpenseBook getExpenseBook() {
            return new ExpenseBook();
        }
    }

}
