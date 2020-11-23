package seedu.address.model.appointment;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppointmentIdTest {

    @Test
    void testEquals() {
        AppointmentId apptId = new AppointmentId(Integer.toString(1));

        //same object, true
        Assertions.assertEquals(true, apptId.equals(apptId));

        //same value, true
        Assertions.assertEquals(true, apptId.equals(new AppointmentId(Integer.toString(1))));

        //different value, false
        Assertions.assertEquals(false, apptId.equals(new AppointmentId(Integer.toString(2))));
    }
}
