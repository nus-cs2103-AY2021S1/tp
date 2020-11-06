package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROGRESS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NAME;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.TASK_A_DEADLINE;
import static seedu.address.testutil.TypicalTasks.TASK_A_NAME;
import static seedu.address.testutil.TypicalTasks.TASK_A_PROGRESS;
import static seedu.address.testutil.TypicalTasks.TASK_B_DEADLINE;
import static seedu.address.testutil.TypicalTasks.TASK_B_NAME;
import static seedu.address.testutil.TypicalTasks.TASK_B_PROGRESS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.project.AddTaskCommand;
import seedu.address.model.project.Deadline;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TypicalTasks;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(TypicalTasks.TASK_A).withTaskDescription(null).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TASK_A_NAME + TASK_A_DEADLINE
                + TASK_A_PROGRESS, new AddTaskCommand(
                expectedTask));

        // multiple task names - last name accepted
        assertParseSuccess(parser, TASK_B_NAME + TASK_A_NAME + TASK_A_DEADLINE
                + TASK_A_PROGRESS, new AddTaskCommand(
                expectedTask));


        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser, TASK_A_NAME + TASK_B_DEADLINE + TASK_A_DEADLINE
                + TASK_A_PROGRESS, new AddTaskCommand(
                expectedTask));

        // multiple progress - last repoUrl accepted
        assertParseSuccess(parser, TASK_A_NAME + TASK_A_DEADLINE
                + TASK_B_PROGRESS + TASK_A_PROGRESS, new AddTaskCommand(
                expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);
        // missing name prefix
        assertParseFailure(parser, SampleDataUtil.getValidTask().get(0) + TASK_A_DEADLINE
                        + TASK_A_PROGRESS,
                expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, TASK_A_NAME + SampleDataUtil.getValidTask().get(2)
                        + TASK_A_PROGRESS,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, TASK_A_NAME + TASK_A_DEADLINE
                        + SampleDataUtil.getValidTask().get(3),
                expectedMessage);


        // all prefixes missing
        assertParseFailure(parser, SampleDataUtil.getValidTask().get(0) + SampleDataUtil.getValidTask().get(2)
                        + SampleDataUtil.getValidTask().get(3),
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid task name
        assertParseFailure(parser, INVALID_TASK_NAME + TASK_A_DEADLINE
                + TASK_A_PROGRESS, Task.NAME_MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, TASK_A_NAME + INVALID_DEADLINE
                + TASK_A_PROGRESS, Deadline.MESSAGE_CONSTRAINTS);

        // invalid task progress
        assertParseFailure(parser, TASK_A_NAME + TASK_A_DEADLINE
                + INVALID_PROGRESS, Task.PROGRESS_MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TASK_NAME + INVALID_DEADLINE
                + TASK_A_PROGRESS, Task.NAME_MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TASK_A_NAME + TASK_A_DEADLINE
                        + TASK_A_PROGRESS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
    }
}
