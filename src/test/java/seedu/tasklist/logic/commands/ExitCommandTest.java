package seedu.tasklist.logic.commands;

import static seedu.tasklist.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tasklist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tasklist.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.tasklist.model.Model;
import seedu.tasklist.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new ExitCommand("exit"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_exit_failed() {
        assertCommandFailure(new ExitCommand("exit 3"), expectedModel, ExitCommand.MESSAGE_INCORRECT_FORMAT);
    }
}
