package seedu.medibook.model.medicalnote;

import static seedu.medibook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.medibook.model.commonfields.Date;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.doctor.Mcr;

class MedicalNoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        // when date is null
        assertThrows(NullPointerException.class, () -> {
            new MedicalNote(null, new Doctor(new Name("Lydia Yu"), new Mcr("M12121B")),
                    new Content("Patient is asymptomatic."));
        });
        // when doctorName is null
        assertThrows(NullPointerException.class, () -> {
            new MedicalNote(new Date("15-04-2020", true), null,
                    new Content("Patient is asymptomatic."));
        });
        // when content is null
        assertThrows(NullPointerException.class, () -> {
            new MedicalNote(new Date("15-04-2020", true),
                    new Doctor(new Name("Lydia Yu"), new Mcr("M12121B")), null);
        });
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "15/04/20";
        assertThrows(IllegalArgumentException.class, () -> {
            new MedicalNote(new Date(invalidDate, true),
                    new Doctor(new Name("Lydia Yu"), new Mcr("M12121B")),
                    new Content("Patient is asymptomatic."));
        });
    }

    @Test
    public void constructor_invalidDoctorName_throwsIllegalArgumentException() {
        String invalidDoctorName = "";
        assertThrows(IllegalArgumentException.class, () -> {
            new MedicalNote(new Date("15-04-2020", true),
                    new Doctor(new Name(invalidDoctorName), new Mcr("M72369X")),
                    new Content("Patient is asymptomatic."));
        });
    }

    @Test
    public void constructor_invalidDoctorMcr_throwsIllegalArgumentException() {
        String invalidDoctorName = "";
        assertThrows(IllegalArgumentException.class, () -> {
            new MedicalNote(new Date("15-04-2020", true),
                    new Doctor(new Name("Lydia Yu"), new Mcr("P7212369X")),
                    new Content("Patient is asymptomatic."));
        });
    }

    @Test
    public void constructor_invalidContent_throwsIllegalArgumentException() {
        String invalidContent = "";
        assertThrows(IllegalArgumentException.class, () -> {
            new MedicalNote(new Date("15-04-2020", true),
                    new Doctor(new Name("Lydia Yu"), new Mcr("M72369X")),
                    new Content(invalidContent));
        });
    }
}
