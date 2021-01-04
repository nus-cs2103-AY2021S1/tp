package seedu.homerce.logic.parser.appointment;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.core.index.Index;
import seedu.homerce.logic.commands.appointment.UnDoneAppointmentCommand;
import seedu.homerce.logic.parser.exceptions.ParseException;

public class UnDoneAppointmentCommandParserTest {
    private UnDoneAppointmentCommandParser parser = new UnDoneAppointmentCommandParser();

    @Test
    public void parse_validArgs_returnsUnDoneCommand() throws ParseException {
        UnDoneAppointmentCommand command = parser.parse("1");
        UnDoneAppointmentCommand expectedCommand = new UnDoneAppointmentCommand(Index.fromOneBased(1));
        assertTrue(command.equals(expectedCommand));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("a"));
    }
}
