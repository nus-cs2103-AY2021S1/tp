package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteBookingCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteBookingCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteBookingCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteBookingCommandParserTest {

    private DeleteBookingCommandParser parser = new DeleteBookingCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, " bid/1", new DeleteBookingCommand(1));
    }

    @Test
    public void parse_validArgsWithWhitespaces_returnsDeleteCommand() {
        assertParseSuccess(parser, " \t bid/ 1 \t", new DeleteBookingCommand(1));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteBookingCommand.MESSAGE_USAGE));
    }
}
