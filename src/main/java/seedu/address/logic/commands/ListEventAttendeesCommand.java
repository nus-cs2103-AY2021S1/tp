package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.FilterEventPredicate;

public class ListEventAttendeesCommand extends Command {
    public static final String COMMAND_WORD = "list-event-attendees";
    public static final String MESSAGE_FILTER_SUCCESS = "Displaying residents attending event %s";
    public static final String MESSAGE_INVALID_EVENT = "The event index provided is invalid";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the residents attending "
            + "the event specified by the index number used in the displayed event list.\n"
            + "Parameters: EVENT_INDEX (must be positive integer from 1 to 2147483647) \n"
            + "Example: " + COMMAND_WORD + " 1 ";

    private final Index eventIndex;

    public ListEventAttendeesCommand(Index eventIndex) {
        this.eventIndex = eventIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Event> eventList = model.getEventList();
        if (eventIndex.getZeroBased() >= eventList.size()) {
            throw new CommandException(MESSAGE_INVALID_EVENT);
        }

        // get event from event list
        Event event = eventList.get(eventIndex.getZeroBased());

        FilterEventPredicate predicate = new FilterEventPredicate(event.getAttendeesList());
        model.updateFilteredPersonList(predicate);
        return new CommandResult(String.format(MESSAGE_FILTER_SUCCESS, event.getName()));
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
                || (obj instanceof ListEventAttendeesCommand)
                && eventIndex.equals(((ListEventAttendeesCommand) obj).eventIndex);
    }
}
