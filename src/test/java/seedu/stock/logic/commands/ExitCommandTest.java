package seedu.stock.logic.commands;

import static seedu.stock.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.stock.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;


public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT,
                null, false, false, null, false, null, false, true);
        assertCommandSuccess(new ExitCommand(), model, expectedResult, expectedModel);
    }
}
