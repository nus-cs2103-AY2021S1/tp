package seedu.address.logic.parser.itineraryattraction;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_VISITING;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.itineraryattraction.DeleteItineraryAttractionCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteItineraryAttractionCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteItineraryAttractionCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteItineraryAttractionCommandParserTest {

    private DeleteItineraryAttractionCommandParser parser = new DeleteItineraryAttractionCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser,
                INDEX_FIRST.getOneBased() + " " + PREFIX_DAY_VISITING + INDEX_SECOND.getOneBased(),
                new DeleteItineraryAttractionCommand(INDEX_FIRST, INDEX_SECOND));
        assertParseSuccess(parser,
                INDEX_THIRD.getOneBased() + " " + PREFIX_DAY_VISITING + INDEX_FIRST.getOneBased(),
                new DeleteItineraryAttractionCommand(INDEX_THIRD, INDEX_FIRST));
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + INDEX_THIRD.getOneBased() + " " + PREFIX_DAY_VISITING + INDEX_FIRST.getOneBased(),
                new DeleteItineraryAttractionCommand(INDEX_THIRD, INDEX_FIRST));

    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser,
                "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteItineraryAttractionCommand.MESSAGE_USAGE));

        assertParseFailure(parser,
                "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteItineraryAttractionCommand.MESSAGE_USAGE));

        assertParseFailure(parser,
                "1day/1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteItineraryAttractionCommand.MESSAGE_USAGE));

        assertParseFailure(parser,
                "day/23",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteItineraryAttractionCommand.MESSAGE_USAGE));

        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY + INDEX_THIRD.getOneBased() + " " + PREFIX_DAY_VISITING + INDEX_FIRST.getOneBased(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteItineraryAttractionCommand.MESSAGE_USAGE));

    }
}
