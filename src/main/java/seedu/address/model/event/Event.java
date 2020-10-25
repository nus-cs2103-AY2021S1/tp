package seedu.address.model.event;


import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.util.Objects;


/**
 * An Event object that encompass the the various fields that an event can contain. These includes event name, start
 * time, end time, description, and recurrence type of the event.
 * This will be mapped to iCalendarFx's VEvent to be displayed in the iCalendar graphical user interface.
 */
public class Event {

    private static String INVALID_EVENT_NAME_MSG = "Event name cannot be blank";
    private static String INVALID_EVENT_START_END_TIME_MSG = "Event start time is after end time";

    private String eventName;
    private LocalDateTime eventStartTime;
    private LocalDateTime eventEndTime;
    private String description;
    private String uniqueIdentifier; // uid for iCalendarAgenda use
    private EventRecurrence recurrence;

    /**
     * Creates an Event object that will be link to the user's schedule.
     * @param eventName
     * @param eventStartTime
     * @param eventEndTime
     * @param description
     * @param uniqueIdentifier
     * @param recurrence
     */
    public Event(String eventName, LocalDateTime eventStartTime, LocalDateTime eventEndTime,
                 String description, String uniqueIdentifier, EventRecurrence recurrence) {
        requireAllNonNull(eventName, eventStartTime, eventEndTime, description, uniqueIdentifier, recurrence);
        checkArgument(isValidEventName(eventName));
        checkArgument(isValidEventStartAndEndTime(eventStartTime, eventEndTime));
        this.eventName = eventName;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.description = description;
        this.uniqueIdentifier = uniqueIdentifier;
        this.recurrence = recurrence;
    }

    /**
     * Validates an event name to ensure it is not blank
     * @param eventName
     * @return
     */
    public boolean isValidEventName(String eventName) {
        return !eventName.isBlank();
    }

    public boolean isValidEventStartAndEndTime(LocalDateTime eventStartTime, LocalDateTime eventEndTime) {
        return eventStartTime.isBefore(eventEndTime);
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDateTime getEventStartTime() {
        return eventStartTime;
    }

    public LocalDateTime getEventEndTime() {
        return eventEndTime;
    }

    public String getDescription() {
        return description;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public EventRecurrence getRecurrence() {
        return recurrence;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, eventStartTime, eventEndTime, description, uniqueIdentifier, recurrence);
    }

    /**
     * Returns true if both events has the same data fields
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
                && otherEvent.eventStartTime.equals(eventStartTime)
                && otherEvent.eventEndTime.equals(eventEndTime)
                && otherEvent.description.equals(description)
                && otherEvent.uniqueIdentifier.equals(uniqueIdentifier)
                && otherEvent.recurrence.equals(recurrence);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Event name: ")
                .append(eventName)
                .append("Timing: ")
                .append(eventStartTime)
                .append(" - ")
                .append(eventEndTime)
                .append("Description: ")
                .append(description);

        return builder.toString();
    }
}
