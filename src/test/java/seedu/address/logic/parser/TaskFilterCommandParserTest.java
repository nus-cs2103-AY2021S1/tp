package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.TaskCommandTestUtil.INVALID_TASK_ASSIGNEE_DESC;
import static seedu.address.logic.commands.TaskCommandTestUtil.INVALID_TASK_DEADLINE_DESC_A;
import static seedu.address.logic.commands.TaskCommandTestUtil.INVALID_TASK_DEADLINE_DESC_B;
import static seedu.address.logic.commands.TaskCommandTestUtil.INVALID_TASK_DEADLINE_DESC_C;
import static seedu.address.logic.commands.TaskCommandTestUtil.INVALID_TASK_IS_DONE_DESC;
import static seedu.address.logic.commands.TaskCommandTestUtil.INVALID_TASK_PROGRESS_DESC_A;
import static seedu.address.logic.commands.TaskCommandTestUtil.INVALID_TASK_PROGRESS_DESC_B;
import static seedu.address.logic.commands.TaskCommandTestUtil.INVALID_TASK_PROGRESS_DESC_C;
import static seedu.address.logic.commands.TaskCommandTestUtil.INVALID_TIME_RANGE_DESC_A;
import static seedu.address.logic.commands.TaskCommandTestUtil.INVALID_TIME_RANGE_DESC_B;
import static seedu.address.logic.commands.TaskCommandTestUtil.TASK_ASSIGNEE_DESC;
import static seedu.address.logic.commands.TaskCommandTestUtil.TASK_DEADLINE_DESC;
import static seedu.address.logic.commands.TaskCommandTestUtil.TASK_IS_DONE_DESC;
import static seedu.address.logic.commands.TaskCommandTestUtil.TASK_NAME_DESC;
import static seedu.address.logic.commands.TaskCommandTestUtil.TASK_PROGRESS_DESC;
import static seedu.address.logic.commands.TaskCommandTestUtil.TASK_TIME_RANGE_DESC;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_END_DATE;
import static seedu.address.logic.commands.TaskCommandTestUtil.VALID_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.project.TaskFilterCommand;
import seedu.address.model.person.GitUserName;
import seedu.address.model.project.Deadline;
import seedu.address.model.task.Date;
import seedu.address.model.task.Task;

class TaskFilterCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskFilterCommand.MESSAGE_USAGE);

    private final TaskFilterCommandParser parser = new TaskFilterCommandParser();
    @Test
    public void parse_emptyInput_failure() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPrefix_failure() {
        // wrong prefix used
        assertParseFailure(parser, " " + PREFIX_TASK, MESSAGE_INVALID_FORMAT);
        // missing one of the time range prefixes
        assertParseFailure(parser, " " + PREFIX_START_DATE + VALID_START_DATE, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " " + PREFIX_END_DATE + VALID_END_DATE, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " " + PREFIX_END_DATE + VALID_END_DATE + TASK_IS_DONE_DESC, MESSAGE_INVALID_FORMAT);
        // too many prefixes provided
        assertParseFailure(parser, TASK_TIME_RANGE_DESC + TASK_NAME_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, TASK_DEADLINE_DESC + TASK_PROGRESS_DESC, MESSAGE_INVALID_FORMAT);
        // no space between start date prefix and end date prefix
        assertParseFailure(parser, " " + PREFIX_START_DATE + VALID_START_DATE
            + PREFIX_END_DATE + VALID_END_DATE, MESSAGE_INVALID_FORMAT);
    }
    @Test
    // TaskFilterCommand does not have equal() method
    // as one cannot compare two predicates unless they are the same object
    // Therefore, one can only check that whether the result of parsing is an instance of TaskFilterCommand (not null)
    public void parse_validPrefixAndValue_success() throws Exception {
        // leading white space
        assertNotNull(parser.parse(PREAMBLE_WHITESPACE + TASK_NAME_DESC));

        // other valid inputs
        assertNotNull(parser.parse(TASK_ASSIGNEE_DESC));
        assertNotNull(parser.parse(TASK_DEADLINE_DESC));
        assertNotNull(parser.parse(TASK_PROGRESS_DESC));
        assertNotNull(parser.parse(TASK_IS_DONE_DESC));
    }
    @Test
    public void parse_validPrefixInvalidValue_failure() {
        assertParseFailure(parser, INVALID_TASK_DEADLINE_DESC_A, Deadline.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_TASK_DEADLINE_DESC_B, Deadline.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_TASK_DEADLINE_DESC_C, Deadline.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_TASK_PROGRESS_DESC_A, Task.PROGRESS_MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_TASK_PROGRESS_DESC_B, Task.PROGRESS_MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_TASK_PROGRESS_DESC_C, Task.PROGRESS_MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_TASK_IS_DONE_DESC, Task.IS_DONE_MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_TASK_ASSIGNEE_DESC, GitUserName.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_TIME_RANGE_DESC_A, Date.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_TIME_RANGE_DESC_B, TaskFilterCommand.MESSAGE_INVALID_TIME_RANGE);
    }
}
