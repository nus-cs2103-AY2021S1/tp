package seedu.address.model.appointment;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.AppointmentBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_SECOND;
import static seedu.address.testutil.TypicalAppointments.FIRST;
import static seedu.address.testutil.TypicalAppointments.SECOND;

public class AppointmentTest {

    @Test
    public void isSameAppointment() {
        // same object -> returns true
        assertTrue(FIRST.isSameAppointment(FIRST));

        // null -> returns false
        assertFalse(FIRST.isSameAppointment(null));

        // different patient name -> returns false
        Appointment editedFirst = new AppointmentBuilder(FIRST).withPatientName(VALID_PATIENT_NAME_SECOND).build();
        assertFalse(FIRST.isSameAppointment(editedFirst));

        // same patient name and different time -> returns true
        editedFirst = new AppointmentBuilder(FIRST).withEndTime(VALID_END_TIME_SECOND).build();
        assertTrue(FIRST.isSameAppointment(editedFirst));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Appointment firstCopy = new AppointmentBuilder(FIRST).build();
        assertTrue(FIRST.equals(firstCopy));

        // same object -> returns true
        assertTrue(FIRST.equals(FIRST));

        // null -> returns false
        assertFalse(FIRST.equals(null));

        // different type -> returns false
        assertFalse(FIRST.equals(5));

        // different patient -> returns false
        assertFalse(FIRST.equals(SECOND));

        // different patient name -> returns false
        Appointment editedFirst = new AppointmentBuilder(FIRST).withPatientName(VALID_PATIENT_NAME_SECOND).build();
        assertFalse(FIRST.equals(editedFirst));

        // different start time -> returns false
        editedFirst = new AppointmentBuilder(FIRST).withStartTime(VALID_START_TIME_SECOND).build();
        assertFalse(FIRST.equals(editedFirst));

        // different end time -> returns false
        editedFirst = new AppointmentBuilder(FIRST).withEndTime(VALID_END_TIME_SECOND).build();
        assertFalse(FIRST.equals(editedFirst));

    }
}
