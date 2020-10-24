package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEntries.getTypicalAccount;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class ListAccountCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_listacc_success() {
        model.addAccount(getTypicalAccount());
        expectedModel.addAccount(getTypicalAccount());
        ListAccountCommand listacc = new ListAccountCommand();
        CommandResult expectedCommandResult =
                CommandResultFactory.createDefaultCommandResult(
                        listacc.accountsToString(expectedModel.getFilteredAccountList()));
        assertCommandSuccess(listacc, model, expectedCommandResult, expectedModel);
    }
}
