package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.LightThemeCommand.SUCCESS_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class LightThemeCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_light_success() {
        CommandResult expectedCommandResult = new CommandResult(SUCCESS_MESSAGE, true,
                false);
        assertCommandSuccess(new LightThemeCommand(), model, expectedCommandResult, expectedModel);
    }
}
