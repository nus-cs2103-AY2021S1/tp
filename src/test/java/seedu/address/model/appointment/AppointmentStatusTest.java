package seedu.address.model.appointment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppointmentStatusTest {

    @Test
    void isValidAppointmentStatus() {
        Assertions.assertEquals(true, AppointmentStatus.isValidAppointmentStatus("COMPLETED"));
        Assertions.assertEquals(false, AppointmentStatus.isValidAppointmentStatus("abc"));
    }
}
