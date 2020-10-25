package seedu.stock.logic.commands;

import seedu.stock.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Parameters: No parameters\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Opened help window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS, null, true, false, null, false, null, false);
    }
}
