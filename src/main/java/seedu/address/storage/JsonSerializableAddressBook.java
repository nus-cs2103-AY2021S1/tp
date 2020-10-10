package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ClientList;
import seedu.address.model.ReadOnlyClientList;
import seedu.address.model.person.Person;

/**
 * An Immutable ClientList that is serializable to JSON format.
 */
@JsonRootName(value = "clientlist")
class JsonSerializableClientList {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableClientList} with the given persons.
     */
    @JsonCreator
    public JsonSerializableClientList(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyClientList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableClientList}.
     */
    public JsonSerializableClientList(ReadOnlyClientList source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this client list into the model's {@code ClientList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ClientList toModelType() throws IllegalValueException {
        ClientList clientList = new ClientList();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (clientList.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            clientList.addPerson(person);
        }
        return clientList;
    }

}
