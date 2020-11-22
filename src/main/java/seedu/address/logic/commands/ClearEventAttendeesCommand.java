package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * clears an event's attendee list identified using its displayed index from the events list.
 */
public class ClearEventAttendeesCommand extends Command {

    public static final String COMMAND_WORD = "clear-event-attendees";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears the attendee list of the "
            + "event identified by the index number used in the displayed event list.\n"
            + "Parameter: EVENT_INDEX (must be a positive integer from 1 to 2147483647)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CLEAR_EVENT_SUCCESS = "Cleared attendee list of Event:\n%1$s";

    private final Index targetIndex;

    /**
     * Creates a {@code ClearEventAttendeesCommand} with the given {@code targetIndex}.
     */
    public ClearEventAttendeesCommand(Index targetIndex) {
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

        Event event = eventsList.get(targetIndex.getZeroBased());
        Event editedEvent = new Event(event.getName(), event.getEventDate(), event.getLocation(),
                event.getDescription(), new HashSet<>());
        // update model
        model.setEvent(event, editedEvent);

        return new CommandResult(String.format(MESSAGE_CLEAR_EVENT_SUCCESS, event));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClearEventAttendeesCommand // instanceof handles null
                && targetIndex.equals(((ClearEventAttendeesCommand) other).targetIndex)); // state check
    }
}
