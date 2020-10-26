package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        String commandWord1 = "";
        String commandWord2 = "tag";
        String commandWord3 = "afiiwehfoiw";
        CommandResult expectedCommandResult1 = new CommandResult(SHOWING_HELP_MESSAGE);
        CommandResult expectedCommandResult2 = new CommandResult(TagCommand.MESSAGE_USAGE);
        CommandResult expectedCommandResult3 = new CommandResult(
                String.format(HelpCommand.INVALID_KEYWORD_MESSAGE, commandWord3));
        assertCommandSuccess(new HelpCommand(commandWord1), model, expectedCommandResult1, expectedModel);
        assertCommandSuccess(new HelpCommand(commandWord2), model, expectedCommandResult2, expectedModel);
        assertCommandSuccess(new HelpCommand(commandWord3), model, expectedCommandResult3, expectedModel);
    }
}
