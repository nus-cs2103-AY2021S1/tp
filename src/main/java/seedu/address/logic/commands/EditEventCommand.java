package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;

/**
 * Edits the details of an existing event in the address book.
 */
public class EditEventCommand extends Command {

    public static final String COMMAND_WORD = "edit-event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the event identified "
            + "by the index number used in the displayed events list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: EVENT_INDEX (must be a positive integer from 1 to 2147483647) "
            + "[" + PREFIX_EVENT_NAME + "NAME] "
            + "[" + PREFIX_EVENT_DATE + "DATE] "
            + "[" + PREFIX_EVENT_LOCATION + "LOCATION] "
            + "[" + PREFIX_EVENT_DESC + "DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EVENT_NAME + "Block Lunch "
            + PREFIX_EVENT_LOCATION + "UTown";

    public static final String MESSAGE_EDIT_EVENT_SUCCESS = "Edited Event: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in Hall-y";

    private final Index index;
    private final EditEventDescriptor editEventDescriptor;

    /**
     * @param index index of the event in the events list
     * @param editEventDescriptor details to edit the event with
     */
    public EditEventCommand(Index index, EditEventDescriptor editEventDescriptor) {
        requireAllNonNull(index, editEventDescriptor);
        this.index = index;
        this.editEventDescriptor = editEventDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> eventsList = model.getEventList();

        if (index.getZeroBased() >= eventsList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToEdit = eventsList.get(index.getZeroBased());
        Event editedEvent = createEditedEvent(eventToEdit, editEventDescriptor);

        if (!eventToEdit.isSameEvent(editedEvent) && model.hasEvent(editedEvent)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.setEvent(eventToEdit, editedEvent);
        return new CommandResult(String.format(MESSAGE_EDIT_EVENT_SUCCESS, editedEvent));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof EditEventCommand)) {
            return false;
        }

        EditEventCommand command = (EditEventCommand) obj;
        return index.equals(command.index)
                && editEventDescriptor.equals(command.editEventDescriptor);
    }

    private Event createEditedEvent(Event eventToEdit, EditEventDescriptor editEventDescriptor) {
        assert eventToEdit != null;
        assert editEventDescriptor != null;

        EventName updatedName = editEventDescriptor.getEventName().orElse(eventToEdit.getName());
        EventDate updatedDate = editEventDescriptor.getEventDate().orElse(eventToEdit.getEventDate());
        Location updatedLocation = editEventDescriptor.getLocation().orElse(eventToEdit.getLocation());
        Description updatedDescription = editEventDescriptor.getDescription().orElse(eventToEdit.getDescription());

        return new Event(updatedName, updatedDate, updatedLocation, updatedDescription, eventToEdit.getAttendeesList());
    }

    /**
     * Class that stores the details to edit the event with. Each non-empty field will replace
     * the corresponding field of the event.
     * <p>
     * Note that attendees cannot be edited through this. That field will be directly copied from the original.
     */
    public static class EditEventDescriptor {
        private EventName eventName;
        private EventDate eventDate;
        private Location location;
        private Description description;

        public EditEventDescriptor() {
        }

        /**
         * Copy using the constructor by calling each field's setter.
         */
        public EditEventDescriptor(EditEventDescriptor toCopy) {
            setEventName(toCopy.eventName);
            setEventDate(toCopy.eventDate);
            setLocation(toCopy.location);
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(eventName, eventDate, location, description);
        }

        public void setEventName(EventName eventName) {
            this.eventName = eventName;
        }

        public void setEventDate(EventDate eventDate) {
            this.eventDate = eventDate;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<EventName> getEventName() {
            return Optional.ofNullable(eventName);
        }

        public Optional<EventDate> getEventDate() {
            return Optional.ofNullable(eventDate);
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }

            if (!(obj instanceof EditEventDescriptor)) {
                return false;
            }

            EditEventDescriptor otherDescriptor = (EditEventDescriptor) obj;
            return getEventName().equals(otherDescriptor.getEventName())
                    && getEventDate().equals(otherDescriptor.getEventDate())
                    && getLocation().equals(otherDescriptor.getLocation())
                    && getDescription().equals(otherDescriptor.getDescription());
        }
    }
}
