package seedu.address.logic.commands.deliverable;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.deliverable.DeliverableBook;
import seedu.address.model.deliverable.ModelDeliverable;

/**
 * Clears the deliverable book.
 */
public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Cleared all deliverables!";

    @Override
    public CommandResult execute(ModelDeliverable modelDeliverable) throws CommandException {
        requireNonNull(modelDeliverable);

        if (modelDeliverable.getInternalDeliverableList().size() == 0) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_DELIVERABLE_LIST_EMPTY, COMMAND_WORD));
        }

        modelDeliverable.setDeliverableBook(new DeliverableBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
