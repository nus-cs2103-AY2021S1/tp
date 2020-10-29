package seedu.address.model;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventRecurrence;

import java.time.LocalDateTime;

public class builder {
    public static final String DEFAULT_EVENT_NAME = "Lesson event";
    public static final LocalDateTime DEFAULT_START_DATE_TIME = LocalDateTime.of(2020, 12, 25, 21, 30);
    public static final LocalDateTime DEFAULT_END_DATE_TIME = LocalDateTime.of(2020, 12, 25, 23, 30);
    public static final EventRecurrence DEFAULT_RECURRENCE = EventRecurrence.NONE;
    public static final String DEFAULT_UNIQUE_IDENTIFIER = "defaultUID";

    private String eventName;
    private LocalDateTime eventStartDateTime;
    private LocalDateTime eventEndDateTime;
    private String uniqueIdentifier;
    private EventRecurrence recurrence;

    /**
     * Creates a {@code builder} with the default details.
     */
    public builder() {
        this.eventName = DEFAULT_EVENT_NAME;
        this.eventStartDateTime = DEFAULT_START_DATE_TIME;
        this.eventEndDateTime = DEFAULT_END_DATE_TIME;
        this.uniqueIdentifier = DEFAULT_UNIQUE_IDENTIFIER;
        this.recurrence = DEFAULT_RECURRENCE;
    }

    /**
     * Initializes the builder with the data of {@code eventToCopy}.
     */
    public builder(Event eventToCopy) {
        this.eventName = eventToCopy.getEventName();
        this.eventStartDateTime = eventToCopy.getEventStartDateTime();
        this.eventEndDateTime = eventToCopy.getEventEndDateTime();
        this.uniqueIdentifier = eventToCopy.getUniqueIdentifier();
        this.recurrence = eventToCopy.getRecurrence();
    }

    /**
     * Sets the {@code eventName} of the {@code Event} that we are building.
     */
    public builder withEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    /**
     * Sets the {@code eventStartDateTime} of the {@code Event} that we are building.
     */
    public builder withEventStartDateTime(LocalDateTime eventStartDateTime) {
        this.eventStartDateTime = eventStartDateTime;
        return this;
    }

    /**
     * Sets the {@code eventEndDateTime} of the {@code Event} that we are building.
     */
    public builder withEventEndDateTime(LocalDateTime eventEndDateTime) {
        this.eventEndDateTime = eventEndDateTime;
        return this;
    }

    /**
     * Sets the {@code uniqueIdentifier} of the {@code Event} that we are building.
     */
    public builder withUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
        return this;
    }

    /**
     * Sets the {@code recurrence} of the {@code Event} that we are building.
     */
    public builder withRecurrence(EventRecurrence recurrence) {
        this.recurrence = recurrence;
        return this;
    }

    /**
     * Builds a {@code Event} based on the given information.
     */
    public Event build() {
        return new Event(eventName, eventStartDateTime, eventEndDateTime,
                uniqueIdentifier, recurrence);
    }

}
