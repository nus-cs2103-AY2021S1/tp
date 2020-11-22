package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";
    public static final String MESSAGE_ASSIGN_PERSON_SUCCESS = "Assigned resident %s to %s";
    public static final String MESSAGE_DUPLICATE_PERSON_ADDED = "Duplicate resident %s being added to event %s";
    public static final String MESSAGE_INVALID_EVENT = "The event provided is invalid";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns the resident identified by the index number used in the displayed person list"
            + " to the event specified by the index number used in the displayed event list.\n"
            + "Parameters: RESIDENT_INDEX EVENT_INDEX (both positive integers from 1 to 2147483647)\n"
            + "Example: " + COMMAND_WORD + " 1 1";

    private static final Logger logger = LogsCenter.getLogger(AssignCommand.class);

    // index of person in person list to add
    // index event that person will be added to
    private final Index residentIndex;
    private final Index eventIndex;

    /**
     * @param residentIndex index of the resident to be added
     * @param eventIndex index of the event to be modified
     */
    public AssignCommand(Index residentIndex, Index eventIndex) {
        this.residentIndex = residentIndex;
        this.eventIndex = eventIndex;
    }

    // an example of an assign command is assign 1 1
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();
        List<Event> eventList = model.getEventList();

        // get person from list based on index
        if (residentIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (eventIndex.getZeroBased() >= eventList.size()) {
            throw new CommandException(MESSAGE_INVALID_EVENT);
        }

        // ensure that index is not negative
        // to be taken out on final release
        assert residentIndex.getZeroBased() >= 0;
        assert eventIndex.getZeroBased() >= 0;

        Person personToAdd = lastShownList.get(residentIndex.getZeroBased());

        // get event from event list
        Event event = eventList.get(eventIndex.getZeroBased());

        // get attendee list from event
        Set<Person> attendeesList = new HashSet<>(event.getAttendeesList());

        // check if person to add is already in the event list
        if (attendeesList.contains(personToAdd)) {
            logger.warning(String.format(MESSAGE_DUPLICATE_PERSON_ADDED,
                    personToAdd.getName(),
                    event.getName()));
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON_ADDED,
                    personToAdd.getName(),
                    event.getName()));
        }

        // add person to event's attendee list
        attendeesList.add(personToAdd);
        Event editedEvent = new Event(event.getName(), event.getEventDate(), event.getLocation(),
                event.getDescription(), attendeesList);

        // update model
        model.setEvent(event, editedEvent);

        logger.fine(String.format(MESSAGE_ASSIGN_PERSON_SUCCESS, personToAdd.getName(), event.getName()));

        return new CommandResult(String.format(MESSAGE_ASSIGN_PERSON_SUCCESS, personToAdd.getName(), event.getName()));
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
                || (obj instanceof AssignCommand)
                && residentIndex.equals(((AssignCommand) obj).residentIndex)
                && eventIndex.equals(((AssignCommand) obj).eventIndex);
    }
}
