package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.EXTRA_ARGUMENT_MESSAGE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.find.FindTodoCommand;
import seedu.address.model.task.TaskMatchesFindKeywordPredicate;

public class FindTodoCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // no prefix
        assertParseFailure(parser, "todo     ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTodoCommand.MESSAGE_USAGE));

        // wrong prefix
        assertParseFailure(parser, "todo i/2", String.format(EXTRA_ARGUMENT_MESSAGE, "i/"));

        // no tag keyword given
        assertParseFailure(parser, "todo " + PREFIX_TAG,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTodoCommand.MESSAGE_USAGE));

        // more than one tag keyword given
        assertParseFailure(parser, "todo " + PREFIX_TAG + "tag1 tag2",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTodoCommand.MESSAGE_USAGE));

        // no desc keyword given
        assertParseFailure(parser, "todo " + PREFIX_DESCRIPTION + "  " + PREFIX_TAG + "tag",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTodoCommand.MESSAGE_USAGE));

        // no desc keyword given
        assertParseFailure(parser, "todo " + PREFIX_DESCRIPTION + "  ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTodoCommand.MESSAGE_USAGE));

        // no tag keyword given
        assertParseFailure(parser, "todo " + PREFIX_DESCRIPTION + "desc " + PREFIX_TAG + "tag1 tag2",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTodoCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        FindTodoCommand expectedFindTodoCommandWithDesc =
            new FindTodoCommand(new TaskMatchesFindKeywordPredicate(Arrays.asList("homework", "quiz")));
        FindTodoCommand expectedFindTodoCommandWithTag =
            new FindTodoCommand(new TaskMatchesFindKeywordPredicate("cs2100"));
        FindTodoCommand expectedFindTodoCommand =
            new FindTodoCommand(new TaskMatchesFindKeywordPredicate(Arrays.asList("homework", "quiz"), "cs2100"));

        // no leading and trailing whitespaces desc keyword
        assertParseSuccess(parser, "todo " + PREFIX_DESCRIPTION + "homework quiz", expectedFindTodoCommandWithDesc);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "todo "
            + PREFIX_DESCRIPTION + "\n homework \n \t quiz  \t", expectedFindTodoCommandWithDesc);

        // tag keyword given
        assertParseSuccess(parser, "todo " + PREFIX_TAG + "cs2100", expectedFindTodoCommandWithTag);

        // both desc and tag keyword given
        assertParseSuccess(parser, "todo "
            + PREFIX_DESCRIPTION + "homework quiz " + PREFIX_TAG + "cs2100", expectedFindTodoCommand);
    }
}
