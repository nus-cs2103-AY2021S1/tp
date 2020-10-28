package seedu.expense.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

class DeleteCategoryCommandTest {

    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteCategoryCommand(null));
    }

    @Test
    public void execute_categoryAcceptedByModel_deleteSuccessful() throws Exception {
        DeleteCategoryCommandTest.ModelStubAcceptingTagDeleted modelStub =
            new DeleteCategoryCommandTest.ModelStubAcceptingTagDeleted();
        Tag validTag = new Tag("Valid");

        CommandResult commandResult = new DeleteCategoryCommand(validTag).execute(modelStub);

        assertEquals(String.format(DeleteCategoryCommand.MESSAGE_SUCCESS, validTag), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(), modelStub.tags);
    }

    @Test
    public void execute_invalidTag_throwsCommandException() {
        Tag validTag = new Tag("Valid");
        Tag invalidTag = new Tag("Invalid");
        DeleteCategoryCommand deleteCategoryCommand = new DeleteCategoryCommand(invalidTag);
        DeleteCategoryCommandTest.ModelStub modelStub = new DeleteCategoryCommandTest.ModelStubWithTag(validTag);

        assertThrows(CommandException.class,
            DeleteCategoryCommand.MESSAGE_INVALID_CATEGORY, () -> deleteCategoryCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Tag aTag = new Tag("A");
        Tag bTag = new Tag("B");
        DeleteCategoryCommand deleteACommand = new DeleteCategoryCommand(aTag);
        DeleteCategoryCommand deleteBCommand = new DeleteCategoryCommand(bTag);

        // same object -> returns true
        assertTrue(deleteACommand.equals(deleteACommand));

        // same values -> returns true
        DeleteCategoryCommand deleteACopy = new DeleteCategoryCommand(aTag);
        assertTrue(deleteACommand.equals(deleteACopy));

        // different types -> returns false
        assertFalse(deleteACommand.equals(1));

        // null -> returns false
        assertFalse(deleteACommand.equals(null));

        // different values -> returns false
        assertFalse(deleteACommand.equals(deleteBCommand));
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
     * A Model stub that contains a single tag.
     */
    private class ModelStubWithTag extends DeleteCategoryCommandTest.ModelStub {
        private final ArrayList<Tag> tags = new ArrayList<>();

        ModelStubWithTag(Tag tag) {
            requireNonNull(tag);
            this.tags.add(tag);
        }

        @Override
        public boolean hasCategory(Tag toCheck) {
            return this.tags.contains(toCheck);
        }
    }


    /**
     * A Model stub that always accept the expense being deleted.
     */
    private class ModelStubAcceptingTagDeleted extends DeleteCategoryCommandTest.ModelStub {
        final ArrayList<Tag> tags = new ArrayList<>();

        ModelStubAcceptingTagDeleted() {
            tags.add(new Tag("Valid"));
        }

        @Override
        public boolean hasCategory(Tag toCheck) {
            requireNonNull(toCheck);
            return tags.stream().anyMatch(toCheck::equals);
        }

        @Override
        public void deleteCategory(Tag tag) {
            requireNonNull(tag);
            tags.remove(tag);
        }

        @Override
        public ReadOnlyExpenseBook getExpenseBook() {
            return new ExpenseBook();
        }
    }
}
