package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpPerformanceCommand extends PerformanceCommand {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Format: " + COMMAND_WORD + " (case-sensitive, 'help xx' is not allowed)";

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public PerformanceCommandResult execute(Model model) {
        return new PerformanceCommandResult(SHOWING_HELP_MESSAGE, true);
    }
}
