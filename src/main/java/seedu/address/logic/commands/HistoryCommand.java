package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.history.HistoryManager;
import seedu.address.model.Model;

/**
 * Lists all previous commands.
 */
public class HistoryCommand extends Command {
    public static final String COMMAND_WORD = "history";

    public static final String MESSAGE_NO_HISTORY = "No history found";

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        requireNonNull(historyManager);

        String history = historyManager.getHistory();

        if (history.isBlank()) {
            throw new CommandException(MESSAGE_NO_HISTORY);
        }

        return new CommandResult(history);
    }
}
