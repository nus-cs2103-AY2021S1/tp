package seedu.expense.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_REMARK_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_REMARK_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.expense.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.expense.logic.commands.CommandTestUtil.showExpenseAtIndex;
import static seedu.expense.testutil.TypicalExpenses.getTypicalExpenseBook;
import static seedu.expense.testutil.TypicalIndexes.INDEX_FIRST_EXPENSE;
import static seedu.expense.testutil.TypicalIndexes.INDEX_SECOND_EXPENSE;

import org.junit.jupiter.api.Test;

import seedu.expense.commons.core.Messages;
import seedu.expense.commons.core.index.Index;
import seedu.expense.model.ExpenseBook;
import seedu.expense.model.Model;
import seedu.expense.model.ModelManager;
import seedu.expense.model.UserPrefs;
import seedu.expense.model.alias.AliasMap;
import seedu.expense.model.expense.Expense;
import seedu.expense.model.expense.Remark;
import seedu.expense.testutil.ExpenseBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class RemarkCommandTest {

    private static final String REMARK_STUB = "Some remark";

    private Model model = new ModelManager(getTypicalExpenseBook(), new UserPrefs(), new AliasMap());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Expense firstExpense = model.getFilteredExpenseList().get(INDEX_FIRST_EXPENSE.getZeroBased());
        Expense editedExpense = new ExpenseBuilder(firstExpense).withRemark(REMARK_STUB).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_EXPENSE,
                new Remark(editedExpense.getRemark().value));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedExpense);

        Model expectedModel = new ModelManager(new ExpenseBook(model.getExpenseBook()),
                new UserPrefs(), new AliasMap());
        expectedModel.setExpense(firstExpense, editedExpense);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteRemarkUnfilteredList_success() {
        Expense firstExpense = model.getFilteredExpenseList().get(INDEX_FIRST_EXPENSE.getZeroBased());
        Expense editedExpense = new ExpenseBuilder(firstExpense).withRemark("").build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_EXPENSE,
                new Remark(editedExpense.getRemark().toString()));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS, editedExpense);

        Model expectedModel = new ModelManager(new ExpenseBook(model.getExpenseBook()),
                new UserPrefs(), new AliasMap());
        expectedModel.setExpense(firstExpense, editedExpense);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showExpenseAtIndex(model, INDEX_FIRST_EXPENSE);

        Expense firstExpense = model.getFilteredExpenseList().get(INDEX_FIRST_EXPENSE.getZeroBased());
        Expense editedExpense = new ExpenseBuilder(model.getFilteredExpenseList()
                .get(INDEX_FIRST_EXPENSE.getZeroBased()))
                .withRemark(REMARK_STUB).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_EXPENSE,
                new Remark(editedExpense.getRemark().value));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedExpense);

        Model expectedModel = new ModelManager(new ExpenseBook(model.getExpenseBook()),
                new UserPrefs(), new AliasMap());
        expectedModel.setExpense(firstExpense, editedExpense);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidExpenseIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExpenseList().size() + 1);
        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, new Remark(VALID_REMARK_BUS));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of expense book
     */
    @Test
    public void execute_invalidExpenseIndexFilteredList_failure() {
        showExpenseAtIndex(model, INDEX_FIRST_EXPENSE);
        Index outOfBoundIndex = INDEX_SECOND_EXPENSE;
        // ensures that outOfBoundIndex is still in bounds of expense book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getExpenseBook().getExpenseList().size());

        RemarkCommand remarkCommand = new RemarkCommand(outOfBoundIndex, new Remark(VALID_REMARK_BUS));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final RemarkCommand standardCommand = new RemarkCommand(INDEX_FIRST_EXPENSE, new Remark(VALID_REMARK_FOOD));

        // same values -> returns true
        RemarkCommand commandWithSameValues = new RemarkCommand(INDEX_FIRST_EXPENSE, new Remark(VALID_REMARK_FOOD));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_SECOND_EXPENSE, new Remark(VALID_REMARK_FOOD))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new RemarkCommand(INDEX_FIRST_EXPENSE, new Remark(VALID_REMARK_BUS))));
    }

}
