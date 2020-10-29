package seedu.address.model.event;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;


/**
 * Represents an Event to that keep tracks of the start and end time and date.
 */
public class Event {

    private final EventName name;
    private final EventTime time;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Creates a Event object with the specified name and the start and end timings.
     * @param name
     * @param time
     */
    public Event(EventName name, EventTime time) {
        assert(name != null);
        this.name = name;
        this.time = time;
    }

    public EventName getName() {
        return this.name;
    }

    public EventTime getTime() {
        return this.time;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(this.tags);
    }

    /**
     * Returns True if the two events have at least one field that is different, ignoring the Tags.
     * This defines a weaker notion of equality between two Events.
     * @param event Event to be compared.
     * @return boolean if the two Events are the same.
     */
    public boolean isSameEvent(Event event) {
        if (this == event) {
            return true;
        } else if (event == null) {
            return false;
        } else {
            return this.getName().equals(event.getName())
                    && this.getTime().equals(event.getTime());
        }
    }

    /**
     * Returns True if the two Events are exactly the same, even the Tags.
     * This defines a stronger notion of equality between the two Events.
     * @param other Event to be compared.
     * @return boolean if the two Events are the same.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof Event)) {
            return false;
        } else if (other == null) {
            return false;
        } else {
            Event otherEvent = (Event) other;
            return this.getName().equals(otherEvent.getName())
                    && this.getTime().equals(otherEvent.getTime())
                    && this.getTags().equals(otherEvent.getTags());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.time, this.tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Event Name: ")
                .append(getName())
                .append(" Time/Date: ")
                .append(getTime())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
