package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindByTagCommand;
import seedu.address.model.item.TagMatchesKeywordsPredicate;

public class FindByTagCommandParserTest {
    private FindByTagCommandParser parser = new FindByTagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindByTagCommand expectedFindCommand =
                new FindByTagCommand(new TagMatchesKeywordsPredicate(Arrays.asList("Apple", "Banana")));
        assertParseSuccess(parser, "Apple, Banana", expectedFindCommand);

        // space between same input
        assertParseSuccess(parser, "Apple , Banana", expectedFindCommand);
    }
}
