package com.eva.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.eva.commons.exceptions.IllegalValueException;
import com.eva.model.EvaDatabase;
import com.eva.model.ReadOnlyEvaDatabase;
import com.eva.model.ReadOnlyEvaDatabase;
import com.eva.model.person.Person;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * An Immutable EvaDatabase that is serializable to JSON format.
 */
@JsonRootName(value = "evaDatabase")
class JsonSerializableEvaDatabase {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableEvaDatabase} with the given persons.
     */
    @JsonCreator
    public JsonSerializableEvaDatabase(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyEvaDatabase} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableEvaDatabase}.
     */
    public JsonSerializableEvaDatabase(ReadOnlyEvaDatabase source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code EvaDatabase} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public EvaDatabase toModelType() throws IllegalValueException {
        EvaDatabase addressBook = new EvaDatabase();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        return addressBook;
    }

}
