package seedu.address.model.appointment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppointmentDateTest {

    @Test
    void isValidAppointmentDate() {
        Assertions.assertEquals(true, AppointmentDate.isValidAppointmentDate("2020-01-01"));
        Assertions.assertEquals(false, AppointmentDate.isValidAppointmentDate("2020-31-01"));
    }
}
