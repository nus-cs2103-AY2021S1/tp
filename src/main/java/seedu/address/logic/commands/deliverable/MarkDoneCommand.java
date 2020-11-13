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
 * Completes a deliverable
 */
public class MarkDoneCommand extends Command {

    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_DONE_DELIVERABLE_SUCCESS = "Marked deliverable as completed: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks as completed the deliverable identified by the index number "
            + "used in the displayed deliverable list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    /**
     * Construct command given index of deliverable to complete.
     * @param targetIndex specified index of deliverable to complete.
     */
    public MarkDoneCommand(Index targetIndex) {
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

        Deliverable deliverableToComplete = lastShownList.get(targetIndex.getZeroBased());
        Deliverable completedDeliverable = createCompletedDeliverable(deliverableToComplete);
        modelDeliverable.updateDeliverableStatus(deliverableToComplete, completedDeliverable);
        return new CommandResult(String.format(MESSAGE_DONE_DELIVERABLE_SUCCESS, deliverableToComplete));
    }

    private Deliverable createCompletedDeliverable(Deliverable deliverableToComplete) {
        Title title = deliverableToComplete.getTitle();
        Milestone milestone = deliverableToComplete.getMilestone();
        Description description = deliverableToComplete.getDescription();
        Deadline deadline = deliverableToComplete.getDeadline();
        Contacts contacts = deliverableToComplete.getContacts();
        return new Deliverable(title, milestone, description, deadline, true, contacts);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof MarkDoneCommand
                && targetIndex.equals(((MarkDoneCommand) other).targetIndex);
    }


}
