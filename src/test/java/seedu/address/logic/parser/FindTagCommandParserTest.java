package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindByTagCommand;
import seedu.address.model.person.TagContainsKeywordsPredicate;

public class FindTagCommandParserTest {

    private TagFindCommandParser parser = new TagFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindByTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsTagFindCommand() {
        // no leading and trailing whitespaces
        FindByTagCommand expectedTagFindCommand =
                new FindByTagCommand(new TagContainsKeywordsPredicate(Arrays.asList("friends")));
        assertParseSuccess(parser, "friends", expectedTagFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n friends \n \t  \t", expectedTagFindCommand);
    }

}
