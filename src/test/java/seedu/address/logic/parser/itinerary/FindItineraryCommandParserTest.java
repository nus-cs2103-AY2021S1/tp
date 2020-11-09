package seedu.address.logic.parser.itinerary;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.itinerary.FindItineraryCommand;
import seedu.address.model.itinerary.ItineraryContainsKeywordsPredicate;

class FindItineraryCommandParserTest {

    private FindItineraryCommandParser parser = new FindItineraryCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, FindItineraryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindItineraryCommand expectedFindItineraryCommand =
                new FindItineraryCommand(new ItineraryContainsKeywordsPredicate(Arrays.asList("Zoo", "Paris")));
        assertParseSuccess(parser, "Zoo Paris", expectedFindItineraryCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Zoo \n \t Paris  \t", expectedFindItineraryCommand);
    }

}
