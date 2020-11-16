package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.Tasker;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.person.Person;

/**
 * An Immutable TAsker that is serializable to JSON format.
 */
@JsonRootName(value = "tasker")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_CONSULTATION = "Consultation list contains duplicate consult(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedConsultation> consults = new ArrayList<>();
    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons & consultations.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("consults") List<JsonAdaptedConsultation> consults) {
        this.persons.addAll(persons);
        this.consults.addAll(consults);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        consults.addAll(source.getConsultationList()
                .stream()
                .map(JsonAdaptedConsultation::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code Tasker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Tasker toModelType() throws IllegalValueException {
        Tasker tasker = new Tasker();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (tasker.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            tasker.addPerson(person);
        }
        for (JsonAdaptedConsultation jsonAdaptedConsultation : consults) {
            Consultation consultation = jsonAdaptedConsultation.toModelType();
            if (tasker.hasConsultation(consultation)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONSULTATION);
            }
            tasker.addConsultation(consultation);
        }
        return tasker;
    }

}
