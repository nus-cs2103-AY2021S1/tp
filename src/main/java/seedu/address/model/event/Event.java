package seedu.address.model.event;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;


/**
 * An Event object that encompass the the various fields that an event can contain. These includes event name, start
 * time, end time, description, and recurrence type of the event.
 * This will be mapped to iCalendarFx's VEvent to be displayed in the iCalendar graphical user interface.
 */
public class Event {

    public static final String INVALID_EVENT_NAME_MSG = "Event name cannot be blank";
    public static final String INVALID_EVENT_START_END_TIME_MSG = "Event start time is after end time";
    public static final String INCORRECT_DATE_TIME_MESSAGE = "Event date time is in incorrect format!";


    private String eventName;
    private LocalDateTime eventStartDateTime;
    private LocalDateTime eventEndDateTime;
    private String uniqueIdentifier; // uid for iCalendarAgenda use
    private EventRecurrence recurrence;

    /**
     * Creates an Event object that will be link to the user's schedule.
     *
     * @param eventName
     * @param eventStartDateTime
     * @param eventEndDateTime
     * @param uniqueIdentifier
     * @param recurrence
     */
    public Event(String eventName, LocalDateTime eventStartDateTime, LocalDateTime eventEndDateTime,
                 String uniqueIdentifier, EventRecurrence recurrence) {
        requireAllNonNull(eventName, eventStartDateTime, eventEndDateTime, uniqueIdentifier, recurrence);
        checkArgument(isValidEventName(eventName));
        checkArgument(isValidEventStartAndEndTime(eventStartDateTime, eventEndDateTime));
        this.eventName = eventName;
        this.eventStartDateTime = eventStartDateTime;
        this.eventEndDateTime = eventEndDateTime;
        this.uniqueIdentifier = uniqueIdentifier;
        this.recurrence = recurrence;
    }

    /**
     * Validates an event name to ensure it is not blank
     *
     * @param eventName
     * @return
     */
    public static boolean isValidEventName(String eventName) {
        return !eventName.isBlank();
    }

    public static boolean isValidEventStartAndEndTime(LocalDateTime eventStartTime, LocalDateTime eventEndTime) {
        return eventStartTime.isBefore(eventEndTime);
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDateTime getEventStartDateTime() {
        return eventStartDateTime;
    }

    public LocalDateTime getEventEndDateTime() {
        return eventEndDateTime;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public EventRecurrence getRecurrence() {
        return recurrence;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, eventStartDateTime, eventEndDateTime, uniqueIdentifier, recurrence);
    }

    /**
     * This method checks if events are the same based on event name, start and end date time.
     * This is a weaker notion of equality between events.
     */
    public boolean isSameEvent(Event otherEvent) {
        return otherEvent.eventName.equals(eventName)
                && otherEvent.eventStartDateTime.equals(eventStartDateTime)
                && otherEvent.eventEndDateTime.equals(eventEndDateTime);
    }

    /**
     * Returns true if both events has the same data fields
     *
     * @param obj the event to be compared
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) obj;
        return otherEvent.eventName.equals(eventName)
                && otherEvent.eventStartDateTime.equals(eventStartDateTime)
                && otherEvent.eventEndDateTime.equals(eventEndDateTime)
                && otherEvent.uniqueIdentifier.equals(uniqueIdentifier)
                && otherEvent.recurrence.equals(recurrence);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Event: ")
                .append(eventName)
                .append("\n")
                .append("StartDateTime: ")
                .append(eventStartDateTime)
                .append("\n")
                .append("EndDateTime: ")
                .append(eventEndDateTime);

        return builder.toString();
    }

    /**
     * Returns true if this event overlaps with another event.
     *
     * @param otherEvent the event to be compared
     */
    public boolean isOverlapping(Event otherEvent) {
        return eventStartDateTime.isBefore(otherEvent.eventEndDateTime)
                && otherEvent.eventStartDateTime.isBefore(eventEndDateTime);
    }

    public boolean isSameName(Event otherEvent) {
        return otherEvent.eventName.equals(eventName);
    }

}
