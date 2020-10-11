package seedu.address.logic.commands;


import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Abstract class for deleting different types of items based off of index.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    //TODO: Consider abstracting this out further, find a way to represent any item type or show
    // of all different item types and the delete command usage
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the specified type of item identified by the index number"
            + " used in the displayed list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " case 1";

    protected final Index targetIndex;

    protected DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
