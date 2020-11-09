package seedu.medibook.model.medicalnote;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.medibook.model.commonfields.Date;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.doctor.Mcr;

class MedicalNoteComparatorTest {
    private final MedicalNoteComparator comparator = new MedicalNoteComparator();

    private final MedicalNote medicalNote1 =
            new MedicalNote(new Date("19-02-2020", true),
                    new Doctor(new Name("John"), new Mcr("M16906X")),
                    new Content("Patient is sick."));
    private final MedicalNote medicalNote2 =
            new MedicalNote(new Date("19-02-2020", true),
                    new Doctor(new Name("John"), new Mcr("M16906X")),
                    new Content("Patient is sick."));
    private final MedicalNote medicalNote3 =
            new MedicalNote(new Date("19-02-2020", true),
                    new Doctor(new Name("Mary"), new Mcr("MQ7260X")),
                    new Content("Patient is healthy."));
    private final MedicalNote medicalNote4 =
            new MedicalNote(new Date("30-12-2019", true),
                    new Doctor(new Name("John"), new Mcr("M16906X")),
                    new Content("Patient is sick."));
    private final MedicalNote medicalNote5 =
            new MedicalNote(new Date("30-12-2019", true),
                    new Doctor(new Name("Mary"), new Mcr("MQ7260X")),
                    new Content("Patient is healthy."));

    @Test
    void compare_sameDateSameFields_zero() {
        assertTrue(comparator.compare(medicalNote1, medicalNote2) == 0);
    }

    @Test
    void compare_sameDateDiffDoctor_zero() {
        assertTrue(comparator.compare(medicalNote1, medicalNote3) < 0);
    }

    @Test
    void compare_earlierDateSameFields_positive() {
        assertTrue(comparator.compare(medicalNote4, medicalNote1) > 0);
    }

    @Test
    void compare_earlierDateDiffFields_positive() {
        assertTrue(comparator.compare(medicalNote5, medicalNote1) > 0);
    }

    @Test
    void compare_laterDateSameFields_negative() {
        assertTrue(comparator.compare(medicalNote1, medicalNote4) < 0);
    }

    @Test
    void compare_laterDateDiffFields_negative() {
        assertTrue(comparator.compare(medicalNote1, medicalNote5) < 0);
    }
}
