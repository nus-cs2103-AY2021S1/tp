package seedu.stock.logic.commands;

import static seedu.stock.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedResult = new CommandResult(HelpCommand.MESSAGE_SUCCESS, null, true,
                false, null, false, null, false,
                false);
        assertCommandSuccess(new HelpCommand(), model, expectedResult, expectedModel);
    }
}
