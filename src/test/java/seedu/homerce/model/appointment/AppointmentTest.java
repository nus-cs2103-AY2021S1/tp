package seedu.homerce.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.appointment.TypicalAppointments.OCT_25_2020_230PM_LASH_LIFT_BENSON;
import static seedu.homerce.testutil.appointment.TypicalAppointments.OCT_25_2020_2PM_HAIR_TREATMENT_ALICE;

import org.junit.jupiter.api.Test;

import seedu.homerce.testutil.appointment.AppointmentBuilder;

public class AppointmentTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            new Appointment(null, null, null, null)
        );
    }

    @Test
    public void isSameAppointment() {
        Appointment firstAppointment = OCT_25_2020_2PM_HAIR_TREATMENT_ALICE;
        Appointment secondAppointment = OCT_25_2020_230PM_LASH_LIFT_BENSON;
        Appointment thirdAppointment = new AppointmentBuilder(firstAppointment).withDate("25-11-2020").build();
        assertTrue(firstAppointment.isSame(new AppointmentBuilder(firstAppointment).build())); // Same object
        assertTrue(firstAppointment.isSame(secondAppointment)); // Clashing time period
        assertFalse(firstAppointment.isSame(null)); // handle null items
        assertFalse(firstAppointment.isSame(thirdAppointment)); // different month should not clash
        // change time to non-clashing time
        secondAppointment = new AppointmentBuilder(secondAppointment).withTimeOfDay("1330").build();
        assertFalse(secondAppointment.isSame(firstAppointment));
    }

    @Test
    public void equals() {
        Appointment firstAppointment = new AppointmentBuilder().build();
        Appointment secondAppointment = new AppointmentBuilder(firstAppointment).build();
        Appointment thirdAppointment = new AppointmentBuilder(firstAppointment).withDate("12-10-2020").build();
        //same object
        assertTrue(firstAppointment.equals(firstAppointment));
        // same values
        assertTrue(firstAppointment.equals(secondAppointment));
        // different types
        assertFalse(firstAppointment.equals(1));
        assertFalse(firstAppointment.equals(null));
        // different values
        assertFalse(firstAppointment.equals(thirdAppointment));
    }

    @Test
    public void markDone_andMarkUndone() {
        Appointment toTest = new AppointmentBuilder(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE).build();
        assertFalse(toTest.getStatus().isDone());
        toTest.markDone();
        assertTrue(toTest.getStatus().isDone());
        toTest.markUnDone();
        assertFalse(toTest.getStatus().isDone());
    }

    @Test
    public void getClient() {

    }

    @Test
    public void getService() {

    }

    @Test
    public void getDate() {

    }

    @Test
    public void getStatus() {}


    @Test
    public void getTime_returns_correctTimeOfDay() {
        Appointment appointment = OCT_25_2020_2PM_HAIR_TREATMENT_ALICE;
        TimeOfDay startTime = new TimeOfDay("1400");
        TimeOfDay endTime = new TimeOfDay("1600");
        assertEquals(appointment.getAppointmentStartTime(), startTime);
        assertEquals(appointment.getAppointmentEndTime(), endTime);
    }

    @Test
    public void testHashCode() {
        Appointment editedAppointment = new AppointmentBuilder().withDate("12-10-2020").build();
        assertEquals(
            OCT_25_2020_2PM_HAIR_TREATMENT_ALICE.hashCode(),
            OCT_25_2020_2PM_HAIR_TREATMENT_ALICE.hashCode()
        );
        assertNotEquals(
            OCT_25_2020_2PM_HAIR_TREATMENT_ALICE.hashCode(),
            OCT_25_2020_230PM_LASH_LIFT_BENSON.hashCode()
        );
        assertNotEquals(
            OCT_25_2020_2PM_HAIR_TREATMENT_ALICE.hashCode(),
            editedAppointment.hashCode()
        );
    }

    @Test
    public void testToString() {
        assertEquals(
            OCT_25_2020_2PM_HAIR_TREATMENT_ALICE.toString(),
            "25-10-2020 1400 Client: Alice Pauline Phone: 94351253 "
                + "Email: alice@example.com Tags: [friends] Service: Hair Treatment"
                + " Duration: 2.0 Amount: 55.50 Service Code: SC001 Done? No"
        );
        assertNotEquals(
            OCT_25_2020_2PM_HAIR_TREATMENT_ALICE.toString(),
            OCT_25_2020_230PM_LASH_LIFT_BENSON.toString()
        );
    }
}
