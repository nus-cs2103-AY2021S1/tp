package seedu.address.logic.parser.attraction;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.attraction.FindAttractionCommand;
import seedu.address.model.attraction.AttractionContainsKeywordsPredicate;

public class FindAttractionCommandParserTest {

    private FindAttractionCommandParser parser = new FindAttractionCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, FindAttractionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindAttractionCommand expectedFindAttractionCommand =
                new FindAttractionCommand(new AttractionContainsKeywordsPredicate(Arrays.asList("Zoo", "MBS")));
        assertParseSuccess(parser, "Zoo MBS", expectedFindAttractionCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Zoo \n \t MBS  \t", expectedFindAttractionCommand);
    }

}
