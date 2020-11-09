package seedu.medibook.model.doctor;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.medibook.model.commonfields.Name;

class DoctorTest {

    @Test
    public void constructor_nullName_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> new Doctor(null, new Mcr("M09298K")));
    }

    @Test
    public void constructor_nullNcr_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> new Doctor(new Name("Bethany"), null));
    }

    @Test
    void equals() {
        Doctor doctor1 = new Doctor(new Name("Brandon"), new Mcr("MP2037X"));
        Doctor doctor2 = new Doctor(new Name("Brandon"), new Mcr("MP2037X"));
        assertTrue(doctor1.equals(doctor2));
    }
}
