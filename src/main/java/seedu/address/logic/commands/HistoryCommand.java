package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.history.CommandHistory;

/**
 * Lists all past used commands in the address book to the user.
 */
public class HistoryCommand extends Command {

    public static final String COMMAND_WORD = "history";

    public static final String MESSAGE_EMPTY_HISTORY = "There is no command history.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        assert model != null : "Model cannot be null";
        if (!CommandHistory.hasCommand()) {
            return new CommandResult(MESSAGE_EMPTY_HISTORY);
        } else {
            CommandResult history = new CommandResult(CommandHistory.getCommandHistory());
            return history;
        }
    }
}
