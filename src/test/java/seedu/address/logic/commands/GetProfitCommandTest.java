package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.account.ActiveAccount;
import seedu.address.model.account.ActiveAccountManager;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.TypicalEntries;

class GetProfitCommandTest {

    private final Model modelStub = new ModelStub();
    private final ActiveAccount activeAccount = new ActiveAccountManager(TypicalEntries.getTypicalAccount());

    @Test
    void execute_validAccount_success() {
        CommandResult commandResult = new GetProfitCommand().execute(modelStub, activeAccount);
        Double profit = activeAccount.getProfits();
        assertEquals(GetProfitCommand.MESSAGE_SUCCESS + String.format("%.2f", profit),
            commandResult.getFeedbackToUser());
    }
}
