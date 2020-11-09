package seedu.address.logic.parser.tagparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TAG;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.tagcommands.TaskClearCommand;

public class TaskClearCommandParserTest {

    private TaskClearCommandParser parser = new TaskClearCommandParser();

    @Test
    public void parse_validArgs_returnsTaskClearCommand() {
        assertParseSuccess(parser, "1", new TaskClearCommand(INDEX_FIRST_TAG));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskClearCommand.MESSAGE_USAGE));
    }
}
