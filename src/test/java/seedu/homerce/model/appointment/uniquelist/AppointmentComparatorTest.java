package seedu.homerce.model.appointment.uniquelist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.homerce.testutil.appointment.TypicalAppointments.OCT_25_2020_230PM_LASH_LIFT_BENSON;
import static seedu.homerce.testutil.appointment.TypicalAppointments.OCT_25_2020_2PM_HAIR_TREATMENT_ALICE;
import static seedu.homerce.testutil.appointment.TypicalAppointments.OCT_26_2020_11AM_MANICURE_CARL;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.appointment.Appointment;

public class AppointmentComparatorTest {
    @Test
    public void compare() {
        AppointmentComparator comparator = new AppointmentComparator();
        Appointment firstAppointment = OCT_25_2020_2PM_HAIR_TREATMENT_ALICE;
        Appointment secondAppointment = OCT_25_2020_230PM_LASH_LIFT_BENSON;
        assertEquals(comparator.compare(secondAppointment, firstAppointment), 1); // later time
        assertEquals(comparator.compare(firstAppointment, secondAppointment), -1); // earlier time
        Appointment thirdAppointment = OCT_26_2020_11AM_MANICURE_CARL;
        assertEquals(comparator.compare(thirdAppointment, secondAppointment), 1); // subsequent date
        assertEquals(comparator.compare(firstAppointment, thirdAppointment), -1); // earlier date
        assertEquals(comparator.compare(thirdAppointment, thirdAppointment), 0); //Exact same date and time
    }
}
