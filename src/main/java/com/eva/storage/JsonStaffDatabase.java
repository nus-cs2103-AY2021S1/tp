package com.eva.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.eva.commons.exceptions.IllegalValueException;
import com.eva.model.EvaDatabase;
import com.eva.model.ReadOnlyEvaDatabase;
import com.eva.model.person.staff.Staff;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * An Immutable EvaDatabase that is serializable to JSON format.
 */
@JsonRootName(value = "staffDatabase")
class JsonStaffDatabase {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedStaff> staffs = new ArrayList<>();

    /**
     * Constructs a {@code JsonPersonDatabase} with the given persons.
     */
    @JsonCreator
    public JsonStaffDatabase(@JsonProperty("staffs") List<JsonAdaptedStaff> staffs) {
        this.staffs.addAll(staffs);
    }

    /**
     * Converts a given {@code ReadOnlyEvaDatabase} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonPersonDatabase}.
     */
    public JsonStaffDatabase(ReadOnlyEvaDatabase<Staff> source) {
        staffs.addAll(source.getPersonList().stream().map(JsonAdaptedStaff::new).collect(Collectors.toList()));
    }

    /**
     * Converts this eva database for staff into the model's {@code EvaDatabase} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public EvaDatabase<Staff> toModelType() throws IllegalValueException {
        EvaDatabase<Staff> addressBook = new EvaDatabase<>();
        for (JsonAdaptedStaff jsonAdaptedStaff : staffs) {
            Staff person = jsonAdaptedStaff.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        return addressBook;
    }

}
