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
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalProjects.AI;
import static seedu.address.testutil.TypicalProjects.BOT;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.global.AddCommand;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.RepoUrl;
import seedu.address.model.tag.ProjectTag;
import seedu.address.testutil.ProjectBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        ArrayList<String> testTask1 = new ArrayList<>();
        testTask1.add(VALID_PROJECT_TAG_DG);
        testTask1.add(null);
        testTask1.add(null);
        testTask1.add("0");
        testTask1.add("false");
        ArrayList<String> testTask2 = new ArrayList<>();
        testTask2.add(VALID_TASK_MODEL);
        testTask2.add(null);
        testTask2.add(null);
        testTask2.add("0");
        testTask2.add("false");
        Project expectedProject = new ProjectBuilder(BOT).withTags(VALID_PROJECT_TAG_B).withTasks(
                testTask1).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + PROJECT_NAME_DESC_BOB + DEADLINE_DESC_B
                + REPOURL_DESC_B
                + PROJECT_DESCRIPTION_DESC_BOB + PROJECT_TAG_DESC_FRIEND + TASK_DESC_DG, new AddCommand(
                expectedProject));

        // multiple project names - last name accepted
        assertParseSuccess(parser, PROJECT_NAME_DESC_AMY + PROJECT_NAME_DESC_BOB + DEADLINE_DESC_B
                + REPOURL_DESC_B + PROJECT_DESCRIPTION_DESC_BOB + PROJECT_TAG_DESC_FRIEND + TASK_DESC_DG,
                new AddCommand(
                expectedProject));

        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser, PROJECT_NAME_DESC_BOB + DEADLINE_DESC_A + DEADLINE_DESC_B
                + REPOURL_DESC_B + PROJECT_DESCRIPTION_DESC_BOB + PROJECT_TAG_DESC_FRIEND + TASK_DESC_DG,
                new AddCommand(
                expectedProject));

        // multiple repoUrl - last repoUrl accepted
        assertParseSuccess(parser, PROJECT_NAME_DESC_BOB + DEADLINE_DESC_B + REPOURL_DESC_A
                + REPOURL_DESC_B + PROJECT_DESCRIPTION_DESC_BOB + PROJECT_TAG_DESC_FRIEND + TASK_DESC_DG,
                new AddCommand(
                expectedProject));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, PROJECT_NAME_DESC_BOB + DEADLINE_DESC_B + REPOURL_DESC_B
                + PROJECT_DESCRIPTION_DESC_AMY + PROJECT_DESCRIPTION_DESC_BOB + PROJECT_TAG_DESC_FRIEND
                + TASK_DESC_DG, new AddCommand(expectedProject));

        // multiple project tags - all accepted
        Project expectedProjectMultipleTags = new ProjectBuilder(BOT).withTags(VALID_PROJECT_TAG_B,
                VALID_PROJECT_TAG_A).withTasks(testTask1)
                .build();
        assertParseSuccess(parser, PROJECT_NAME_DESC_BOB + DEADLINE_DESC_B + REPOURL_DESC_B
                + PROJECT_DESCRIPTION_DESC_BOB
                + PROJECT_TAG_DESC_HUSBAND + PROJECT_TAG_DESC_FRIEND + TASK_DESC_DG, new AddCommand(
                expectedProjectMultipleTags));

        // multiple tasks - all accepted
        Project expectedProjectMultipleTasks = new ProjectBuilder(BOT)
                .withTasks(testTask1, testTask2)
                .build();
        assertParseSuccess(parser, PROJECT_NAME_DESC_BOB + DEADLINE_DESC_B + REPOURL_DESC_B
                        + PROJECT_DESCRIPTION_DESC_BOB
                        + PROJECT_TAG_DESC_FRIEND + PROJECT_TAG_DESC_HUSBAND + TASK_DESC_DG + TASK_DESC_MODEL,
                new AddCommand(expectedProjectMultipleTasks));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        ArrayList<String> testTask1 = new ArrayList<>();
        testTask1.add(VALID_PROJECT_TAG_DG);
        testTask1.add(null);
        testTask1.add(null);
        testTask1.add("0");
        testTask1.add("false");
        ArrayList<String> testTask2 = new ArrayList<>();
        testTask2.add(VALID_TASK_MODEL);
        testTask2.add(null);
        testTask2.add(null);
        testTask2.add("0");
        testTask2.add("false");

        // zero tags
        Project expectedProject = new ProjectBuilder(AI).withTags().withTasks(testTask1, testTask2).build();
        assertParseSuccess(parser, PROJECT_NAME_DESC_AMY + DEADLINE_DESC_A + REPOURL_DESC_A
                + PROJECT_DESCRIPTION_DESC_AMY
                + TASK_DESC_DG + TASK_DESC_MODEL, new AddCommand(expectedProject));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
        // missing name prefix
        assertParseFailure(parser, VALID_PROJECT_NAME_B + DEADLINE_DESC_B + REPOURL_DESC_B
                + PROJECT_DESCRIPTION_DESC_BOB,
            expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, PROJECT_NAME_DESC_BOB + VALID_DEADLINE_B + REPOURL_DESC_B
                + PROJECT_DESCRIPTION_DESC_BOB,
            expectedMessage);

        // missing email prefix
        assertParseFailure(parser, PROJECT_NAME_DESC_BOB + DEADLINE_DESC_B + VALID_REPOURL_B
                        + PROJECT_DESCRIPTION_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, PROJECT_NAME_DESC_BOB + DEADLINE_DESC_B + REPOURL_DESC_B
                + VALID_PROJECT_DESCRIPTION_B,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_PROJECT_NAME_B + VALID_DEADLINE_B + VALID_REPOURL_B
                + VALID_PROJECT_DESCRIPTION_B,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid project name
        assertParseFailure(parser, INVALID_NAME_DESC + DEADLINE_DESC_B + REPOURL_DESC_B
                + PROJECT_DESCRIPTION_DESC_BOB
                + PROJECT_TAG_DESC_HUSBAND + PROJECT_TAG_DESC_FRIEND, ProjectName.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser,
                PROJECT_NAME_DESC_BOB + INVALID_DEADLINE_DESC + REPOURL_DESC_B
                        + PROJECT_DESCRIPTION_DESC_BOB
                        + PROJECT_TAG_DESC_HUSBAND + PROJECT_TAG_DESC_FRIEND, Deadline.MESSAGE_CONSTRAINTS);

        // invalid repoUrl
        assertParseFailure(parser,
                PROJECT_NAME_DESC_BOB + DEADLINE_DESC_B + INVALID_REPOURL_DESC
                        + PROJECT_DESCRIPTION_DESC_BOB
                        + PROJECT_TAG_DESC_HUSBAND + PROJECT_TAG_DESC_FRIEND, RepoUrl.MESSAGE_CONSTRAINTS);

        // invalid project description
        assertParseFailure(parser,
                PROJECT_NAME_DESC_BOB + DEADLINE_DESC_B + REPOURL_DESC_B + INVALID_PROJECT_DESCRIPTION_DESC
                        + PROJECT_TAG_DESC_HUSBAND + PROJECT_TAG_DESC_FRIEND, ProjectDescription.MESSAGE_CONSTRAINTS);

        // invalid project tag
        assertParseFailure(parser,
            PROJECT_NAME_DESC_BOB + DEADLINE_DESC_B + REPOURL_DESC_B + PROJECT_DESCRIPTION_DESC_BOB
                + INVALID_PROJECT_TAG_DESC + VALID_PROJECT_TAG_B, ProjectTag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + DEADLINE_DESC_B + REPOURL_DESC_B
                        + INVALID_PROJECT_DESCRIPTION_DESC,
                ProjectName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + PROJECT_NAME_DESC_BOB + DEADLINE_DESC_B
                        + REPOURL_DESC_B
                        + PROJECT_DESCRIPTION_DESC_BOB + PROJECT_TAG_DESC_HUSBAND + PROJECT_TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
