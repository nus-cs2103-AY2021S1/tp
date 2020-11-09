package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Command to undo previous action.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " :undoes previous change";

    public static final String MESSAGE_SUCCESS = "Undo successful.";
    public static final String MESSAGE_FAILURE = "No previous command found.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (model.undoInventory()) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
