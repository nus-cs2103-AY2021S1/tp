package seedu.address.logic.command.delete;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.command.Command;
import seedu.address.logic.command.CommandResult;
import seedu.address.logic.command.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a travel plan object identified using it's displayed index from the travel plans.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE =
            "Delete a travel plan by its index in the displayed travel plan list "
            + "or an activity/accommodation/friend by its index in its displayed list "
            + "using the following as an example.\n"
            + DeleteActivityCommand.MESSAGE_EXAMPLE;

    public static final int COMMAND_TOKENS = 3;

    protected final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
