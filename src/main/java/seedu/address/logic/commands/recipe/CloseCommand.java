package seedu.address.logic.commands.recipe;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public class CloseCommand extends Command {
    public static final String COMMAND_WORD = "close";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Closes left drawer.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Closed left drawer.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, COMMAND_WORD);
    }
}
