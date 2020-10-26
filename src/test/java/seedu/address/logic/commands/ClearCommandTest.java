package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEntries.BUY_FLOWER_POTS;
import static seedu.address.testutil.TypicalEntries.SELL_FLOWER_POTS;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.category.Category;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.account.ActiveAccount;
import seedu.address.model.account.ActiveAccountManager;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.TypicalEntries;

/**
 * Contains integration and unit tests for {@code ClearCommand}.
 */
public class ClearCommandTest {

    private final Model modelStub = new ModelStub();
    private final Category expense = new Category("expense");
    private final Category revenue = new Category("revenue");
    private final ActiveAccount activeAccount = new ActiveAccountManager(TypicalEntries.getTypicalAccount());

    @Test
    public void execute_validClearExpense_success() throws CommandException {
        CommandResult commandResult = new ClearCommand(expense).execute(modelStub, activeAccount);
        assertEquals(String.format(ClearCommand.MESSAGE_CLEAR_ENTRY_SUCCESS, expense),
                commandResult.getFeedbackToUser());
        assertFalse(activeAccount.hasExpense(BUY_FLOWER_POTS));
        assertTrue(activeAccount.getFilteredExpenseList().isEmpty());
    }

    @Test
    public void execute_validClearRevenue_success() throws CommandException {
        CommandResult commandResult = new ClearCommand(revenue).execute(modelStub, activeAccount);
        assertEquals(String.format(ClearCommand.MESSAGE_CLEAR_ENTRY_SUCCESS, revenue),
                commandResult.getFeedbackToUser());
        assertFalse(activeAccount.hasRevenue(SELL_FLOWER_POTS));
        assertTrue(activeAccount.getFilteredRevenueList().isEmpty());
    }

    @Test
    public void execute_validClearExpense_failure() {
        assertTrue(activeAccount.hasExpense(BUY_FLOWER_POTS));
        assertFalse(activeAccount.getFilteredExpenseList().isEmpty());
    }

    @Test
    public void execute_validClearRevenue_failure() {
        assertTrue(activeAccount.hasRevenue(SELL_FLOWER_POTS));
        assertFalse(activeAccount.getFilteredRevenueList().isEmpty());
    }

}
