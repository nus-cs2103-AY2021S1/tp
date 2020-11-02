package seedu.expense.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.expense.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.expense.commons.core.GuiSettings;
import seedu.expense.logic.commands.exceptions.CommandException;
import seedu.expense.model.Model;
import seedu.expense.model.ReadOnlyExpenseBook;
import seedu.expense.model.ReadOnlyUserPrefs;
import seedu.expense.model.Statistics;
import seedu.expense.model.alias.AliasEntry;
import seedu.expense.model.alias.AliasMap;
import seedu.expense.model.budget.Budget;
import seedu.expense.model.budget.CategoryBudget;
import seedu.expense.model.budget.exceptions.CategoryBudgetNotFoundException;
import seedu.expense.model.expense.Amount;
import seedu.expense.model.expense.Expense;
import seedu.expense.model.tag.Tag;

public class ResetAliasCommandTest {

    @Test
    public void execute_nullObject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ResetAliasCommand().execute(null));
    }

    @Test
    public void execute_emptyAliasMap_throwsCommandException() {
        ModelStubWithAliasMap modelStub = new ModelStubWithAliasMap();
        modelStub.getAliasMap().removeAllAliases();
        assertThrows(CommandException.class, () -> new ResetAliasCommand().execute(modelStub));
    }

    @Test
    public void execute_nonEmptyAliasMap_aliasMapBecomesEmpty() throws CommandException {
        ModelStubWithAliasMap modelStub = new ModelStubWithAliasMap();
        new ResetAliasCommand().execute(modelStub);
        assertTrue(modelStub.getAliasMap().isEmpty());
    }

    @Test
    public void equals_anyTwoResetAliasCommand_true() {
        assertEquals(new ResetAliasCommand(), new ResetAliasCommand());
    }

    private class ModelStubWithAliasMap implements Model {
        private AliasMap am;

        ModelStubWithAliasMap() {
            am = new AliasMap();
            am.addAlias(new AliasEntry("get", "find"));
            am.addAlias(new AliasEntry("d", "delete"));
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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public CategoryBudget getCategoryBudget(Tag category) throws CategoryBudgetNotFoundException {
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
            this.am.setAlias(prev, curr);
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
            this.am = new AliasMap(map);
        }

        @Override
        public void addAlias(AliasEntry entry) {
            this.am.addAlias(entry);
        }

        @Override
        public AliasMap getAliasMap() {
            return this.am;
        }

        @Override
        public boolean hasAlias(AliasEntry entry) {
            return this.am.hasAlias(entry);
        }

        @Override
        public void deleteAlias(AliasEntry entry) {
            this.am.removeAlias(entry);
        }
    }

}
