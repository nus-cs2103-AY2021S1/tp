package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Person;


/**
 * Represents an Event in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event {
    private final EventName eventName;
    private final EventDate eventDate;
    private final Location location;
    private final Description description;
    private final Set<Person> attendeesList;

    /**
     * Every field must be present and not null.
     */
    public Event(EventName eventName, EventDate eventDate, Location location, Description description) {
        requireAllNonNull(eventName, eventDate, location, description);
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.location = location;
        this.description = description;
        this.attendeesList = new HashSet<>();
    }

    /**
     * Every field must be present and not null.
     */
    public Event (EventName eventName, EventDate eventDate,
                  Location location, Description description, Set<Person> personSet) {
        requireAllNonNull(eventName, eventDate, location, description, personSet);
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.location = location;
        this.description = description;
        this.attendeesList = personSet;
    }

    public EventName getName() {
        return eventName;
    }

    public EventDate getEventDate() {
        return eventDate;
    }

    public Location getLocation() {
        return location;
    }

    public Description getDescription() {
        return description;
    }

    public Set<Person> getAttendeesList() {
        return attendeesList;
    }

    /**
     * Returns true if both events have the same name, location and date.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getName().equals(getName())
                && otherEvent.getLocation().equals(getLocation())
                && otherEvent.getEventDate().equals(getEventDate());
    }

    /**
     * Returns true if both events have the same name and description.
     * This defines a stronger notion of equality between two events.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return otherEvent.getName().equals(getName())
                && otherEvent.getDescription().equals(getDescription())
                && otherEvent.getEventDate().equals(getEventDate())
                && otherEvent.getLocation().equals(getLocation())
                && attendeesList.equals(otherEvent.attendeesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, description, eventDate, location, attendeesList);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("\nEvent Date: ")
                .append(getEventDate())
                .append("\nLocation: ")
                .append(getLocation())
                .append("\nDescription: ")
                .append(getDescription());
        return builder.toString();
    }
}
