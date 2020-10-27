package seedu.expense.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_TAG_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.expense.testutil.Assert.assertThrows;
import static seedu.expense.testutil.TypicalExpenses.getTypicalExpenseBook;

import java.nio.file.Path;
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

/**
 * Contains integration tests (interaction with the Model) for {@code SwitchCommand}.
 */
class SwitchCommandTest {

    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SwitchCommand(null));
    }

    @Test
    void execute_noMatchingKeywords_noCategoryFound() {
        Tag foodTag = new Tag("Potato");
        SwitchCommand command = new SwitchCommand(foodTag);
        ModelStub modelStub = new ModelStub();
        assertThrows(CommandException.class, String.format(SwitchCommand.MESSAGE_INVALID_CATEGORY,
            foodTag), () -> command.execute(modelStub));
    }

    @Test
    public void equals() {
        Tag foodTag = new Tag(VALID_TAG_FOOD);
        Tag transportTag = new Tag(VALID_TAG_TRANSPORT);

        SwitchCommand switchFirstCommand = new SwitchCommand(foodTag);
        SwitchCommand switchSecondCommand = new SwitchCommand(transportTag);

        // same object -> returns true
        assertTrue(switchFirstCommand.equals(switchFirstCommand));

        // same values -> returns true
        SwitchCommand switchFirstCommandCopy = new SwitchCommand(foodTag);
        assertTrue(switchFirstCommand.equals(switchFirstCommandCopy));

        // different types -> returns false
        assertFalse(switchFirstCommand.equals(1));

        // null -> returns false
        assertFalse(switchFirstCommand.equals(null));

        // different tag -> returns false
        assertFalse(switchFirstCommand.equals(switchSecondCommand));
    }

    /**
     * A Model stub with a budget that can be topped up.
     */
    private class ModelStub implements Model {

        final ExpenseBook expenseBook;

        ModelStub() {
            expenseBook = new ExpenseBook(getTypicalExpenseBook());
        }

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
            requireNonNull(predicate);
            expenseBook.updateFilteredExpenses(predicate);
        }

        @Override
        public void updateFilteredBudgetList(Predicate<CategoryBudget> predicate) {
            requireNonNull(predicate);
            expenseBook.updateFilteredBudgets(predicate);
        }

        @Override
        public void updateExpenseBookCategory(Tag category) {
            requireNonNull(category);

            if (category.equals(ExpenseBook.DEFAULT_TAG)) {
                updateFilteredBudgetList(PREDICATE_SHOW_ALL_BUDGETS);
                updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
            } else {
                updateFilteredBudgetList(budget -> budget.getTag().equals(category));
                updateFilteredExpenseList(expense -> expense.getTag().equals(category));
            }
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
            return expenseBook.containsCategory(toCheck);
        }

        @Override
        public void switchCategory(Tag category) {
            requireNonNull(category);
            if (hasCategory(category)) {
                updateExpenseBookCategory(category);
            }
        }

        @Override
        public void setAlias(AliasEntry prev, AliasEntry next) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortExpenseList(Comparator<Expense> expenseComparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAliasMap(AliasMap map) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public AliasMap getAliasMap() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAlias(AliasEntry entry) {
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
}
