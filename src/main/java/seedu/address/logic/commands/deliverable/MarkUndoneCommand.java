package seedu.address.logic.commands.deliverable;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.deliverable.ModelDeliverable;
import seedu.address.model.deliverable.deliverable.Deadline;
import seedu.address.model.deliverable.deliverable.Deliverable;
import seedu.address.model.deliverable.deliverable.Milestone;
import seedu.address.model.util.Contacts;
import seedu.address.model.util.Description;
import seedu.address.model.util.Title;

/**
 * Mark a deliverable as on-going.
 */
public class MarkUndoneCommand extends Command {

    public static final String COMMAND_WORD = "undone";
    public static final String MESSAGE_UNDONE_DELIVERABLE_SUCCESS = "Marked deliverable as on-going: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks as on-going the deliverable identified by the index number "
            + "used in the displayed deliverable list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    /**
     * Construct command given index of deliverable to mark as on-going.
     * @param targetIndex specified index of deliverable to complete.
     */
    public MarkUndoneCommand(Index targetIndex) {
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

        Deliverable deliverableToMarkUndone = lastShownList.get(targetIndex.getZeroBased());

        Deliverable unDoneDeliverable = createUndoneDeliverable(deliverableToMarkUndone);
        modelDeliverable.updateDeliverableStatus(deliverableToMarkUndone, unDoneDeliverable);
        return new CommandResult(String.format(MESSAGE_UNDONE_DELIVERABLE_SUCCESS, deliverableToMarkUndone));
    }

    private Deliverable createUndoneDeliverable(Deliverable deliverableToMarkUndone) {
        Title title = deliverableToMarkUndone.getTitle();
        Milestone milestone = deliverableToMarkUndone.getMilestone();
        Description description = deliverableToMarkUndone.getDescription();
        Deadline deadline = deliverableToMarkUndone.getDeadline();
        Contacts contacts = deliverableToMarkUndone.getContacts();
        return new Deliverable(title, milestone, description, deadline, false, contacts);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof MarkUndoneCommand
                && targetIndex.equals(((MarkUndoneCommand) other).targetIndex);
    }
}
