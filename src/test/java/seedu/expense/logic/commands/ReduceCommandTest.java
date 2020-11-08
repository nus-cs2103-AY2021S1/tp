package seedu.expense.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_TAG_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.expense.model.ExpenseBook.DEFAULT_TAG;
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
import seedu.expense.model.budget.UniqueCategoryBudgetList;
import seedu.expense.model.expense.Amount;
import seedu.expense.model.expense.Expense;
import seedu.expense.model.tag.Tag;
import seedu.expense.model.tag.UniqueTagList;

public class ReduceCommandTest {

    @Test
    public void constructor_nullAmount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ReduceCommand(null));
    }

    @Test
    void execute_nonNegativeAmountReducedFromModel_success() throws Exception {
        ModelStub modelStub = new ModelStub();
        Amount validAmount0 = new Amount("0");
        Amount validAmount1 = new Amount("1");
        Amount validAmount2 = new Amount("2");
        Amount validAmount3 = new Amount("3");
        modelStub.topupBudget(validAmount3);

        CommandResult commandResult0 = new ReduceCommand(validAmount0).execute(modelStub);
        assertEquals(String.format(ReduceCommand.MESSAGE_SUCCESS, DEFAULT_TAG.tagName, validAmount3),
                commandResult0.getFeedbackToUser());
        assertEquals(validAmount3, modelStub.budgets.getAmount());

        CommandResult commandResult1 = new ReduceCommand(validAmount1).execute(modelStub);
        assertEquals(String.format(ReduceCommand.MESSAGE_SUCCESS, DEFAULT_TAG.tagName, validAmount2),
                commandResult1.getFeedbackToUser());
        assertEquals(validAmount2, modelStub.budgets.getAmount());

        CommandResult commandResult2 = new ReduceCommand(validAmount2).execute(modelStub);
        assertEquals(String.format(ReduceCommand.MESSAGE_SUCCESS, DEFAULT_TAG.tagName, validAmount0),
                commandResult2.getFeedbackToUser());
        assertEquals(validAmount0, modelStub.budgets.getAmount());

        CommandResult commandResult3 = new ReduceCommand(validAmount3).execute(modelStub);
        assertEquals(String.format(ReduceCommand.MESSAGE_INSUFFICIENT_BUDGET, DEFAULT_TAG.tagName),
                commandResult3.getFeedbackToUser());
        assertEquals(validAmount0, modelStub.budgets.getAmount());
    }

    @Test
    void execute_negativeAmountReducedFromModel_throwsCommandException() {
        ModelStub modelStub = new ModelStub();
        assertThrows(CommandException.class, () -> new ReduceCommand(new Amount("-0.01")).execute(modelStub));
    }

    @Test
    void execute_validAmountReducedFromCategoryBudget_success() throws Exception {
        ModelStub modelStub = new ModelStub();
        Tag validTag = new Tag(VALID_TAG_FOOD);
        Amount validAmount1 = new Amount("1");
        Amount validAmount2 = new Amount("2");
        modelStub.addCategory(validTag);
        modelStub.topupCategoryBudget(validTag, validAmount2);
        CommandResult commandResult = new ReduceCommand(validAmount1, validTag).execute(modelStub);
        assertEquals(String.format(ReduceCommand.MESSAGE_SUCCESS, VALID_TAG_FOOD, validAmount1),
                commandResult.getFeedbackToUser());
        assertEquals(validAmount1, modelStub.budgets.getCategoryBudget(validTag).getAmount());
    }

    @Test
    void execute_validAmountReducedFromNonExistentCategoryBudget_throwsCommandException() {
        ModelStub modelStub = new ModelStub();
        assertThrows(CommandException.class, () -> new ReduceCommand(new Amount("1"),
                new Tag(VALID_TAG_FOOD)).execute(modelStub));
    }

    @Test
    void equals() {
        Amount toReduceOne = new Amount("1");
        Amount toReduceTwo = new Amount("2");
        ReduceCommand reduceCommandOne = new ReduceCommand(toReduceOne);
        ReduceCommand reduceCommandTwo = new ReduceCommand(toReduceTwo);

        Tag foodTag = new Tag(VALID_TAG_FOOD);
        Tag transportTag = new Tag(VALID_TAG_TRANSPORT);
        ReduceCommand reduceFood = new ReduceCommand(toReduceOne, foodTag);
        ReduceCommand reduceTransport = new ReduceCommand(toReduceOne, transportTag);

        // same object -> returns true
        assertTrue(reduceCommandOne.equals(reduceCommandOne));

        // same values -> returns true
        ReduceCommand reduceOneCopy = new ReduceCommand(toReduceOne);
        assertTrue(reduceCommandOne.equals(reduceOneCopy));

        // different types -> returns false
        assertFalse(reduceCommandOne.equals(1));

        // null -> returns false
        assertFalse(reduceCommandOne.equals(null));

        // different amount -> returns false
        assertFalse(reduceCommandOne.equals(reduceCommandTwo));

        // different categories -> returns false
        assertFalse(reduceFood.equals(reduceTransport));
    }

    /**
     * A Model stub with a budget that can be topped up.
     */
    private class ModelStub implements Model {

        final UniqueCategoryBudgetList budgets;
        final UniqueTagList tags;

        ModelStub() {
            budgets = new UniqueCategoryBudgetList();
            tags = new UniqueTagList();
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
            return budgets;
        }

        @Override
        public CategoryBudget getCategoryBudget(Tag category) {
            return budgets.getCategoryBudget(category);
        }

        @Override
        public void topupBudget(Amount amount) {
            budgets.topupBudget(amount);
        }

        @Override
        public void topupCategoryBudget(Tag category, Amount amount) {
            budgets.topupCategoryBudget(category, amount);
        }

        @Override
        public boolean categoryBudgetHasAmount(Tag category, Amount amount) {
            return budgets.categoryBudgetHasAmount(category, amount);
        }

        @Override
        public void reduceCategoryBudget(Tag category, Amount amount) {
            budgets.reduceCategoryBudget(category, amount);
        }

        @Override
        public void addCategory(Tag tag) {
            tags.add(tag);
            budgets.add(new CategoryBudget(tag));
        }

        @Override
        public void deleteCategory(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCategory(Tag toCheck) {
            return tags.contains(toCheck);
        }

        @Override
        public void switchCategory(Tag category) {
            throw new AssertionError("This method should not be called.");
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
