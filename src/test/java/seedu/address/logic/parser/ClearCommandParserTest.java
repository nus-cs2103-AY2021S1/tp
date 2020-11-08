package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ClearCommandParserTest {
    private final SupperStrikersParser parser = new SupperStrikersParser();

    @Test
    public void parse_extraFields_throwsParseException() {
        String input = ClearCommand.COMMAND_WORD + " 1 2 3";
        assertThrows(ParseException.class, () -> parser.parseCommand(input));
    }

    @Test
    public void parse_noFields_present() throws ParseException {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
    }
}
