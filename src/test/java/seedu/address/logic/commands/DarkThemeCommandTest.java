package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DarkThemeCommand.SUCCESS_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class DarkThemeCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_dark_success() {
        CommandResult expectedCommandResult = new CommandResult(SUCCESS_MESSAGE, false,
                true);
        assertCommandSuccess(new DarkThemeCommand(), model, expectedCommandResult, expectedModel);
    }
}
