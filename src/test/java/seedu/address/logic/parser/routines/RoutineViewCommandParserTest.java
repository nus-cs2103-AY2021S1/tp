package seedu.address.logic.parser.routines;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.routines.RoutineViewCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the RoutineViewCommand code. The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class RoutineViewCommandParserTest {
    private final RoutineViewCommandParser parser = new RoutineViewCommandParser();

    @Test
    public void parse_validArgs_returnsRoutineViewCommand() {
        assertParseSuccess(parser, "1", new RoutineViewCommand(Index.fromOneBased(1)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RoutineViewCommand.MESSAGE_USAGE);

        assertParseFailure(
                parser, "a", expectedMessage);
    }
    @Test
    public void parse_missingArgs_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RoutineViewCommand.MESSAGE_USAGE);
        assertParseFailure(
                parser, "", expectedMessage);
    }

    @Test
    public void parse_numberFormat() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RoutineViewCommand.MESSAGE_USAGE);
        assertParseFailure(
                parser, "2147483648", expectedMessage);
    }

}
