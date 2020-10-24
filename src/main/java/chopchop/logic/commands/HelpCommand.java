package chopchop.logic.commands;

import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;

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
        return new CommandResult(SHOWING_HELP_MESSAGE, /* showHelp: */ true, /* exit: */ false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof HelpCommand);
    }

    @Override
    public String toString() {
        return String.format("HelpCommand");
    }
}
