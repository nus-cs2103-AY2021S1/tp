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
 * View a deliverable by displaying its details in the side panel
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_VIEW_DELIVERABLE_SUCCESS = "Displayed deliverable: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the details of the deliverable identified by the index number used in "
            + "the displayed deliverable list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    /**
     * Construct command given index of deliverable to view.
     * @param targetIndex specified index of deliverable to view.
     */
    public ViewCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }


    @Override
    public CommandResult execute(ModelDeliverable modelDeliverable) throws CommandException {
        requireNonNull(modelDeliverable);
        List<Deliverable> lastShownList = modelDeliverable.getFilteredDeliverableList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DELIVERABLE_DISPLAYED_INDEX);
        }

        Deliverable deliverableToView = lastShownList.get(targetIndex.getZeroBased());
        modelDeliverable.setDeliverableInView(deliverableToView);
        return new CommandResult(String.format(MESSAGE_VIEW_DELIVERABLE_SUCCESS, deliverableToView));
    }
}
