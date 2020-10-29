package seedu.address.storage.schedule;


import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventRecurrence;

/**
 * Jackson-friendly version of {@link Event}.
 */
public class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";


    private String eventName;
    private String eventStartDateTime;
    private String eventEndDateTime;
    private String uniqueIdentifier;
    private String eventRecurrence;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("eventName") String eventName,
                              @JsonProperty("eventStartDateTime") String eventStartDateTime,
                              @JsonProperty("eventEndDateTime") String eventEndDateTime,
                              @JsonProperty("uniqueIdentifier") String uniqueIdentifier,
                              @JsonProperty("eventRecurrence") String eventRecurrence) {
        this.eventName = eventName;
        this.eventStartDateTime = eventStartDateTime;
        this.eventEndDateTime = eventEndDateTime;
        this.uniqueIdentifier = uniqueIdentifier;
        this.eventRecurrence = eventRecurrence;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        eventName = source.getEventName();
        eventStartDateTime = source.getEventStartDateTime().toString();
        eventEndDateTime = source.getEventEndDateTime().toString();
        uniqueIdentifier = source.getUniqueIdentifier();
        eventRecurrence = source.getRecurrence().toString();
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public Event toModelType() throws IllegalValueException {

        if (eventName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "eventName"));
        }
        if (!Event.isValidEventName(eventName)) {
            throw new IllegalValueException(Event.INVALID_EVENT_NAME_MSG);
        }

        if (eventStartDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "eventStartDateTime"));
        }

        if (eventEndDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "eventEndDateTime"));
        }

        if (uniqueIdentifier == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "uniqueIdentifier"));
        }

        if (eventRecurrence == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "eventRecurrence"));
        }

        EventRecurrence eventRecurrenceEnum;
        try {
            eventRecurrenceEnum = EventRecurrence.checkWhichRecurrence(eventRecurrence);
        } catch (ParseException e) {
            throw new IllegalValueException(e.getMessage());
        }

        try {
            LocalDateTime startDateTime = LocalDateTime.parse(eventStartDateTime);
            LocalDateTime endDateTime = LocalDateTime.parse(eventEndDateTime);

            if (!Event.isValidEventStartAndEndTime(startDateTime, endDateTime)) {
                throw new IllegalValueException(Event.INVALID_EVENT_START_END_TIME_MSG);
            }

            return new Event(eventName, startDateTime, endDateTime, uniqueIdentifier,
                    eventRecurrenceEnum);

        } catch (DateTimeParseException e) {
            throw new IllegalValueException(Event.INCORRECT_DATE_TIME_MESSAGE);
        }

    }

}
