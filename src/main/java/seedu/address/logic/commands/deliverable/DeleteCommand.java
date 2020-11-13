package seedu.address.logic.commands.deliverable;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.deliverable.ModelDeliverable;
import seedu.address.model.deliverable.deliverable.Deliverable;

/**
 * Deletes a deliverable identified using it's displayed index from the deliverable book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the deliverable identified by the index number used in the displayed deliverable list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_DELIVERABLE_SUCCESS = "Deleted deliverable: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(ModelDeliverable modelDeliverable) throws CommandException {
        requireNonNull(modelDeliverable);
        List<Deliverable> lastShownList = modelDeliverable.getFilteredDeliverableList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DELIVERABLE_DISPLAYED_INDEX);
        }

        Deliverable deliverableToDelete = lastShownList.get(targetIndex.getZeroBased());
        modelDeliverable.deleteDeliverable(deliverableToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_DELIVERABLE_SUCCESS, deliverableToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
