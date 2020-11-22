package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Deletes an event identified using its displayed index from the events list.
 */
public class DeleteEventCommand extends Command {

    public static final String COMMAND_WORD = "delete-event";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the event identified by the index number used in the displayed event list.\n"
            + "Parameter: EVENT_INDEX (must be a positive integer from 1 to 2147483647)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Deleted Event:\n%1$s";

    private final Index targetIndex;

    /**
     * Creates a {@code DeleteEventCommand} with the given {@code targetIndex}.
     */
    public DeleteEventCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> eventsList = model.getEventList();

        if (targetIndex.getZeroBased() >= eventsList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToDelete = eventsList.get(targetIndex.getZeroBased());

        model.deleteEvent(eventToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteEventCommand // instanceof handles null
            && targetIndex.equals(((DeleteEventCommand) other).targetIndex)); // state check
    }
}
