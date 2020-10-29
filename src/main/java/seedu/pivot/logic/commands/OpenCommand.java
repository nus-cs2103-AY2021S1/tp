package seedu.pivot.logic.commands;

import seedu.pivot.commons.core.index.Index;

/**
 * Abstract class for opening items based on Index.
 */
public abstract class OpenCommand extends Command {

    public static final String COMMAND_WORD = "open";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens the item of a specified type, identified by the index number"
            + " used in the displayed list.\n"
            + "Format: '" + COMMAND_WORD + " TYPE'\n\n"
            + "TYPE 'case'\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " case 1\n\n"
            + "TYPE 'doc'\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " doc 1\n\n";


    protected final Index targetIndex;

    protected OpenCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OpenCommand // instanceof handles nulls
                && targetIndex.equals(((OpenCommand) other).targetIndex)); // state check
    }

}
