package seedu.address.logic.parser.bids;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BID;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.bidcommands.DeleteBidCommand;
import seedu.address.logic.parser.bidparser.DeleteBidCommandParser;

public class DeleteBidCommandParserTest {

    private DeleteBidCommandParser parser = new DeleteBidCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteBidCommand(INDEX_FIRST_BID));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteBidCommand.MESSAGE_USAGE));
    }
}
