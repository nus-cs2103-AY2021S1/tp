package seedu.flashcard.logic.parser;

import static seedu.flashcard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashcard.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.flashcard.logic.commands.FilterCommand;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    //    @Test
    //    public void parse_validArgs_returnsFilterCommand() {
    //        // no leading and trailing whitespaces
    //        FilterCommand expectedFilterCommand =
    //                new FilterCommand(new CategoryEqualsKeywordsPredicate(null),
    //                        new RatingEqualsKeywordsPredicate(new Rating("1")),
    //                        new FavouriteEqualsKeywordsPredicate(null),
    //                        new TagsEqualKeywordsPredicate(null));
    //        assertParseSuccess(parser, " r/ 1", expectedFilterCommand);
    //        // multiple whitespaces between keywords
    //        assertParseSuccess(parser, " \n \t c/Revision History  \t", expectedFilterCommand);
    //    }
}
