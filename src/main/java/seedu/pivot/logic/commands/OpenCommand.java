package seedu.pivot.logic.commands;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.model.Model;

/**
 * Abstract class for opening items based on Index.
 */
public abstract class OpenCommand extends Command {

    public static final String COMMAND_WORD = "open";

    // TODO: to be updated when with open doc functionality
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens the item of a specified type, identified by the index number"
            + " used in the displayed list.\n"
            + "Format: '" + COMMAND_WORD + " TYPE'\n\n"
            + "TYPE 'case'\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " case 1";;

    protected final Index targetIndex;

    protected OpenCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OpenCommand // instanceof handles nulls
                && targetIndex.equals(((OpenCommand) other).targetIndex)); // state check
    }

}
