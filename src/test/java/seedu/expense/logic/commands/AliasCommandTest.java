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

public class AliasCommandTest {

    @Test
    public void constructor_nullStrings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AliasCommand(null, null));
    }

    @Test
    public void execute_nonAlphabetical_throwsIllegalArgumentException() {
        ModelStubWithAliasMap modelStub = new ModelStubWithAliasMap();
        assertThrows(CommandException.class, () -> new AliasCommand("get", "-$").execute(modelStub));
    }

    @Test
    public void execute_fromDefaultInsteadOfCustomisedAlias_throwCommandException() throws CommandException {
        ModelStubWithAliasMap modelStub = new ModelStubWithAliasMap();
        assertThrows(CommandException.class, () -> new AliasCommand("find", "f").execute(modelStub));
    }

    @Test
    public void execute_fromDefaultWithNoCustomisedAliasYet_leestBecomesAliasForListCommand()
            throws CommandException {
        ModelStubWithAliasMap modelStub = new ModelStubWithAliasMap();
        new AliasCommand("list", "leest").execute(modelStub);
        assertTrue(modelStub.getAliasMap().hasAlias(new AliasEntry("leest", "list")));
    }

    @Test
    public void equals_sameStrings_true() {
        assertEquals(new AliasCommand("a", "b"), new AliasCommand("a", "b"));
    }

    @Test
    public void execute_duplicateKeywords_throwIllegalArgumentException() {
        ModelStubWithAliasMap modelStub = new ModelStubWithAliasMap();
        assertThrows(CommandException.class, () ->
                new AliasCommand("get", "switch").execute(modelStub)
        );
    }

    @Test
    public void execute_removeSingleAlias_revertBack() throws CommandException {
        ModelStubWithAliasMap modelStub = new ModelStubWithAliasMap();
        assertEquals(
                new CommandResult("Removed alias. [get] is no longer alias for [find]. "),
                new AliasCommand("get", "find").execute(modelStub)
        );
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
        public CategoryBudget getCategoryBudget(Tag category) throws CategoryBudgetNotFoundException {
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
