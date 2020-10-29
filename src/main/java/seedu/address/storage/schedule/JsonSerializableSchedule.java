package seedu.address.storage.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Event;
import seedu.address.model.event.ReadOnlyEvent;
import seedu.address.model.event.Scheduler;
import seedu.address.model.event.exceptions.DuplicateEventException;

/**
 *  An Immutable Schedule that is serializable to JSON format.
 */
@JsonRootName(value = "schedule")
public class JsonSerializableSchedule {

    public static final String MESSAGE_DUPLICATE_EVENTS = "Events list contains duplicate event(s).";

    private final List<JsonAdaptedEvent> events = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableSchedule} with the given events.
     */
    @JsonCreator
    public JsonSerializableSchedule(@JsonProperty("events") List<JsonAdaptedEvent> events) {
        this.events.addAll(events);
    }

    /**
     * Converts a given {@code ReadOnlyEvent} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSchedule}.
     */
    public JsonSerializableSchedule(ReadOnlyEvent source) {
        events.addAll(source.getEventsList().stream().map(JsonAdaptedEvent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this schedule into the model's {@code Scheduler} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Scheduler toModelType() throws IllegalValueException {
        List<Event> lstOfEvents = new ArrayList<>();
        for (JsonAdaptedEvent jsonAdaptedEvent : events) {
            Event event = jsonAdaptedEvent.toModelType();
            if (lstOfEvents.contains(event)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EVENTS);
            }
            lstOfEvents.add(event);
        }
        return new Scheduler(lstOfEvents);
    }

}
