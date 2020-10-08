package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.budget.Budget;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Expense;

public class TopupCommandTest {

    @Test
    public void constructor_nullAmount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TopupCommand(null));
    }

    @Test
    void execute_amountAddedToModel_success() {
        ModelStub modelStub = new ModelStub();
        Amount validAmount = new Amount("1");
        CommandResult commandResult = new TopupCommand(validAmount).execute(modelStub);
        assertEquals(String.format(TopupCommand.MESSAGE_SUCCESS, validAmount.asDouble()),
                commandResult.getFeedbackToUser());
        assertEquals(validAmount, modelStub.budget.getAmount());
    }

    @Test
    void equals() {
        Amount toAddOne = new Amount("1");
        Amount toAddTwo = new Amount("2");
        TopupCommand topupCommandOne = new TopupCommand(toAddOne);
        TopupCommand topupCommandTwo = new TopupCommand(toAddTwo);

        // same object -> returns true
        assertTrue(topupCommandOne.equals(topupCommandOne));

        // same values -> returns true
        TopupCommand topupOneCopy = new TopupCommand(toAddOne);
        assertTrue(topupCommandOne.equals(topupOneCopy));

        // different types -> returns false
        assertFalse(topupCommandOne.equals(1));

        // null -> returns false
        assertFalse(topupCommandOne.equals(null));

        // different amount -> returns false
        assertFalse(topupCommandOne.equals(topupCommandTwo));
    }

    /**
     * A Model stub with a budget that can be topped up.
     */
    private class ModelStub implements Model {

        final Budget budget;

        ModelStub() {
            budget = new Budget();
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExpense(Expense expense) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
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
        public Budget getBudget() {
            return budget;
        }

        @Override
        public void topupBudget(Amount amount) {
            budget.topupBudget(amount);
        }
    }
}
