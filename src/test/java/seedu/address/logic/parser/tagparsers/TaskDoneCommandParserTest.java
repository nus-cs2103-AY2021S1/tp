package seedu.address.logic.parser.tagparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tagcommands.TaskDoneCommand;

class TaskDoneCommandParserTest {
    private TaskDoneCommandParser parser = new TaskDoneCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, "1 a",
                new TaskDoneCommand(Index.fromOneBased(1), Index.fromOneBased(1)));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskDoneCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingOneArg_throwsParseException() {
        // missing tagIndex
        assertParseFailure(parser, " a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskDoneCommand.MESSAGE_USAGE));

        // missing taskIndex
        assertParseFailure(parser, "1 ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskDoneCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_failure() {
        // invalid tagIndex
        assertParseFailure(parser, "-1 a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskDoneCommand.MESSAGE_USAGE));

        // invalid taskIndex
        assertParseFailure(parser, "1 aa",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskDoneCommand.MESSAGE_USAGE));

    }

}
