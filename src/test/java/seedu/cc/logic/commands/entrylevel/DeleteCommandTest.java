package seedu.cc.logic.commands.entrylevel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cc.testutil.Assert.assertThrows;
import static seedu.cc.testutil.TypicalEntries.BUY_FLOWER_POTS;
import static seedu.cc.testutil.TypicalEntries.SELL_FLOWER_POTS;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static seedu.cc.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;
import static seedu.cc.testutil.TypicalIndexes.INDEX_TENTH_ENTRY;

import org.junit.jupiter.api.Test;

import seedu.cc.commons.core.category.Category;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.account.ActiveAccount;
import seedu.cc.model.account.ActiveAccountManager;
import seedu.cc.testutil.ActiveAccountStub;
import seedu.cc.testutil.ModelStub;
import seedu.cc.testutil.TypicalEntries;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private final Model modelStub = new ModelStub();
    private final Category expense = new Category("expense");
    private final Category revenue = new Category("revenue");
    private final ActiveAccount activeAccountStub = new ActiveAccountStub();
    private final ActiveAccount activeAccount = new ActiveAccountManager(TypicalEntries.getTypicalAccount());

    @Test
    public void constructor_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteCommand(INDEX_FIRST_ENTRY, expense)
                .execute(modelStub, activeAccountStub));
    }

    @Test
    public void execute_validDeleteExpense_success() throws CommandException {
        CommandResult commandResult = new DeleteCommand(INDEX_FIRST_ENTRY, expense).execute(modelStub, activeAccount);
        assertEquals(String.format(DeleteCommand.MESSAGE_DELETE_ENTRY_SUCCESS, BUY_FLOWER_POTS),
                commandResult.getFeedbackToUser());
        assertFalse(activeAccount.hasExpense(BUY_FLOWER_POTS));
    }

    @Test
    public void execute_validDeleteRevenue_success() throws CommandException {
        CommandResult commandResult = new DeleteCommand(INDEX_FIRST_ENTRY, revenue).execute(modelStub, activeAccount);
        assertEquals(String.format(DeleteCommand.MESSAGE_DELETE_ENTRY_SUCCESS, SELL_FLOWER_POTS),
                commandResult.getFeedbackToUser());
        assertFalse(activeAccount.hasRevenue(SELL_FLOWER_POTS));
    }

    @Test
    public void execute_invalidIndexExpense_failure() {
        assertThrows(CommandException.class, () -> new DeleteCommand(INDEX_TENTH_ENTRY, expense)
                .execute(modelStub, activeAccount));
    }

    @Test
    public void execute_invalidIndexRevenue_failure() {
        assertThrows(CommandException.class, () -> new DeleteCommand(INDEX_TENTH_ENTRY, revenue)
                .execute(modelStub, activeAccount));
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_ENTRY, expense);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_ENTRY, revenue);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_ENTRY, expense);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}

