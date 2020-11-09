package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.EXTRA_ARGUMENT_MESSAGE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.find.FindEventCommand;
import seedu.address.model.task.TaskMatchesFindKeywordPredicate;

public class FindEventCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // no prefix
        assertParseFailure(parser, "event     ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE));

        // wrong prefix
        assertParseFailure(parser, "event i/2", String.format(EXTRA_ARGUMENT_MESSAGE, "i/"));

        // no tag keyword given
        assertParseFailure(parser, "event " + PREFIX_TAG,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE));

        // more than one tag keyword given
        assertParseFailure(parser, "event " + PREFIX_TAG + "tag1 tag2",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE));

        // no desc keyword given
        assertParseFailure(parser, "event " + PREFIX_DESCRIPTION + "  " + PREFIX_TAG + "tag",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE));

        // no desc keyword given
        assertParseFailure(parser, "event " + PREFIX_DESCRIPTION + "  ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE));

        // no tag keyword given
        assertParseFailure(parser, "event " + PREFIX_DESCRIPTION + "desc " + PREFIX_TAG + "tag1 tag2",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        FindEventCommand expectedFindEventCommandWithDesc =
            new FindEventCommand(new TaskMatchesFindKeywordPredicate(Arrays.asList("workshop", "meeting")));
        FindEventCommand expectedFindEventCommandWithTag =
            new FindEventCommand(new TaskMatchesFindKeywordPredicate("urgent"));
        FindEventCommand expectedFindEventCommand =
            new FindEventCommand(new TaskMatchesFindKeywordPredicate(Arrays.asList("workshop", "meeting"), "urgent"));

        // no leading and trailing whitespaces desc keyword
        assertParseSuccess(parser, "event "
            + PREFIX_DESCRIPTION + "workshop meeting", expectedFindEventCommandWithDesc);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "event "
            + PREFIX_DESCRIPTION + "\n workshop \n \t meeting  \t", expectedFindEventCommandWithDesc);

        // tag keyword given
        assertParseSuccess(parser, "event " + PREFIX_TAG + "urgent", expectedFindEventCommandWithTag);

        // both desc and tag keyword given
        assertParseSuccess(parser, "event "
            + PREFIX_DESCRIPTION + "workshop meeting " + PREFIX_TAG + "urgent", expectedFindEventCommand);
    }
}
