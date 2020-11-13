package seedu.internhunter.logic.commands;

import seedu.internhunter.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    /**
     * Executes the HelpCommand and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Feedback message of the operation result for display.
     */
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false, false, false);
    }
}
