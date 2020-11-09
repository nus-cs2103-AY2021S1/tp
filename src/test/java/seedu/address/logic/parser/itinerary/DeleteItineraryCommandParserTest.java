package seedu.address.logic.parser.itinerary;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.itinerary.DeleteItineraryCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteItineraryCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteItineraryCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteItineraryCommandParserTest {

    private DeleteItineraryCommandParser parser = new DeleteItineraryCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteItineraryCommand() {
        assertParseSuccess(parser, "1", new DeleteItineraryCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteItineraryCommand.MESSAGE_USAGE));
    }
}
