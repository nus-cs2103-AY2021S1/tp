package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.meeting.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.meeting.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meeting.FindCommand;
import seedu.address.model.meeting.meeting.TitleDescriptionContainsKeywordsPredicate;

class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new TitleDescriptionContainsKeywordsPredicate(Arrays.asList("discuss", "final")));
        assertParseSuccess(parser, "discuss final", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n discuss \n \t final  \t", expectedFindCommand);
    }

}
