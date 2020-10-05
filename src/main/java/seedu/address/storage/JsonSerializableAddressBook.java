package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.animal.Animal;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_ANIMAL = "Animals list contains duplicate animal(s).";

    private final List<JsonAdaptedAnimal> animals = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given animals.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("animals") List<JsonAdaptedAnimal> animals) {
        this.animals.addAll(animals);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        animals.addAll(source.getAnimalList().stream().map(JsonAdaptedAnimal::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedAnimal jsonAdaptedAnimal : animals) {
            Animal animal = jsonAdaptedAnimal.toModelType();
            if (addressBook.hasAnimal(animal)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ANIMAL);
            }
            addressBook.addAnimal(animal);
        }
        return addressBook;
    }

}
