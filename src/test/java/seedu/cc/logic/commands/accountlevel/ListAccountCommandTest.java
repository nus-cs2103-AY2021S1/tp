package seedu.cc.logic.commands.accountlevel;

import static seedu.cc.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cc.testutil.TypicalEntries.getTypicalAccount;

import org.junit.jupiter.api.Test;

import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.CommandResultFactory;
import seedu.cc.model.Model;
import seedu.cc.model.ModelManager;

public class ListAccountCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_listacc_success() {
        model.addAccount(getTypicalAccount());
        expectedModel.addAccount(getTypicalAccount());
        ListAccountCommand listacc = new ListAccountCommand();
        CommandResult expectedCommandResult =
                new CommandResultFactory().createDefaultCommandResult(listacc
                        .accountsToString(expectedModel.getFilteredAccountList()));
        assertCommandSuccess(listacc, model, expectedCommandResult, expectedModel);
    }
}
