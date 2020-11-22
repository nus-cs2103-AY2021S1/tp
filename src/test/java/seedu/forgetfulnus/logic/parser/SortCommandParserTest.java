package seedu.forgetfulnus.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.logic.commands.SortCommand;
import seedu.forgetfulnus.logic.parser.exceptions.ParseException;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_invalidArgs_throwsException() {
        String args = "invalid argument";
        assertThrows(ParseException.class, () -> parser.parse(args));
    }
    @Test
    public void parse_germanString_success() throws ParseException {
        String args = "german";
        SortCommand command = new SortCommand(args);
        assertEquals(command, parser.parse(args));
    }
    @Test
    public void parse_null_throwsAssertionException() {
        assertThrows(AssertionError.class, () -> parser.parse(null));
    }
}
