package seedu.tr4cker.model.countdown;

import static seedu.tr4cker.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an Event in the Countdowns list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event {
    private final EventName eventName;
    private final EventDate eventDate;
    // to consider adding: date time instead  of date
    // description

    /**
     * Every field must be present and not null.
     */
    public Event(EventName eventName, EventDate eventDate) {
        requireAllNonNull(eventName, eventDate);
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public EventName getEventName() {
        return eventName;
    }

    public EventDate getEventDate() {
        return eventDate;
    }

    /**
     * Returns the number of days remaining to the event as an int.
     * Returns 0 if event has passed.
     */
    public int getDaysRemaining() {
        return getEventDate().getDaysTill();
    }

    /**
     * Returns true if both events have the same name.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(seedu.tr4cker.model.countdown.Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }
        return otherEvent != null
                && otherEvent.getEventName().equals(getEventName());
    }

    /**
     * Returns true if both events have the same identity and data fields.
     * This defines a stronger notion of equality between two events.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof seedu.tr4cker.model.countdown.Event)) {
            return false;
        }

        seedu.tr4cker.model.countdown.Event otherTask = (seedu.tr4cker.model.countdown.Event) other;
        return otherTask.getEventName().equals(getEventName())
                && otherTask.getEventDate().equals(getEventDate());

    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(eventName, eventDate);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getEventName())
                .append(" Date: ")
                .append(getEventDate());
        return builder.toString();
    }
}
