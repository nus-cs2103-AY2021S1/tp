package seedu.cc.logic.commands;

import static seedu.cc.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cc.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;
import static seedu.cc.testutil.TypicalEntries.getTypicalAccount;

import org.junit.jupiter.api.Test;

import seedu.cc.model.Model;
import seedu.cc.model.ModelManager;


public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        model.addAccount(getTypicalAccount());
        expectedModel.addAccount(getTypicalAccount());
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
