package seedu.address.logic.parser.tagparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_HOMEWORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_HOMEWORK;
import static seedu.address.logic.commands.tagcommands.TaskAddCommand.MESSAGE_TASK_NAME_NOT_PROVIDED;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tagcommands.TaskAddCommand;
import seedu.address.model.tag.TagTask;

public class TaskAddCommandParserTest {
    private TaskAddCommandParser parser = new TaskAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, "1 " + TASK_NAME_DESC_HOMEWORK,
                new TaskAddCommand(Index.fromOneBased(1), new EditTagDescriptorStub(VALID_TASK_NAME_HOMEWORK)));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskAddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingOneArg_throwsParseException() {
        // missing tag index
        assertParseFailure(parser, TASK_NAME_DESC_HOMEWORK,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskAddCommand.MESSAGE_USAGE));

        // missing task name
        assertParseFailure(parser, "1 ",
                String.format(MESSAGE_TASK_NAME_NOT_PROVIDED, TaskAddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_failure() {
        // invalid tagIndex
        assertParseFailure(parser, "-1 " + TASK_NAME_DESC_HOMEWORK,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskAddCommand.MESSAGE_USAGE));

    }

    private class EditTagDescriptorStub extends TaskAddCommand.EditTagDescriptor {
        EditTagDescriptorStub(String validTaskName) {
            setNewTagTask(new TagTask(validTaskName, false));
        }
    }
}
