package seedu.address.testutil;

import static seedu.address.testutil.PatientBuilder.DEFAULT_NAME;

import java.time.LocalDate;

import seedu.address.model.patient.Name;
import seedu.address.model.visit.Visit;

/**
 * A utility class to help with building Visit objects.
 */
public class VisitBuilder {

    public static final LocalDate DEFAULT_VISIT_DATE = LocalDate.of(2020, 11, 11);

    public static final String DEFAULT_VISIT_DIAGNOSIS = "Asthma";
    public static final String DEFAULT_VISIT_PRESCRIPTION = "Nasal Spray";
    public static final String DEFAULT_VISIT_COMMENT = "Follow up in 2 weeks";

    private LocalDate visitDate;
    private Name patientName;
    private String diagnosis;
    private String prescription;
    private String comment;

    /**
     * Creates a {@code VisitBuilder} with the default details.
     */
    public VisitBuilder() {
        visitDate = DEFAULT_VISIT_DATE;
        patientName = new Name(DEFAULT_NAME);
        diagnosis = DEFAULT_VISIT_DIAGNOSIS;
        prescription = DEFAULT_VISIT_PRESCRIPTION;
        comment = DEFAULT_VISIT_COMMENT;
    }

    /**
     * Initializes the VisitBuilder with the data of {@code visitToCopy}.
     */
    public VisitBuilder(Visit visitToCopy) {
        visitDate = visitToCopy.getVisitDate();
        patientName = visitToCopy.getPatientName();
        diagnosis = visitToCopy.getDiagnosis();
        prescription = visitToCopy.getPrescription();
        comment = visitToCopy.getComment();
    }

    /**
     * Sets the {@code visitDate} of the {@code Visit} that we are building.
     */
    public VisitBuilder withVisitDate(LocalDate date) {
        this.visitDate = date;
        return this;
    }

    /**
     * Sets the {@code patientName} of the {@code Visit} that we are building.
     */
    public VisitBuilder withPatientName(String patientName) {
        this.patientName = new Name(patientName);
        return this;
    }

    /**
     * Sets the {@code diagnosis} of the {@code Visit} that we are building.
     */
    public VisitBuilder withDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
        return this;
    }

    /**
     * Sets the {@code prescription} of the {@code Visit} that we are building.
     */
    public VisitBuilder withPrescription(String prescription) {
        this.prescription = prescription;
        return this;
    }

    /**
     * Sets the {@code comment} of the {@code Visit} that we are building.
     */
    public VisitBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }

    /**
     * Builds {@code Patient} with the given fields.
     */
    public Visit build() {
        return new Visit(visitDate, patientName, diagnosis, prescription, comment);
    }

}
