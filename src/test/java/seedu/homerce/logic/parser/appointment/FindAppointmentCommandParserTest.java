package seedu.homerce.logic.parser.appointment;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.homerce.logic.commands.appointment.FindAppointmentCommand;
import seedu.homerce.logic.parser.exceptions.ParseException;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.appointment.predicate.AppointmentDatePredicate;
import seedu.homerce.model.appointment.predicate.AppointmentNamePredicate;
import seedu.homerce.model.appointment.predicate.AppointmentPhonePredicate;
import seedu.homerce.model.appointment.predicate.AppointmentServiceCodePredicate;
import seedu.homerce.model.client.Phone;
import seedu.homerce.model.service.ServiceCode;
import seedu.homerce.model.util.attributes.Date;

public class FindAppointmentCommandParserTest {
    private FindAppointmentCommandParser parser = new FindAppointmentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
        assertThrows(ParseException.class, () -> parser.parse("  "));
    }

    @Test
    public void parse_multipleArg_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" n/test name dt/10-10-2020"));
        assertThrows(ParseException.class, () -> parser.parse(" dt/10-10-2020 p/94226655"));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" w/wrong prefix"));
    }

    @Test
    public void parse_noPrefix_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" noPrefix"));
    }

    @Test
    public void parse_noValue_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" t/"));
    }

    @Test
    public void parse_invalidPreamble_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" hello dt/25-10-2020"));
    }

    @Test
    public void parse_invalidValue_throwsParseException() {
        //invalid date
        assertThrows(ParseException.class, () -> parser.parse(" dt/32-20-2020"));

        //invalid phone
        assertThrows(ParseException.class, () -> parser.parse(" p/9asd999a9"));

        // invalid service code
        assertThrows(ParseException.class, () -> parser.parse(" s/S1324"));
    }

    @Test
    public void parse_validPhone_success() throws ParseException {
        FindAppointmentCommand command = parser.parse(" p/91234567");
        Predicate<Appointment> phonePredicate =
            new AppointmentPhonePredicate(new Phone("91234567"));
        assertTrue(command.getPredicate().equals(phonePredicate));
    }

    @Test
    public void parse_validName_success() throws ParseException {
        FindAppointmentCommand command = parser.parse(" n/test name");
        Predicate<Appointment> namePredicate =
            new AppointmentNamePredicate(Arrays.asList("test", "name"));
        assertTrue(command.getPredicate().equals(namePredicate));
    }

    @Test
    public void parse_validDate_success() throws ParseException {
        FindAppointmentCommand command = parser.parse(" dt/10-10-2020");
        Predicate<Appointment> datePredicate =
            new AppointmentDatePredicate(new Date("10-10-2020"));
        assertTrue(command.getPredicate().equals(datePredicate));
    }

    @Test
    public void parse_validServiceCode_success() throws ParseException {
        FindAppointmentCommand command = parser.parse(" s/SC001");
        Predicate<Appointment> serviceCodePredicate =
            new AppointmentServiceCodePredicate(new ServiceCode("SC001"));
        assertTrue(command.getPredicate().equals(serviceCodePredicate));
    }
}
