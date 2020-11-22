package seedu.address.storage;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.Person;

/**
 * Jackson-friendly version of {@link Event}
 */
public class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event %s field is missing";
    public static final String PERSON_NOT_FOUND_FORMAT = "Resident with matriculation number %s was not found";
    public static final String PERSON_FIELD_INVALID_FORMAT = "%s is not a valid matriculation number";
    public static final String PERSON_REPEATED_FORMAT =
            "Resident with matriculation number %s appeared twice in the list";

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    private final String eventName;
    private final String eventDate;
    private final String location;
    private final String description;
    private final List<String> attendees;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("eventName") String eventName,
                            @JsonProperty("eventDate") String eventDate,
                            @JsonProperty("location") String location,
                            @JsonProperty("description") String description,
                            @JsonProperty("attendees") List<String> attendees) {
        requireAllNonNull(eventName, eventDate, location, description, attendees);
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.location = location;
        this.description = description;
        this.attendees = attendees;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event event) {
        requireNonNull(event);
        this.eventName = event.getName().eventName;
        this.eventDate = event.getEventDate().toString();
        this.location = event.getLocation().location;
        this.description = event.getDescription().description;
        this.attendees = event.getAttendeesList().stream().map(person ->
                person.getMatriculationNumber().value).collect(Collectors.toList());
    }

    /**
     * Converts this Jackson-friendly adapted event into the model's {@code Event} object.
     *
     */
    public Event toModelType(Map<String, Person> personMap) throws IllegalValueException {
        if (eventName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventName.class.getSimpleName()));
        }
        if (!EventName.isValidEventName(eventName)) {
            throw new IllegalValueException(EventName.MESSAGE_CONSTRAINTS);
        }
        final EventName modelName = new EventName(eventName);

        if (eventDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventDate.class.getSimpleName()));
        }
        if (!EventDate.isValidEventDate(eventDate)) {
            throw new IllegalValueException(EventDate.MESSAGE_CONSTRAINTS);
        }
        final EventDate modelDate = new EventDate(eventDate);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (attendees == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "attendees"));
        }
        final Set<Person> modelAttendees = new HashSet<>();
        for (String matriculationNumber : attendees) {
            if (!MatriculationNumber.isValidMatriculationNumber(matriculationNumber)) {
                logger.warning(String.format(PERSON_FIELD_INVALID_FORMAT, matriculationNumber));
                logger.warning(String.format("Omitting %s from the attendees list", matriculationNumber));
                continue;
            }

            if (!personMap.containsKey(matriculationNumber)) {
                logger.warning(String.format(PERSON_NOT_FOUND_FORMAT, matriculationNumber));
                logger.warning(String.format("Omitting %s from the attendees list", matriculationNumber));
                continue;
            }

            boolean hasAddedPerson = modelAttendees.add(personMap.get(matriculationNumber));
            if (!hasAddedPerson) {
                logger.warning(String.format(PERSON_REPEATED_FORMAT, matriculationNumber));
                logger.warning(String.format("%s will be only added once", matriculationNumber));
            }
        }

        return new Event(modelName, modelDate, modelLocation, modelDescription, modelAttendees);
    }

}
