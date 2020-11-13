package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        String commandWord1 = "";
        String commandWord2 = "tag";
        CommandResult expectedCommandResult1 = new CommandResult(SHOWING_HELP_MESSAGE);
        CommandResult expectedCommandResult2 = new CommandResult(TagCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand(commandWord1), model, expectedCommandResult1, expectedModel);
        assertCommandSuccess(new HelpCommand(commandWord2), model, expectedCommandResult2, expectedModel);
        assertThrows(CommandException.class, () -> new HelpCommand("adnfh woiefoik").execute(model));
    }

    @Test
    public void execute_invalidcommandword_throwsCommandException() {
        HelpCommand expectedCommandResult = new HelpCommand("nadfoidjei");
        assertThrows(CommandException.class, () -> expectedCommandResult.execute(model));
    }
}
