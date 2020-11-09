package seedu.medibook.model.medicalnote;

import static seedu.medibook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.medibook.model.commonfields.Date;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.doctor.Mcr;

/**
 * Represents a medical note entry for a Patient in MediBook, on a particular date by a particular doctor.
 * Guarantees: details are present and not null, date and doctor's names are valid, fields are immutable.
 */
public class MedicalNote {
    /** Date of medical note entry. */
    public final Date date;
    /** Author of note. */
    public final Doctor doctor;
    /** Content of the note i.e. relevant information from patient's consultation. */
    public final Content content;

    /**
     * Constructs a {@code MedicalNote}.
     *
     * @param date String representation of a valid date of entry.
     * @param doctor A valid doctor's name.
     * @param content Contents of the note.
     */
    public MedicalNote(Date date, Doctor doctor, Content content) {
        requireAllNonNull(date, doctor, content);
        this.date = date;
        this.doctor = doctor;
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Name getDoctorName() {
        return doctor.getName();
    }

    public Mcr getDoctorMcr() {
        return doctor.getMcr();
    }

    public Content getContent() {
        return content;
    }

    public String getInputDateString() {
        return date.inputValue;
    }

    public String getDoctorNameString() {
        return doctor.getStringNameNoTitle();
    }

    public String getDoctorMcrString() {
        return doctor.getStringMcr();
    }

    public String getContentString() {
        return content.toString();
    }

    public boolean isAuthoredBy(Doctor testDoctor) {
        return doctor.equals(testDoctor);
    }

    @Override
    public String toString() {
        return "Date: " + date.outputValue
                + "\nDoctor: " + doctor
                + "\nContent: " + content;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MedicalNote)) {
            return false;
        }

        MedicalNote otherNote = (MedicalNote) other;
        return otherNote.date.equals(date)
                && otherNote.doctor.equals(doctor)
                && otherNote.content.equals(content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, doctor, content);
    }
}

