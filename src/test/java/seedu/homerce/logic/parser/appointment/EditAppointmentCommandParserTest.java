package seedu.homerce.logic.parser.appointment;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.core.index.Index;
import seedu.homerce.logic.commands.appointment.EditAppointmentCommand;
import seedu.homerce.logic.parser.exceptions.ParseException;
import seedu.homerce.model.appointment.TimeOfDay;
import seedu.homerce.model.client.Phone;
import seedu.homerce.model.service.ServiceCode;
import seedu.homerce.model.util.attributes.Date;

public class EditAppointmentCommandParserTest {
    private EditAppointmentCommandParser parser = new EditAppointmentCommandParser();

    @Test
    public void parse_missingParts_throwsParseException() {

        // no index specified
        assertThrows(ParseException.class, () -> parser.parse("dt/25-10-2020"));

        // no field specified
        assertThrows(ParseException.class, () -> parser.parse("1 1400"));

        //no index and no field
        assertThrows(ParseException.class, () -> parser.parse("25-10-2020"));
    }

    @Test
    public void parse_invalidPreamble_throwsParseException() {

        // negative index
        assertThrows(ParseException.class, () -> parser.parse("-5 dt/25-10-2020"));

        // zero index
        assertThrows(ParseException.class, () -> parser.parse("0 t/1300"));

        // invalid preamble
        assertThrows(ParseException.class, () -> parser.parse("2 hello dt/25-10-2020"));

        //invalid prefix
        assertThrows(ParseException.class, () -> parser.parse("1 r/25-10-2020"));
    }

    @Test
    public void parse_invalidValues_throwsParseException() {

        // invalid date
        assertThrows(ParseException.class, () -> parser.parse("1 dt/1234-123-123"));

        // invalid time
        assertThrows(ParseException.class, () -> parser.parse("1 t/2568"));

        // invalid phone
        assertThrows(ParseException.class, () -> parser.parse("1 p/8sf81s"));

        //invalid service code
        assertThrows(ParseException.class, () -> parser.parse("1 s/SC12345"));

    }

    @Test
    public void parse_allFieldsSpecified_success() throws ParseException {
        String userInput = "2 dt/10-1-2020 t/1400 p/83222666 s/SC001";
        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
            new EditAppointmentCommand.EditAppointmentDescriptor();
        descriptor.setDate(new Date("10-1-2020"));
        descriptor.setTimeOfDay(new TimeOfDay("1400"));
        descriptor.setPhone(new Phone("83222666"));
        descriptor.setServiceCode(new ServiceCode("SC001"));
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(Index.fromOneBased(2), descriptor);
        EditAppointmentCommand command = parser.parse(userInput);
        assertTrue(expectedCommand.equals(command));
    }

    @Test
    public void parse_someFieldsSpecified_success() throws ParseException {
        String userInput = "2 dt/25-10-2020 p/91234567";
        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
            new EditAppointmentCommand.EditAppointmentDescriptor();
        descriptor.setDate(new Date("25-10-2020"));
        descriptor.setPhone(new Phone("91234567"));
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(Index.fromOneBased(2), descriptor);
        EditAppointmentCommand command = parser.parse(userInput);
        assertTrue(expectedCommand.equals(command));
    }

    @Test
    public void parse_oneFieldSpecified_success() throws ParseException {
        String userInput = "2 t/1530";
        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
            new EditAppointmentCommand.EditAppointmentDescriptor();
        descriptor.setTimeOfDay(new TimeOfDay("1530"));
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(Index.fromOneBased(2), descriptor);
        EditAppointmentCommand command = parser.parse(userInput);
        assertTrue(expectedCommand.equals(command));
    }

    @Test
    public void parse_multipleFieldsRepeated_takesLast() throws ParseException {
        String userInput = "2 t/1400 t/1530 dt/10-1-2020";
        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
            new EditAppointmentCommand.EditAppointmentDescriptor();
        descriptor.setDate(new Date("10-1-2020"));
        descriptor.setTimeOfDay(new TimeOfDay("1530"));
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(Index.fromOneBased(2), descriptor);
        EditAppointmentCommand command = parser.parse(userInput);
        assertTrue(expectedCommand.equals(command));
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() throws ParseException {
        String userInput = "2 dt/10-1-2020 t/2698 t/1400";
        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
            new EditAppointmentCommand.EditAppointmentDescriptor();
        descriptor.setDate(new Date("10-1-2020"));
        descriptor.setTimeOfDay(new TimeOfDay("1400"));
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(Index.fromOneBased(2), descriptor);
        EditAppointmentCommand command = parser.parse(userInput);
        assertTrue(expectedCommand.equals(command));
    }
}
