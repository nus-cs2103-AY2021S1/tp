package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.HistoryStack;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyZooKeepBook;
import seedu.address.model.ZooKeepBook;

/**
 * Undoes the most recently performed action.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Undo successful";
    public static final String MESSAGE_NO_UNDO = "Nothing left to undo";

    private final HistoryStack historyStack;

    /**
     * Creates an UndoCommand that undoes the last action
     */
    public UndoCommand() {
        historyStack = HistoryStack.getHistoryStack();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (historyStack.getHistorySize() <= 1) {
            throw new CommandException(MESSAGE_NO_UNDO);
        }

        // add current state to redo stack before deleting it
        ReadOnlyZooKeepBook currentState = historyStack.viewRecentHistory();
        historyStack.addToRedo(new ZooKeepBook(currentState));

        historyStack.removeRecentHistory();
        ReadOnlyZooKeepBook lastState = historyStack.viewRecentHistory();
        model.setZooKeepBook(lastState);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UndoCommand // instanceof handles nulls
                && historyStack.equals(((UndoCommand) other).historyStack));
    }
}
