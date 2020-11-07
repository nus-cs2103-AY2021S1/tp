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
        phone = adaptPhoneToJson(source.getPhone());
        email = adaptEmailToJson(source.getEmail());
        address = adaptAddressToJson(source.getAddress());

        clientSource.addAll(source.getClientSources()
                .stream()
                .map(JsonAdaptedClientSource::new)
                .collect(Collectors.toList()));

        note = adaptNoteToJson(source.getNote());
        isArchive = source.getIsArchive();
        priority = source.getPriority().value;
        policy = adaptPolicyToJson(source.getPolicy());
    }

    private String adaptPhoneToJson (Phone phone) {
        if (phone == null) {
            return null;
        }
        String jsonAdaptedPhone = phone.value;
        return jsonAdaptedPhone;
    }

    private String adaptEmailToJson (Email email) {
        if (email == null) {
            return null;
        }
        String jsonAdaptedEmail = email.value;
        return jsonAdaptedEmail;
    }

    private String adaptAddressToJson (Address address) {
        if (address == null) {
            return null;
        }
        String jsonAdaptedAddress = address.value;
        return jsonAdaptedAddress;
    }

    private String adaptNoteToJson (Note note) {
        if (note == null) {
            return null;
        }
        String jsonAdaptedNote = note.noteName;
        return jsonAdaptedNote;
    }

    private JsonAdaptedPolicy adaptPolicyToJson (Policy policy) {
        if (policy == null) {
            return null;
        }
        JsonAdaptedPolicy jsonAdaptedPolicy = new JsonAdaptedPolicy(policy);
        return jsonAdaptedPolicy;
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

        final Name modelName = nameToModelType(name);
        final Phone modelPhone = phoneToModelType(phone);
        final Email modelEmail = emailToModelType(email);
        final Address modelAddress = addressToModelType(address);
        final Set<ClientSource> modelClientSources = new HashSet<>(personClientSources);
        final Note modelNote = noteToModelType(note);
        final boolean modelIsArchive = isArchive;
        final Priority modelPriority = priorityToModelType(priority);
        final Policy modelPolicy = policyToModelType(policy);

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelClientSources, modelNote,
                modelIsArchive, modelPriority, modelPolicy);
    }

    private Name nameToModelType(String name) throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        Name modelName = new Name(name);
        return modelName;
    }

    private Phone phoneToModelType(String phone) throws IllegalValueException {
        if (phone == null) {
            return null;
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        Phone modelPhone = new Phone(phone);
        return modelPhone;
    }

    private Email emailToModelType(String email) throws IllegalValueException {
        if (email == null) {
            return null;
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        Email modelEmail = new Email(email);
        return modelEmail;
    }

    private Address addressToModelType(String address) throws IllegalValueException {
        if (address == null) {
            return null;
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        Address modelAddress = new Address(address);
        return modelAddress;
    }

    private Note noteToModelType(String note) throws IllegalValueException {
        if (note == null) {
            return null;
        }
        if (!Note.isValidNoteName(note)) {
            throw new IllegalValueException(Note.MESSAGE_CONSTRAINTS);
        }
        Note modelNote = new Note(note);
        return modelNote;
    }

    private Priority priorityToModelType(String priority) throws IllegalValueException {
        if (priority == null) {
            Priority modelPriority = new Priority(null);
            return modelPriority;
        }
        if (!Priority.isValidPriority(priority)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }
        Priority modelPriority = new Priority(priority);
        return modelPriority;
    }

    private Policy policyToModelType(JsonAdaptedPolicy policy) throws IllegalValueException {
        if (policy == null) {
            return null;
        }
        Policy modelPolicy = policy.toModelType();
        return modelPolicy;
    }

}
