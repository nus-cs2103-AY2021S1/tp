package com.eva.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.eva.commons.exceptions.IllegalValueException;
import com.eva.model.EvaDatabase;
import com.eva.model.ReadOnlyEvaDatabase;
import com.eva.model.person.applicant.Applicant;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * An Immutable EvaDatabase that is serializable to JSON format.
 */
@JsonRootName(value = "applicantDatabase")
class JsonApplicantDatabase {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedApplicant> applicants = new ArrayList<>();

    /**
     * Constructs a {@code JsonApplicantDatabase} with the given persons.
     */
    @JsonCreator
    public JsonApplicantDatabase(@JsonProperty("applicants") List<JsonAdaptedApplicant> applicants) {
        this.applicants.addAll(applicants);
    }

    /**
     * Converts a given {@code ReadOnlyEvaDatabase} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonApplicantDatabase}.
     */
    public JsonApplicantDatabase(ReadOnlyEvaDatabase<Applicant> source) {
        applicants.addAll(source.getPersonList().stream().map(JsonAdaptedApplicant::new).collect(Collectors.toList()));
    }

    /**
     * Converts this eva database into the model's {@code EvaDatabase} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public EvaDatabase<Applicant> toModelType() throws IllegalValueException {
        EvaDatabase<Applicant> addressBook = new EvaDatabase<>();
        for (JsonAdaptedApplicant jsonAdaptedApplicant : applicants) {
            Applicant person = jsonAdaptedApplicant.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        return addressBook;
    }

}
