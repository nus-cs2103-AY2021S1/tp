package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_FIRST;
import static seedu.address.testutil.TypicalAppointments.CONFLICTING_APPOINTMENT1;
import static seedu.address.testutil.TypicalAppointments.CONFLICTING_APPOINTMENT2;
import static seedu.address.testutil.TypicalAppointments.FIRST_APP;
import static seedu.address.testutil.TypicalAppointments.NOCONFLICT_APPOINTMENT1;
import static seedu.address.testutil.TypicalAppointments.NOCONFLICT_APPOINTMENT2;
import static seedu.address.testutil.TypicalAppointments.SECOND_APP;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentBuilder;

public class AppointmentTest {

    @Test
    public void isSameAppointment() {
        // same object -> returns true
        assertTrue(FIRST_APP.isSameAppointmentTime(FIRST_APP));

        // null -> returns false
        assertFalse(FIRST_APP.isSameAppointmentTime(null));

        // different patient name -> returns true
        Appointment editedFirst = new AppointmentBuilder(FIRST_APP).withPatientName(VALID_PATIENT_NAME_SECOND).build();
        assertTrue(FIRST_APP.isSameAppointmentTime(editedFirst));

        // same patient name and different time -> returns false
        editedFirst = new AppointmentBuilder(FIRST_APP).withEndTime(VALID_END_TIME_SECOND).build();
        assertFalse(FIRST_APP.isSameAppointmentTime(editedFirst));

    }

    @Test
    public void compare() {
        assertTrue(Appointment.compare(FIRST_APP, SECOND_APP) <= 1);
        assertTrue(Appointment.compare(SECOND_APP, FIRST_APP) >= 1);
    }

    @Test
    public void hasTimeConflict() {
        assertTrue(CONFLICTING_APPOINTMENT1.hasTimeConflict(CONFLICTING_APPOINTMENT2));
        assertTrue(CONFLICTING_APPOINTMENT2.hasTimeConflict(CONFLICTING_APPOINTMENT1));

        assertFalse(NOCONFLICT_APPOINTMENT1.hasTimeConflict(NOCONFLICT_APPOINTMENT2));
        assertFalse(NOCONFLICT_APPOINTMENT2.hasTimeConflict(NOCONFLICT_APPOINTMENT1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Appointment firstCopy = new AppointmentBuilder(FIRST_APP).build();
        assertTrue(FIRST_APP.equals(firstCopy));

        // same object -> returns true
        assertTrue(FIRST_APP.equals(FIRST_APP));

        // null -> returns false
        assertFalse(FIRST_APP.equals(null));

        // different type -> returns false
        assertFalse(FIRST_APP.equals(5));

        // different patient -> returns false
        assertFalse(FIRST_APP.equals(SECOND_APP));

        // different patient name -> returns false
        Appointment editedFirst = new AppointmentBuilder(FIRST_APP).withPatientName(VALID_PATIENT_NAME_SECOND).build();
        assertFalse(FIRST_APP.equals(editedFirst));

        // different start time -> returns false
        editedFirst = new AppointmentBuilder(SECOND_APP).withStartTime(VALID_START_TIME_FIRST).build();
        assertFalse(FIRST_APP.equals(editedFirst));

        // different end time -> returns false
        editedFirst = new AppointmentBuilder(FIRST_APP).withEndTime(VALID_END_TIME_SECOND).build();
        assertFalse(FIRST_APP.equals(editedFirst));

    }
}
