package seedu.homerce.logic.parser.appointment;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.core.index.Index;
import seedu.homerce.logic.commands.appointment.DoneAppointmentCommand;
import seedu.homerce.logic.parser.exceptions.ParseException;

public class DoneAppointmentCommandParserTest {
    private DoneAppointmentCommandParser parser = new DoneAppointmentCommandParser();

    @Test
    public void parse_validArgs_returnsDoneCommand() throws ParseException {
        DoneAppointmentCommand command = parser.parse("1");
        DoneAppointmentCommand expectedCommand = new DoneAppointmentCommand(Index.fromOneBased(1));
        assertTrue(command.equals(expectedCommand));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("a"));
    }
}
