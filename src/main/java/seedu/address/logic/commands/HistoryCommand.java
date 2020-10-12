package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.history.History;
import seedu.address.model.Model;

/**
 * Lists all previous commands.
 */
public class HistoryCommand extends Command {
    public static final String COMMAND_WORD = "history";

    public static final String MESSAGE_NO_HISTORY = "No history found";

    @Override
    public CommandResult execute(Model model, History history) throws CommandException {
        requireNonNull(history);

        String historyResults = history.getHistory();

        if (historyResults.isBlank()) {
            throw new CommandException(MESSAGE_NO_HISTORY);
        }

        return new CommandResult(historyResults);
    }
}
