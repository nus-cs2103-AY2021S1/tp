package seedu.homerce.logic.commands;

import seedu.homerce.model.Model;
import seedu.homerce.model.manager.HistoryManager;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false, false);
    }
}
