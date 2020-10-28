package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.clientsource.ClientSource;
import seedu.address.model.note.Note;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Priority;
import seedu.address.model.policy.Policy;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedClientSource> clientSource = new ArrayList<>();
    private final String note;
    private final boolean isArchive;
    private final String priority;
    private final JsonAdaptedPolicy policy;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(
            @JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("clientSource") List<JsonAdaptedClientSource> clientSource,
            @JsonProperty("note") String note, @JsonProperty("isArchive") boolean isArchive,
            @JsonProperty("priority") String priority, @JsonProperty("policy") JsonAdaptedPolicy policy) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (clientSource != null) {
            this.clientSource.addAll(clientSource);
        }
        this.note = note;
        this.isArchive = isArchive;
        this.priority = priority;
        this.policy = policy;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        assert name != null : "Value stored in name should not be null";

        if (source.getPhone() != null) {
            phone = source.getPhone().value;
            assert phone != null : "Value stored in phone should not be null";
        } else {
            phone = null;
        }

        if (source.getEmail() != null) {
            email = source.getEmail().value;
            assert email != null : "Value stored in email should not be null";
        } else {
            email = null;
        }

        if (source.getAddress() != null) {
            address = source.getAddress().value;
            assert address != null : "Value stored in address should not be null";
        } else {
            address = null;
        }

        clientSource.addAll(source.getClientSources()
                .stream()
                .map(JsonAdaptedClientSource::new)
                .collect(Collectors.toList()));

        if (source.getNote() != null) {
            note = source.getNote().noteName;
            assert note != null : "Value stored in note should not be null";
        } else {
            note = null;
        }

        isArchive = source.getIsArchive();

        priority = source.getPriority().value;
        assert priority != null : "Value stored in priority should not be null";

        if (source.getPolicy() != null) {
            policy = new JsonAdaptedPolicy(source.getPolicy());
            assert policy != null : "Value stored in address should not be null";
        } else {
            policy = null;
        }
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<ClientSource> personClientSources = new ArrayList<>();
        for (JsonAdaptedClientSource clientSource : clientSource) {
            personClientSources.add(clientSource.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        final Phone modelPhone;
        if (phone == null) {
            modelPhone = null;
        } else {
            if (!Phone.isValidPhone(phone)) {
                throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
            }
            modelPhone = new Phone(phone);
        }

        final Email modelEmail;
        if (email == null) {
            modelEmail = null;
        } else {
            if (!Email.isValidEmail(email)) {
                throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
            }
            modelEmail = new Email(email);
        }

        final Address modelAddress;
        if (address == null) {
            modelAddress = null;
        } else {
            if (!Address.isValidAddress(address)) {
                throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
            }
            modelAddress = new Address(address);
        }

        final Set<ClientSource> modelClientSources = new HashSet<>(personClientSources);

        final Note modelNote;
        if (note == null) {
            modelNote = null;
        } else {
            if (!Note.isValidNoteName(note)) {
                throw new IllegalValueException(Note.MESSAGE_CONSTRAINTS);
            }
            modelNote = new Note(note);
        }

        final boolean modelIsArchive = isArchive;

        final Priority modelPriority;
        if (priority == null) {
            modelPriority = new Priority(null);
        } else {
            if (!Priority.isValidPriority(priority)) {
                throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
            }

            modelPriority = new Priority(priority);
        }

        final Policy modelPolicy;
        if (policy != null) {
            modelPolicy = policy.toModelType();
        } else {
            modelPolicy = null;
        }

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelClientSources, modelNote,
                modelIsArchive, modelPriority, modelPolicy);
    }

}
