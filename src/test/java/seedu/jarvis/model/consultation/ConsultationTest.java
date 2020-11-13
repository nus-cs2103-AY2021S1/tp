package seedu.jarvis.model.consultation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
// TODO: import consultations from testutil as well

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.jarvis.testutil.StudentBuilder;


public class ConsultationTest {
    private String studentName = new StudentBuilder().build().getName().fullName;
    private String studentName2 = "anyName";

    private Consultation normalConsultation = new Consultation(studentName, LocalDateTime.of(2020, 01,
            8, 13, 10));
    private Consultation consultationVariant1 = new Consultation(studentName2, LocalDateTime.of(2020, 01,
            8, 13, 10));
    private Consultation consultationVariant2 = new Consultation(studentName, LocalDateTime.of(2020, 01,
            9, 13, 10));


    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(normalConsultation.equals(normalConsultation));

        // null -> returns false
        assertFalse(normalConsultation.equals(null));

        // different date -> returns false
        assertFalse(normalConsultation.equals(consultationVariant1));

        // different name -> returns false
        assertFalse(normalConsultation.equals(consultationVariant2));
    }

    @Test
    public void conflictsWith() {
        // same dateandtime -> returns true
        assertTrue(normalConsultation.conflictsWith(consultationVariant1));

        // different dateandtime -> returns false
        assertFalse(normalConsultation.conflictsWith(consultationVariant2));
    }
}
