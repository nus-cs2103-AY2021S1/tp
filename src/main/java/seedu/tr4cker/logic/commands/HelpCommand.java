package seedu.tr4cker.logic.commands;

import seedu.tr4cker.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "(E.g. " + COMMAND_WORD + ")";

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(Model model) {
        return CommandResult.createHelpTabSwitchCommandResult(SHOWING_HELP_MESSAGE);
    }

}
