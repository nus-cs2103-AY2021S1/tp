package seedu.address.storage.meeting;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.meeting.From;
import seedu.address.model.meeting.meeting.Location;
import seedu.address.model.meeting.meeting.Meeting;
import seedu.address.model.meeting.meeting.To;
import seedu.address.model.util.Contacts;
import seedu.address.model.util.Description;
import seedu.address.model.util.Title;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
public class JsonAdaptedMeeting {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";

    private final String title;
    private final Optional<String> description;
    private final String from;
    private final String to;
    private final Optional<String> contacts;
    private final Optional<String> location;

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given meeting details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("title") String title,
                              @JsonProperty("description") Optional<String> description,
                             @JsonProperty("from") String from, @JsonProperty("to") String to,
                             @JsonProperty("contacts") Optional<String> contacts,
                              @JsonProperty("location") Optional<String> location) {
        this.title = title;
        this.description = description;
        this.from = from;
        this.to = to;
        this.contacts = contacts;
        this.location = location;
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        title = source.getTitle().value;
        description = source.getDescription().value;
        from = source.getFrom().toString();
        to = source.getTo().toString();
        contacts = source.getContacts().value;
        location = source.getLocation().value;
    }

    /**
     * Converts this Jackson-friendly adapted meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType() throws IllegalValueException {
        // Title
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        // Description
        if (description.isPresent() && !Description.isValidDescription(description.get())) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        // From
        if (from == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, From.class.getSimpleName()));
        }
        if (!From.isValidFrom(from)) {
            throw new IllegalValueException(From.MESSAGE_CONSTRAINTS);
        }
        final From modelFrom = new From(from);

        // To
        if (to == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, To.class.getSimpleName()));
        }
        if (!To.isValidTo(to)) {
            throw new IllegalValueException(To.MESSAGE_CONSTRAINTS);
        }
        final To modelTo = new To(to);

        // Contacts
        if (contacts.isPresent() && !Contacts.isValidContacts(contacts.get())) {
            throw new IllegalValueException(Contacts.MESSAGE_CONSTRAINTS);
        }
        final Contacts modelContacts = new Contacts(contacts);

        // Location
        if (location.isPresent() && !Location.isValidLocation(location.get())) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        final Location modelLocation = new Location(location);

        Meeting meeting;

        try {
            meeting = new Meeting(modelTitle, modelDescription, modelFrom, modelTo, modelContacts, modelLocation);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(Meeting.INCORRECT_FROM_AND_TO_ORDER);
        }

        return meeting;
    }
}
