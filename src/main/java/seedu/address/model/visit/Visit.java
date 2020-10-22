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

    public String getComment() {
        return comment;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getPrescription() {
        return prescription;
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
        builder.append("-----------------\n")
            .append("Date: ")
            .append(getVisitDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .append("\nDiagnosis: ")
            .append(getDiagnosis())
            .append("\nPrescription: ")
            .append(getPrescription())
            .append("\nComment: ")
            .append(getComment()).append("\n\n");
        return builder.toString();
    }

    /**
     * Returns true if visit date is valid.
     */
    public static boolean isValidVisitDate(String input) {

        //make sure month and day are valid and year is 2xxx or 19xx
        DateTimeFormatter dateFormatter1 =
            DateTimeFormatter.ofPattern("dd/MM/2uuu").withResolverStyle(ResolverStyle.STRICT);

        DateTimeFormatter dateFormatter2 =
            DateTimeFormatter.ofPattern("dd/MM/19uu").withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate.parse(input, dateFormatter1);
        } catch (DateTimeParseException e) {
            try {
                LocalDate.parse(input, dateFormatter2);
            } catch (DateTimeParseException e2) {
                return false;
            }
        }
        return true;
    }

    public void setFields(String diagnosis, String prescription, String comment) {
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.comment = comment;
    }

    public void setPatientName(Name patientName) {
        this.patientName = patientName;
    }
}
