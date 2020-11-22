package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for all the command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SUCCESSFUL_MESSAGE = "Please refer to the command summary"
            + " or the User Guide if you need any help!";

    /**
     * Executes the help command.
     * @param model {@code Model} which the command should operate on.
     * @return A command result in the help message to be shown to the user.
     */
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SUCCESSFUL_MESSAGE, true, false);
    }
}
