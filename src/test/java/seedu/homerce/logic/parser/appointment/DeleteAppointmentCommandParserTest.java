package seedu.homerce.logic.parser.appointment;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.core.index.Index;
import seedu.homerce.logic.commands.appointment.DeleteAppointmentCommand;
import seedu.homerce.logic.parser.exceptions.ParseException;

public class DeleteAppointmentCommandParserTest {
    private DeleteAppointmentCommandParser parser = new DeleteAppointmentCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() throws ParseException {
        DeleteAppointmentCommand command = parser.parse("1");
        DeleteAppointmentCommand expectedCommand = new DeleteAppointmentCommand(Index.fromOneBased(1));
        assertTrue(command.equals(expectedCommand));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("a"));
    }
}
