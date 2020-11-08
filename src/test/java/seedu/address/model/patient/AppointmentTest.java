package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class AppointmentTest {

    public final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
    public final String validTimeString = "25/12/2025 20:00";
    public final LocalDateTime validTime = LocalDateTime.parse(validTimeString, formatter);
    public final String validDescription = "Eye checking";

    @Test
    public void constructor_nullDescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, validTime));
    }

    @Test
    public void setTime() {
        String timeString = validTimeString;
        LocalDateTime time = validTime;
        assertTrue(new Appointment().setTime(timeString).getAppointmentTime().equals(time));
    }

    @Test
    public void setDescription() {
        assertTrue(new Appointment().setDescription(validDescription)
                .getAppointmentDescription().equals(validDescription));
    }

    @Test
    public void isValidAppointment() {
        // null appointment
        assertThrows(NullPointerException.class, () -> Appointment.isValidDateTime(null));

        // blank appointment
        assertFalse(Appointment.isValidDateTime("")); // empty string
        assertFalse(Appointment.isValidDateTime(" ")); // spaces only

        // missing parts
        assertFalse(Appointment.isValidDateTime("25/12/2025")); // missing timing
        assertFalse(Appointment.isValidDateTime("20:00")); // missing date

        //wrong format
        assertFalse(Appointment.isValidDateTime("25-12-2025 20:00"));
        assertFalse(Appointment.isValidDateTime("25-12-2025 20/00"));

        //invalid parts
        assertFalse(Appointment.isValidDateTime("25/12/2025 20-00"));
        assertFalse(Appointment.isValidDateTime("25/12/2025 20:00-"));
        assertFalse(Appointment.isValidDateTime("25/12-2025 20:00"));

        //date not exist
        assertFalse(Appointment.isValidDateTime("32/3/2025 20:00"));
        assertFalse(Appointment.isValidDateTime("27/2/2025 20:61"));
        assertFalse(Appointment.isValidDateTime("32/2/2020 20:00"));

        //valid inputs
        assertTrue(Appointment.isValidDateTime("25/12/2025 20:00"));
        assertTrue(Appointment.isValidDateTime("1/10/2027 20:01"));
        assertTrue(Appointment.isValidDateTime("1/1/2027 20:59"));
        assertTrue(Appointment.isValidDateTime("1/10/1000 20:01"));
    }

    @Test
    public void isPassed() {
        //isPassed
        assertTrue(Appointment.isPassed(LocalDateTime.parse("25/12/2019 20:00", formatter)));
        assertTrue(Appointment.isPassed(LocalDateTime.parse("25/10/2020 20:00", formatter)));
        assertTrue(Appointment.isPassed(LocalDateTime.parse("25/10/1000 20:00", formatter)));
        assertTrue(Appointment.isPassed(LocalDateTime.MIN));

        //notPassed
        assertFalse(Appointment.isPassed(LocalDateTime.parse(validTimeString, formatter)));
        assertFalse(Appointment.isPassed(LocalDateTime.parse("25/12/2022 20:00", formatter)));
        assertFalse(Appointment.isPassed(LocalDateTime.parse("25/12/2023 20:00", formatter)));
        assertFalse(Appointment.isPassed(LocalDateTime.parse("25/12/2025 20:00", formatter)));
    }
}
