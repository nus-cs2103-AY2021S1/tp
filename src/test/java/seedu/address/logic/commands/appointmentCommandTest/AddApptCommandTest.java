package seedu.address.logic.commands.appointmentCommandTest;

import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.appointmentcommand.AddApptCommand;
import seedu.address.model.patient.Appointment;
import seedu.address.model.patient.Nric;

public class AddApptCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddApptCommand(null, null));
        assertThrows(NullPointerException.class, () -> new AddApptCommand(null, new Appointment()));
        assertThrows(NullPointerException.class, () -> new AddApptCommand(new Nric("S0000001A"), null));
    }

    @Test
    public void constructor_invalidNric_throwsIllegalArgumentException() {
        // NRIC missing 1 digit
        assertThrows(IllegalArgumentException.class, () -> new AddApptCommand(
                new Nric("S123456A"), new Appointment()));

        // NRIC missing 5 digits
        assertThrows(IllegalArgumentException.class, () -> new AddApptCommand(
                new Nric("S12A"), new Appointment()));

        // NRIC no digits
        assertThrows(IllegalArgumentException.class, () -> new AddApptCommand(
                new Nric("SA"), new Appointment()));

        // NRIC extra digit
        assertThrows(IllegalArgumentException.class, () -> new AddApptCommand(
                new Nric("S12345678A"), new Appointment()));

        // NRIC extra 5 digits
        assertThrows(IllegalArgumentException.class, () -> new AddApptCommand(
                new Nric("S123456789012A"), new Appointment()));
    }

    @Test
    public void constructor_invalidAppointmentTime_throwsDateTimeParseException() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");

        // invalid format
        assertThrows(DateTimeParseException.class, () -> new AddApptCommand(
                new Nric("S1234567A"), new Appointment("", LocalDateTime.parse("1-12-2020", formatter))));

        // missing time
        assertThrows(DateTimeParseException.class, () -> new AddApptCommand(
                new Nric("S1234567A"), new Appointment("", LocalDateTime.parse("10/12/2020", formatter))));

        // missing date
        assertThrows(DateTimeParseException.class, () -> new AddApptCommand(
                new Nric("S1234567A"), new Appointment("", LocalDateTime.parse("18:00", formatter))));

        // missing date and time
        assertThrows(DateTimeParseException.class, () -> new AddApptCommand(
                new Nric("S1234567A"), new Appointment("", LocalDateTime.parse("", formatter))));

        // invalid day
        assertThrows(DateTimeParseException.class, () -> new AddApptCommand(
                new Nric("S1234567A"), new Appointment("", LocalDateTime.parse("32/12/2020", formatter))));

        // invalid day
        assertThrows(DateTimeParseException.class, () -> new AddApptCommand(
                new Nric("S1234567A"), new Appointment("", LocalDateTime.parse("50/20/2020", formatter))));

        // invalid day
        assertThrows(DateTimeParseException.class, () -> new AddApptCommand(
                new Nric("S1234567A"), new Appointment("", LocalDateTime.parse("-1/20/2020", formatter))));

        // invalid month
        assertThrows(DateTimeParseException.class, () -> new AddApptCommand(
                new Nric("S1234567A"), new Appointment("", LocalDateTime.parse("1/JAN/2020", formatter))));

        // invalid month
        assertThrows(DateTimeParseException.class, () -> new AddApptCommand(
                new Nric("S1234567A"), new Appointment("", LocalDateTime.parse("10/13/2020", formatter))));

        // invalid month
        assertThrows(DateTimeParseException.class, () -> new AddApptCommand(
                new Nric("S1234567A"), new Appointment("", LocalDateTime.parse("10/20/2020", formatter))));

        // invalid time
        assertThrows(DateTimeParseException.class, () -> new AddApptCommand(
                new Nric("S1234567A"), new Appointment("", LocalDateTime.parse("10/12/2020 23:60", formatter))));

        // invalid time
        assertThrows(DateTimeParseException.class, () -> new AddApptCommand(
                new Nric("S1234567A"), new Appointment("", LocalDateTime.parse("10/12/2020 24:01", formatter))));

        // invalid time
        assertThrows(DateTimeParseException.class, () -> new AddApptCommand(
                new Nric("S1234567A"), new Appointment("", LocalDateTime.parse("10/12/2020 24:59", formatter))));

        // invalid time
        assertThrows(DateTimeParseException.class, () -> new AddApptCommand(
                new Nric("S1234567A"), new Appointment("", LocalDateTime.parse("10/12/2020 2359", formatter))));

        // invalid time
        assertThrows(DateTimeParseException.class, () -> new AddApptCommand(
                new Nric("S1234567A"), new Appointment("", LocalDateTime.parse("10/12/2020 233:59", formatter))));
    }
}
