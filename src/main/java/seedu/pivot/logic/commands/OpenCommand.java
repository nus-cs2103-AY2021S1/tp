package seedu.pivot.logic.commands;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.model.Model;

/**
 * Abstract class for opening items based on Index.
 */
public abstract class OpenCommand extends Command {

    public static final String COMMAND_WORD = "open";

    //to be updated when with open doc functionality
    public static final Object MESSAGE_USAGE = COMMAND_WORD
            + ": Opens the specified type of item, 'case' for investigation,"
            + " at the specified index in the displayed list.\n"
            + "Parameters: TYPE INDEX (type must be 'case' and index must be a positive integer)\n"
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
