package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.history.CommandHistory;

/**
 * Clears command history in the address book.
 */
public class ClearHistoryCommand extends Command {

    public static final String COMMAND_WORD = "clearhistory";

    public static final String SUCCESS_MESSAGE = "Command history has been cleared.";

    @Override
    public CommandResult execute(Model model) {
        CommandHistory.clearHistory();
        return new CommandResult(SUCCESS_MESSAGE);
    }
}
