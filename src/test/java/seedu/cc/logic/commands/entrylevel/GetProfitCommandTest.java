package seedu.cc.logic.commands.entrylevel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.cc.logic.commands.CommandResult;
import seedu.cc.model.Model;
import seedu.cc.model.account.ActiveAccount;
import seedu.cc.model.account.ActiveAccountManager;
import seedu.cc.testutil.ModelStub;
import seedu.cc.testutil.TypicalEntries;

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
