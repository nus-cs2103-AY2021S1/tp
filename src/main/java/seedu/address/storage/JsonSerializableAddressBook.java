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
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_MODULE = "Modules list contains duplicate module(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedModule> semOneModules = new ArrayList<>();
    private final List<JsonAdaptedModule> semTwoModules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons and modules.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("semOneModules") List<JsonAdaptedModule> semOneModules,
                                       @JsonProperty("semTwoModules") List<JsonAdaptedModule> semTwoModules) {
        if (persons != null) {
            this.persons.addAll(persons);
        }
        if (semOneModules != null) {
            this.semOneModules.addAll(semOneModules);
        }
        if (semTwoModules != null) {
            this.semTwoModules.addAll(semTwoModules);
        }
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        semOneModules.addAll(source.getSemOneModuleList().stream()
                .map(JsonAdaptedModule::new).collect(Collectors.toList()));
        semTwoModules.addAll(source.getSemTwoModuleList().stream()
                .map(JsonAdaptedModule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }

        for (JsonAdaptedModule jsonAdaptedModule : semOneModules) {
            Module module = jsonAdaptedModule.toModelType();
            if (addressBook.hasSemOneModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            addressBook.addSemOneModule(module);
        }

        for (JsonAdaptedModule jsonAdaptedModule : semTwoModules) {
            Module module = jsonAdaptedModule.toModelType();
            if (addressBook.hasSemTwoModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            addressBook.addSemTwoModule(module);
        }
        return addressBook;
    }

}
