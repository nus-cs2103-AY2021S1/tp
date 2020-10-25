package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Command to repeat action that was undone.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " :redoes undone change";

    public static final String MESSAGE_SUCCESS = "Redo successful.";
    public static final String MESSAGE_FAILURE = "No undone command found.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (model.redoInventory()) {
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
