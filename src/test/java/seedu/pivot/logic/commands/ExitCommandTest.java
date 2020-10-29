package seedu.pivot.logic.commands;

import static seedu.pivot.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
