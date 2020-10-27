package seedu.address.logic.parser.bids;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.bidcommands.FindBidCommand;
import seedu.address.logic.parser.bidparser.FindBidCommandParser;
import seedu.address.model.bid.BidContainsKeywordsPredicate;

public class FindBidCommandParserTest {

    private FindBidCommandParser parser = new FindBidCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindBidCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindBidCommand expectedFindBidCommand =
                new FindBidCommand(new BidContainsKeywordsPredicate(Arrays.asList("P1", "B1")));
        assertParseSuccess(parser, "P1 B1", expectedFindBidCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n P1 \n \t B1  \t", expectedFindBidCommand);
    }
}
