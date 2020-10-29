package seedu.address.model.visit;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Objects;

import seedu.address.model.patient.Name;

/**
 * Represents a patient's visit in the CliniCal application.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Visit implements Comparable<Visit> {

    public static final String MESSAGE_CONSTRAINTS = "Visits should take date in valid format.";

    private LocalDate visitDate;
    private Name patientName;
    private String diagnosis;
    private String prescription;
    private String comment;

    /**
     * Instantiates a Visit object.
     */
    public Visit(LocalDate visitDate, Name patientName, String diagnosis, String prescription, String comment) {
        requireAllNonNull(visitDate, patientName, diagnosis, prescription, comment);
        this.visitDate = visitDate;
        this.patientName = patientName;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.comment = comment;
    }

    /**
     * Instantiates a Visit object.
     */
    public Visit(String value) {
        requireNonNull(value);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        visitDate = LocalDate.parse(value, formatter);
        patientName = new Name("null");
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public Name getPatientName() {
        return patientName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public String getComment() {
        return comment;
    }

    /**
     * Sets patient parameters in profile window.
     */
    public void setParameters(String diagnosis, String prescription, String comment) {
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.comment = comment;
    }

    /**
     * Sets patient name.
     */
    public void setPatientName(Name patientName) {
        this.patientName = patientName;
    }

    /**
     * Validates visit date in DD/MM/YYYY format.
     * YYYY must be 19xx or 2xxx.
     */
    public static boolean isValidVisitDate(String input) {
        DateTimeFormatter dateFormatAs19xx =
                DateTimeFormatter.ofPattern("dd/MM/19uu").withResolverStyle(ResolverStyle.STRICT);

        DateTimeFormatter dateFormatAs2xxx =
                DateTimeFormatter.ofPattern("dd/MM/2uuu").withResolverStyle(ResolverStyle.STRICT);

        try {
            LocalDate.parse(input, dateFormatAs19xx);
        } catch (DateTimeParseException exception) {
            try {
                LocalDate.parse(input, dateFormatAs2xxx);
            } catch (DateTimeParseException exception2) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if both visits have the same fields.
     * This defines a stronger notion of equality between two patients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Visit)) {
            return false;
        }

        Visit otherVisit = (Visit) other;
        return otherVisit.getVisitDate().equals(getVisitDate())
                && otherVisit.getPatientName().equals(getPatientName())
                && otherVisit.getDiagnosis().equals(getDiagnosis())
                && otherVisit.getPrescription().equals(getPrescription())
                && otherVisit.getComment().equals(getComment());
    }

    @Override
    public int compareTo(Visit otherVisit) {
        LocalDate currentVisitDate = this.getVisitDate();
        LocalDate otherVisitDate = otherVisit.getVisitDate();
        return currentVisitDate.compareTo(otherVisitDate);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(visitDate, patientName, diagnosis, prescription, comment);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getVisitDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return builder.toString();
    }
}
