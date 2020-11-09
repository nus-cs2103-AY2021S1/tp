package seedu.zookeep.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.zookeep.logic.commands.exceptions.CommandException;
import seedu.zookeep.model.HistoryStack;
import seedu.zookeep.model.Model;
import seedu.zookeep.model.ReadOnlyZooKeepBook;

/**
 * Redoes the next action. Only applicable if undo was used before and no edit to the ZooKeepBook was
 * made in between, otherwise this command will do nothing.
 */

public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_SUCCESS = "Redo successful";
    public static final String MESSAGE_NO_REDO = "Nothing left to redo";

    private final HistoryStack historyStack;

    /**
     * Creates a RedoCommand that redoes the next action
     */
    public RedoCommand() {
        historyStack = HistoryStack.getHistoryStack();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (historyStack.getRedoSize() < 1) {
            throw new CommandException(MESSAGE_NO_REDO);
        }

        ReadOnlyZooKeepBook lastState = historyStack.viewRecentRedo();

        assert (lastState != null) : "The ZooKeepBook state cannot be null!";
        model.setZooKeepBook(lastState);
        historyStack.removeRecentRedo();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RedoCommand // instanceof handles nulls
                && historyStack.equals(((RedoCommand) other).historyStack));
    }
}
