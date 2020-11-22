package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.SUCCESSFUL_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.ExpenseModelManager;
import seedu.address.model.Model;

/**
 * Contains integration tests (interaction with the Model) for {@code HelpCommand}.
 */
public class HelpCommandTest {
    private Model model = new ExpenseModelManager();
    private Model expectedModel = new ExpenseModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SUCCESSFUL_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_help_failure() {
        CommandResult expectedCommandResult = new CommandResult("WRONG MESSAGE", true, false);
        CommandResult actualCommandResult = new HelpCommand().execute(model);
        assertFalse(expectedCommandResult.equals(actualCommandResult));
    }
}
