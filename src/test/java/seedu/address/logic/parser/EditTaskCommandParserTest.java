package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROGRESS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROJECT_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROJECT_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REPOURL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NAME;
import static seedu.address.logic.commands.CommandTestUtil.PROJECT_DESCRIPTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PROJECT_DESCRIPTION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PROJECT_NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PROJECT_TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.PROJECT_TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.REPOURL_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.REPOURL_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESC_DG;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESC_MODEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DESCRIPTION_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DESCRIPTION_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_DG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPOURL_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPOURL_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_MODEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PROJECT;
import static seedu.address.testutil.TypicalTasks.TASK_A_DEADLINE;
import static seedu.address.testutil.TypicalTasks.TASK_A_DESCRIPTION;
import static seedu.address.testutil.TypicalTasks.TASK_A_NAME;
import static seedu.address.testutil.TypicalTasks.TASK_A_PROGRESS;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.global.EditCommand;
import seedu.address.logic.commands.global.EditCommand.EditProjectDescriptor;
import seedu.address.logic.commands.project.EditTaskCommand;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.RepoUrl;
import seedu.address.model.tag.ProjectTag;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.EditProjectDescriptorBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, SampleDataUtil.getTask1().get(0), MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TASK_A_NAME, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TASK_A_NAME, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TASK_NAME, Task.NAME_MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_DEADLINE, Deadline.MESSAGE_CONSTRAINTS); // invalid deadline
        assertParseFailure(parser, "1" + INVALID_PROGRESS, Task.PROGRESS_MESSAGE_CONSTRAINTS); // invalid progress

        // invalid name followed by valid deadline
        assertParseFailure(parser, "1" + INVALID_TASK_NAME + TASK_A_DEADLINE, Task.NAME_MESSAGE_CONSTRAINTS);

        // valid progress followed by invalid progress. The test case for invalid progress followed by valid progress
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + TASK_A_PROGRESS + INVALID_PROGRESS, Task.PROGRESS_MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TASK_NAME + TASK_A_DEADLINE + INVALID_PROGRESS,
                Task.NAME_MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PROJECT;
        String userInput = targetIndex.getOneBased() + TASK_A_NAME + TASK_A_PROGRESS + TASK_A_DESCRIPTION
                + TASK_A_DEADLINE;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withTaskName(SampleDataUtil.getValidTask().get(0))
                .withDeadline(SampleDataUtil.getValidTask().get(2))
                .withProgress(SampleDataUtil.getValidTask().get(3))
                .withTaskDescription(SampleDataUtil.getValidTask().get(1)).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PROJECT;
        String userInput = targetIndex.getOneBased()  + TASK_A_DESCRIPTION+ TASK_A_NAME;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withTaskName(SampleDataUtil.getValidTask().get(0))
                .withTaskDescription(SampleDataUtil.getValidTask().get(1)).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PROJECT;
        String userInput = targetIndex.getOneBased() + TASK_A_NAME;
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withTaskName(SampleDataUtil.getValidTask().get(0)).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline
        userInput = targetIndex.getOneBased() + TASK_A_DEADLINE;
        descriptor = new EditTaskDescriptorBuilder()
                .withDeadline(SampleDataUtil.getValidTask().get(2)).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + TASK_A_DESCRIPTION;
        descriptor = new EditTaskDescriptorBuilder()
                .withTaskDescription(SampleDataUtil.getValidTask().get(1)).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
        // progress
        userInput = targetIndex.getOneBased() + TASK_A_PROGRESS;
        descriptor = new EditTaskDescriptorBuilder()
                .withProgress(SampleDataUtil.getValidTask().get(3)).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PROJECT;
        String userInput = targetIndex.getOneBased() + TASK_A_NAME + TASK_A_PROGRESS + TASK_A_DEADLINE + TASK_A_NAME
                + TASK_A_NAME + TASK_A_PROGRESS + TASK_A_DESCRIPTION;

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withTaskName(SampleDataUtil.getValidTask().get(0))
                .withDeadline(SampleDataUtil.getValidTask().get(2))
                .withProgress(SampleDataUtil.getValidTask().get(3))
                .withTaskDescription(SampleDataUtil.getValidTask().get(1)).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PROJECT;
        String userInput = targetIndex.getOneBased() + INVALID_DEADLINE + TASK_A_DEADLINE;
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withDeadline(SampleDataUtil.getValidTask().get(2)).build();

        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + TASK_A_DESCRIPTION + INVALID_DEADLINE + TASK_A_NAME + TASK_A_DEADLINE;
        descriptor = new EditTaskDescriptorBuilder()
                .withTaskName(SampleDataUtil.getValidTask().get(0))
                .withDeadline(SampleDataUtil.getValidTask().get(2))
                .withTaskDescription(SampleDataUtil.getValidTask().get(1)).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
