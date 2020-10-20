package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.patient.Name;
import seedu.address.model.visit.Visit;

/**
 * Jackson-friendly version of {@link Visit}.
 */
class JsonAdaptedVisit {

    private final LocalDate visitDate;
    private final Name patientName;
    private final String diagnosis;
    private final String prescription;
    private final String comment;

    /**
     * Constructs a {@code JsonAdaptedVisit} with the given visit details.
     */
    @JsonCreator
    public JsonAdaptedVisit(@JsonProperty LocalDate visitDate,
                            @JsonProperty Name patientName,
                            @JsonProperty String diagnosis,
                            @JsonProperty String prescription,
                            @JsonProperty String comment) {
        this.visitDate = visitDate;
        this.patientName = patientName;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.comment = comment;
    }

    /**
     * Converts a given {@code Visit} into this class for Jackson use.
     */
    public JsonAdaptedVisit(Visit source) {
        visitDate = source.getVisitDate();
        patientName = source.getPatientName();
        diagnosis = source.getDiagnosis();
        prescription = source.getPrescription();
        comment = source.getComment();
    }

    /**
     * Converts this Jackson-friendly adapted Visit object into the model's {@code Visit} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted allergy.
     */
    public Visit toModelType() throws IllegalValueException {
        if (!Visit.isValidVisitDate(visitDate.toString())) {
            throw new IllegalValueException(Visit.MESSAGE_CONSTRAINTS);
        }
        return new Visit(visitDate, new Name(patientName.toString()), diagnosis, prescription, comment);
    }

}
