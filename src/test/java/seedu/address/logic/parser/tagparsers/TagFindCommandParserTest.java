package seedu.address.logic.parser.tagparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.tagcommands.TagFindCommand;
import seedu.address.model.tag.TagNameContainsKeywordsPredicate;

public class TagFindCommandParserTest {

    private TagFindCommandParser parser = new TagFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(
                parser,
                "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsTagFindCommand() {
        // no leading and trailing whitespaces
        TagFindCommand expectedTagFindCommand =
                new TagFindCommand(new TagNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedTagFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedTagFindCommand);
    }

}
