package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Description;
import seedu.address.model.person.Document;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Status;
import seedu.address.model.person.Suspect;
import seedu.address.model.person.Title;
import seedu.address.model.person.Victim;
import seedu.address.model.person.Witness;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String title;
    private final String description;
    private final String phone;
    private final String email;
    private final String status;
    private final List<JsonAdaptedDocument> documents = new ArrayList<>();
    private final List<JsonAdaptedSuspect> suspects = new ArrayList<>();
    private final List<JsonAdaptedVictim> victims = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedWitness> witnesses = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("title") String title, @JsonProperty("description") String description,
            @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("status") String status,
            @JsonProperty("documents") List<JsonAdaptedDocument> documents,
            @JsonProperty("suspects") List<JsonAdaptedSuspect> suspects,
            @JsonProperty("victims") List<JsonAdaptedVictim> victims,
            @JsonProperty("witnesses") List<JsonAdaptedWitness> witnesses,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {

        this.title = title;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.status = status;
        if (documents != null) {
            this.documents.addAll(documents);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (witnesses != null) {
            this.witnesses.addAll(witnesses);
        }
        if (suspects != null) {
            this.suspects.addAll(suspects);
        }
        if (victims != null) {
            this.victims.addAll(victims);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        title = source.getTitle().alphaNum;
        description = source.getDescription().alphaNum;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        status = source.getStatus().name();
        documents.addAll(source.getDocuments().stream()
                .map(JsonAdaptedDocument::new)
                .collect(Collectors.toList()));
        suspects.addAll(source.getSuspects().stream().map(JsonAdaptedSuspect::new).collect(Collectors.toList()));
        victims.addAll(source.getVictims().stream()
                .map(JsonAdaptedVictim::new)
                .collect(Collectors.toList()));
        witnesses.addAll(source.getWitnesses().stream()
                .map(JsonAdaptedWitness::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = Status.createStatus(status);

        final List<Suspect> modelSuspects = new ArrayList<>();
        for (JsonAdaptedSuspect suspect : suspects) {
            modelSuspects.add(suspect.toModelType());
        }

        final List<Victim> modelVictims = new ArrayList<>();
        for (JsonAdaptedVictim victim : victims) {
            modelVictims.add(victim.toModelType());
        }

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final List<Witness> modelWitnesses = new ArrayList<>();
        for (JsonAdaptedWitness witness : witnesses) {
            modelWitnesses.add(witness.toModelType());
        }

        final List<Document> modelDocument = new ArrayList<>();
        for (JsonAdaptedDocument document : documents) {
            modelDocument.add(document.toModelType());
        }

        return new Person(modelTitle, modelDescription, modelPhone, modelEmail, modelStatus, modelDocument,
                modelSuspects, modelVictims, modelWitnesses, modelTags);

    }

}
