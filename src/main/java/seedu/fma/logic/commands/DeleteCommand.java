package seedu.fma.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.fma.commons.core.Messages;
import seedu.fma.commons.core.index.Index;
import seedu.fma.logic.commands.exceptions.CommandException;
import seedu.fma.model.Model;
import seedu.fma.model.log.Log;

/**
 * Deletes a log identified using it's displayed index from the log book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String AC_SUGGESTION = COMMAND_WORD + " <index>";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the log identified by the index number used in the displayed log list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_LOG_SUCCESS = "Deleted Log: \n%1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Log> lastShownList = model.getFilteredLogList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LOG_DISPLAYED_INDEX);
        }

        Log logToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteLog(logToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_LOG_SUCCESS, logToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }

}
