package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.history.CommandHistory;

/**
 * Clears command history in the address book.
 */
public class ClearHistoryCommand extends Command {

    public static final String COMMAND_WORD = "clearhistory";

    public static final String MESSAGE_SUCCESS = "Your command history has been cleared.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        assert model != null : "Model cannot be null";
        CommandHistory.clearHistory();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
