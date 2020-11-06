package seedu.pivot.logic.commands;

import static seedu.pivot.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
