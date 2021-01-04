package seedu.homerce.logic.parser.appointment;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.homerce.logic.commands.appointment.AddAppointmentCommand;
import seedu.homerce.logic.parser.exceptions.ParseException;
import seedu.homerce.model.appointment.AppointmentTemp;
import seedu.homerce.testutil.appointment.AppointmentTempBuilder;

public class AddAppointmentCommandParserTest {
    private AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        AppointmentTemp expectedAppointment = new AppointmentTempBuilder()
            .withDate("25-10-2020")
            .withTimeOfDay("1400")
            .withPhone("83222666")
            .withServiceCode("SC001")
            .build();
        AddAppointmentCommand expectedCommand = new AddAppointmentCommand(expectedAppointment);

        // whitespace only preamble
        AddAppointmentCommand commandOne = parser.parse(" dt/25-10-2020 t/1400 p/83222666 s/SC001");
        assertTrue(expectedCommand.equals(commandOne));

        //multiple dates - last date accepted
        AddAppointmentCommand commandTwo = parser.parse(
            " dt/20-10-2020 dt/25-10-2020 t/1400 p/83222666 s/SC001");
        assertTrue(expectedCommand.equals(commandTwo));

        //multiple times - last time accepted
        AddAppointmentCommand commandThree = parser.parse(
            " dt/25-10-2020 t/1200 t/1400 p/83222666 s/SC001");
        assertTrue(expectedCommand.equals(commandThree));

        //multiple phone numbers - last phone number accepted
        AddAppointmentCommand commandFour = parser.parse(
            " dt/25-10-2020 t/1400 p/999 p/83222666 s/SC001");
        assertTrue(expectedCommand.equals(commandFour));

        //multiple services - last service accepted
        AddAppointmentCommand commandFive = parser.parse(
            " dt/25-10-2020 t/1400 p/83222666 s/SC123 s/SC001");
        assertTrue(expectedCommand.equals(commandFive));

    }

    @Test
    public void parse_fieldsMissing_throwsParseException() {

        // date missing
        assertThrows(ParseException.class, () -> parser.parse(" t/1400 p/81234567 s/SC001"));

        // time missing
        assertThrows(ParseException.class, () -> parser.parse(
            " dt/25-10-2020 p/81234567 s/SC001"));

        // phone missing
        assertThrows(ParseException.class, () -> parser.parse(" dt/25-10-2020 t/1400 s/SC001"));

        // service code missing
        assertThrows(ParseException.class, () -> parser.parse(" dt/25-10-2020 t/1400 p/81234567"));
    }

    @Test
    public void parse_invalidValue_throwsParseException() {
        // empty description
        assertThrows(ParseException.class, () -> parser.parse(" dt/ t/ p/81234567 s/sc001"));

        // invalid date
        assertThrows(ParseException.class, () -> parser.parse(
            " dt/32-13-2020 t/1400 p/81234567 s/SC001"));

        // invalid time
        assertThrows(ParseException.class, () -> parser.parse(
            " dt/25-10-2020 t/2569 p/81234567 s/SC001"));

        //invalid phone number
        assertThrows(ParseException.class, () -> parser.parse(
            " dt/25-10-2020 t/1400 p/81234abc7 s/SC001"));

        //invalid service code
        assertThrows(ParseException.class, () -> parser.parse(
            " dt/25-10-2020 t/1400 p/81234567 s/SC1234"));
    }
}
