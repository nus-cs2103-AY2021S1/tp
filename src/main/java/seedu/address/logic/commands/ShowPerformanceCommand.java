package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class ShowPerformanceCommand extends Command {

    public static final String COMMAND_WORD = "performance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows user's past quiz attempts.\n"
            + "Example: " + COMMAND_WORD + " (case sensitive, 'performance xx' is not allowed)";

    public static final String SHOWING_PERFORMANCE_MESSAGE = "Opened performance window.\n"
        + "Only Performance interface commands are workable in the new performance window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_PERFORMANCE_MESSAGE, false, true, false);
    }
}
