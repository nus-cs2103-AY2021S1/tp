package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROJECT_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROJECT_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REPOURL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PROJECT_DESCRIPTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PROJECT_DESCRIPTION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PROJECT_NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PROJECT_NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PROJECT_TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.PROJECT_TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.REPOURL_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.REPOURL_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESC_DG;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESC_MODEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DESCRIPTION_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_DG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPOURL_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_MODEL;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalProjects.AI;
import static seedu.address.testutil.TypicalProjects.BOT;
import static seedu.address.testutil.TypicalTasks.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.global.AddCommand;
import seedu.address.logic.commands.project.AddTaskCommand;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.RepoUrl;
import seedu.address.model.tag.ProjectTag;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TypicalTasks;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(TypicalTasks.TASK_A).withTaskDescription(null).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE+TASK_A_NAME + TASK_A_DEADLINE
                + TASK_A_PROGRESS, new AddTaskCommand(
                expectedTask));

        // multiple task names - last name accepted
        assertParseSuccess(parser, TASK_B_NAME+TASK_A_NAME + TASK_A_DEADLINE
                + TASK_A_PROGRESS, new AddTaskCommand(
                expectedTask));


        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser, TASK_A_NAME + TASK_B_DEADLINE+TASK_A_DEADLINE
                + TASK_A_PROGRESS, new AddTaskCommand(
                expectedTask));

        // multiple progress - last repoUrl accepted
        assertParseSuccess(parser, TASK_A_NAME + TASK_A_DEADLINE
                + TASK_B_PROGRESS+TASK_A_PROGRESS, new AddTaskCommand(
                expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);
        // missing name prefix
        assertParseFailure(parser, SampleDataUtil.getValidTask().get(0) + TASK_A_DEADLINE
                        +TASK_A_PROGRESS,
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

         String INVALID_TASK_NAME = " "+PREFIX_TASK_NAME+"";
         String INVALID_DEADLINE = " "+PREFIX_TASK_DEADLINE+"29022020000000";
         String INVALID_PROGRESS = " "+PREFIX_TASK_PROGRESS+"101";

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
        assertParseFailure(parser, PREAMBLE_NON_EMPTY+TASK_A_NAME + TASK_A_DEADLINE
                        + TASK_A_PROGRESS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
    }
}
