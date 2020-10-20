package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROJECT_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROJECT_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REPOURL_DESC;
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

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.global.EditCommand;
import seedu.address.logic.commands.global.EditCommand.EditProjectDescriptor;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.RepoUrl;
import seedu.address.model.tag.ProjectTag;
import seedu.address.testutil.EditProjectDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_PROJECT_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_PROJECT_NAME_A, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + PROJECT_NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + PROJECT_NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, ProjectName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC, Deadline.MESSAGE_CONSTRAINTS); // invalid deadline
        assertParseFailure(parser, "1" + INVALID_REPOURL_DESC, RepoUrl.MESSAGE_CONSTRAINTS); // invalid repoUrl
        assertParseFailure(parser, "1" + INVALID_PROJECT_DESCRIPTION_DESC,
            ProjectDescription.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_PROJECT_TAG_DESC, ProjectTag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC + REPOURL_DESC_A, Deadline.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + DEADLINE_DESC_B + INVALID_DEADLINE_DESC, Deadline.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Project} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + PROJECT_TAG_DESC_FRIEND + PROJECT_TAG_DESC_HUSBAND + TAG_EMPTY,
            ProjectTag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + PROJECT_TAG_DESC_FRIEND + TAG_EMPTY + PROJECT_TAG_DESC_HUSBAND,
            ProjectTag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + PROJECT_TAG_DESC_FRIEND + PROJECT_TAG_DESC_HUSBAND,
            ProjectTag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_REPOURL_DESC + VALID_PROJECT_DESCRIPTION_A
                + VALID_DEADLINE_A,
                ProjectName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PROJECT;
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_B + PROJECT_TAG_DESC_HUSBAND + TASK_DESC_DG
                + REPOURL_DESC_A + PROJECT_DESCRIPTION_DESC_AMY + PROJECT_NAME_DESC_AMY + PROJECT_TAG_DESC_FRIEND
            + TASK_DESC_MODEL;

        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder().withProjectName(VALID_PROJECT_NAME_A)
                .withDeadline(VALID_DEADLINE_B).withRepoUrl(VALID_REPOURL_A).withProjectDescription(
                VALID_PROJECT_DESCRIPTION_A)
                .withTags(VALID_PROJECT_TAG_A, VALID_PROJECT_TAG_B).withTasks(VALID_PROJECT_TAG_DG,
                VALID_TASK_MODEL).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PROJECT;
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_B + REPOURL_DESC_A;

        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder().withDeadline(VALID_DEADLINE_B)
                .withRepoUrl(VALID_REPOURL_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PROJECT;
        String userInput = targetIndex.getOneBased() + PROJECT_NAME_DESC_AMY;
        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder().withProjectName(
            VALID_PROJECT_NAME_A).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_A;
        descriptor = new EditProjectDescriptorBuilder().withDeadline(VALID_DEADLINE_A).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // repoUrl
        userInput = targetIndex.getOneBased() + REPOURL_DESC_A;
        descriptor = new EditProjectDescriptorBuilder().withRepoUrl(VALID_REPOURL_A).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + PROJECT_DESCRIPTION_DESC_AMY;
        descriptor = new EditProjectDescriptorBuilder().withProjectDescription(VALID_PROJECT_DESCRIPTION_A).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + PROJECT_TAG_DESC_FRIEND;
        descriptor = new EditProjectDescriptorBuilder().withTags(VALID_PROJECT_TAG_B).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tasks
        userInput = targetIndex.getOneBased() + TASK_DESC_MODEL;
        descriptor = new EditProjectDescriptorBuilder().withTasks(VALID_TASK_MODEL).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PROJECT;
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_A + PROJECT_DESCRIPTION_DESC_AMY
            + REPOURL_DESC_A
                + PROJECT_TAG_DESC_FRIEND + DEADLINE_DESC_A + PROJECT_DESCRIPTION_DESC_AMY + REPOURL_DESC_A
            + PROJECT_TAG_DESC_FRIEND + TASK_DESC_DG
                + DEADLINE_DESC_B + PROJECT_DESCRIPTION_DESC_BOB + REPOURL_DESC_B + PROJECT_TAG_DESC_HUSBAND
            + TASK_DESC_MODEL;

        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder().withDeadline(VALID_DEADLINE_B)
                .withRepoUrl(VALID_REPOURL_B).withProjectDescription(VALID_PROJECT_DESCRIPTION_B).withTags(
                VALID_PROJECT_TAG_B,
                VALID_PROJECT_TAG_A).withTasks(VALID_PROJECT_TAG_DG, VALID_TASK_MODEL)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PROJECT;
        String userInput = targetIndex.getOneBased() + INVALID_DEADLINE_DESC + DEADLINE_DESC_B;
        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder().withDeadline(VALID_DEADLINE_B).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + REPOURL_DESC_B + INVALID_DEADLINE_DESC + PROJECT_DESCRIPTION_DESC_BOB
                + DEADLINE_DESC_B;
        descriptor = new EditProjectDescriptorBuilder().withDeadline(VALID_DEADLINE_B).withRepoUrl(VALID_REPOURL_B)
                .withProjectDescription(VALID_PROJECT_DESCRIPTION_B).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PROJECT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
