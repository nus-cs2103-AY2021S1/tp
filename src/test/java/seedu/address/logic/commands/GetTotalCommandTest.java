package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.category.Category;
import seedu.address.model.Model;
import seedu.address.model.account.ActiveAccount;
import seedu.address.model.account.ActiveAccountManager;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.TypicalEntries;

class GetTotalCommandTest {

    private final Category expense = new Category("expense");
    private final Category revenue = new Category("revenue");
    private final Model modelStub = new ModelStub();
    private final ActiveAccount activeAccount = new ActiveAccountManager(TypicalEntries.getTypicalAccount());


    @Test
    public void constructor_nullCategory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GetTotalCommand(null));
    }

    @Test
    void execute_totalExpenses_success() {
        CommandResult commandResult = new GetTotalCommand(expense).execute(modelStub, activeAccount);
        Double totalExpenses = activeAccount.getTotalExpenses();

        assertEquals(String.format(GetTotalCommand.MESSAGE_SUCCESS + totalExpenses, expense),
            commandResult.getFeedbackToUser());
    }

    @Test
    void execute_totalRevenue_success() {
        CommandResult commandResult = new GetTotalCommand(revenue).execute(modelStub, activeAccount);
        Double totalRevenue = activeAccount.getTotalRevenue();

        assertEquals(String.format(GetTotalCommand.MESSAGE_SUCCESS + totalRevenue, revenue),
            commandResult.getFeedbackToUser());
    }
}
