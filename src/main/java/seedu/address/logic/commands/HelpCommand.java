package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = "Format: " + COMMAND_WORD;
    public static final String MESSAGE_HELP = "Input '" + COMMAND_WORD + "' to see an overview of all"
            + " current available commands.";

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private final String userInput;

    public HelpCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        boolean hasNoArgument = userInput.trim().contentEquals("help");
        if (hasNoArgument) {
            return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        } else {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
    }
}
