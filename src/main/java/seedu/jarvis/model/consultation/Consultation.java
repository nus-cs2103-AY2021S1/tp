package seedu.jarvis.model.consultation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;

/**
 * Represents a Consultation that contains information relating to general consultations.
 */
public class Consultation {
    private String studentName;
    private LocalDateTime dateAndTime;

    /**
     * Creates a {@code Consultation} with the given parameters.
     * @param studentName the name of the subject of this consultation
     * @param dateAndTime date and time of the consultation
     */
    public Consultation(String studentName, LocalDateTime dateAndTime) {
        this.studentName = studentName;
        this.dateAndTime = dateAndTime;
    }

    public String getStudentName() {
        return studentName;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public String getFormattedDateTime() {
        return dateAndTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    }

    public String getIdentifier() {
        return studentName + dateAndTime.toString();
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, dateAndTime);
    }


    /**
     * Returns true if both consultations have the same identity and data fields, not including the place of meeting.
     */
    public boolean isSameConsultation(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Consultation)) {
            return false;
        }

        Consultation otherConsultation = (Consultation) other;
        return otherConsultation.getStudentName().equals(getStudentName())
                && otherConsultation.getDateAndTime().equals(getDateAndTime());
    }

    /**
     * Returns true if both consultations have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Consultation)) {
            return false;
        }

        Consultation otherConsultation = (Consultation) other;
        return otherConsultation.getStudentName().equals(getStudentName())
                && otherConsultation.getDateAndTime().equals(getDateAndTime());
    }

    /**
     * Returns true if both consultations occur at the same date and time.
     */
    public boolean conflictsWith(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Consultation)) {
            return false;
        }

        Consultation otherConsultation = (Consultation) other;
        return otherConsultation.getDateAndTime().equals(getDateAndTime());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Consultation with ");
        sb.append(getStudentName());
        sb.append(" [");
        LocalDateTime date = getDateAndTime();
        sb.append(date.getDayOfMonth() + " " + date.getMonth() + ", " + date.getYear() + " @ ");
        sb.append(date.getHour() + ":" + date.getMinute() + "]");

        return sb.toString();
    }
}
