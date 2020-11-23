package seedu.address.model.appointment;


import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class AppointmentTimeTest {

    @Test
    void isValidAppointmentTime() {
        new AppointmentTime("00:00");
        assertThrows(IllegalArgumentException.class, () -> new AppointmentTime("24:00"));
        assertThrows(IllegalArgumentException.class, () -> new AppointmentTime("24:01"));
    }
}
