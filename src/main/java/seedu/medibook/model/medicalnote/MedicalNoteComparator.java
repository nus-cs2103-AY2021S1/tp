package seedu.medibook.model.medicalnote;

import java.util.Comparator;

import seedu.medibook.model.commonfields.Date;
import seedu.medibook.model.doctor.Doctor;

/**
 * A comparator of medical notes that works with sort to give a list of medical notes from most recent to least recent.
 * Medical notes are first sorted by date. If date is tied, it is sorted by doctor's name (lexicographic and ignoring
 * case). If doctor's name is tied, it is sorted by content (lexicographic and ignoring case).
 */
public class MedicalNoteComparator implements Comparator<MedicalNote> {
    @Override
    public int compare(MedicalNote o1, MedicalNote o2) {
        Date date1 = o1.date;
        Date date2 = o2.date;
        int compareDateResult = date2.compareTo(date1); // reverse order for most recent to least recent

        // different date
        if (compareDateResult != 0) {
            return compareDateResult;
        }

        // same date, sort by doctor name (lexicographic, ignoring case)
        Doctor doctor1 = o1.doctor;
        Doctor doctor2 = o2.doctor;

        int compareDoctorResult = doctor1.compareTo(doctor2);

        if (compareDoctorResult != 0) {
            return compareDoctorResult;
        }

        // same date, same doctor name, sort by content (lexicographic, ignoring case)
        Content content1 = o1.content;
        Content content2 = o2.content;

        int compareContentResult = content1.compareTo(content2);

        return compareContentResult;
    }
}
