package seedu.address.logic.parser.todolistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LAB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LAB05;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_05;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.todolistcommands.AddTaskCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;
import seedu.address.testutil.todolist.TaskBuilder;

public class AddTaskParserTest {
    private AddTaskParser parser = new AddTaskParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(LAB_05).withTags(VALID_TAG_CS2100).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_LAB05 + TAG_DESC_CS2100 + PRIORITY_DESC_LAB05
                + DATE_DESC_LAB05, new AddTaskCommand(expectedTask));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_LAB05 + NAME_DESC_LAB05 + TAG_DESC_CS2100 + PRIORITY_DESC_LAB05
                + DATE_DESC_LAB05, new AddTaskCommand(expectedTask));

        // multiple tags - all tags considered
        assertParseSuccess(parser, NAME_DESC_LAB05 + TAG_DESC_CS2100 + TAG_DESC_CS2100 + PRIORITY_DESC_LAB05
                + DATE_DESC_LAB05, new AddTaskCommand(expectedTask));

        // multiple priorities - last priority accepted
        assertParseSuccess(parser, NAME_DESC_LAB05 + TAG_DESC_CS2100 + PRIORITY_DESC_LAB05 + PRIORITY_DESC_LAB05
                + DATE_DESC_LAB05, new AddTaskCommand(expectedTask));

        // multiple date - last date accepted
        assertParseSuccess(parser, NAME_DESC_LAB05 + TAG_DESC_CS2100 + PRIORITY_DESC_LAB05 + DATE_DESC_LAB05
                + DATE_DESC_LAB05, new AddTaskCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder(LAB_05).withTags(VALID_TAG_CS2100, VALID_TAG_LAB)
                .build();
        assertParseSuccess(parser, NAME_DESC_LAB05 + TAG_DESC_CS2100 + PRIORITY_DESC_LAB05 + DATE_DESC_LAB05
                + TAG_DESC_LAB, new AddTaskCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder(LAB_05).withTags().build();
        assertParseSuccess(parser, NAME_DESC_LAB05 + PRIORITY_DESC_LAB05 + DATE_DESC_LAB05,
                new AddTaskCommand(expectedTask));

        // no priority
        expectedTask = new TaskBuilder(LAB_05).withPriority(null).build();
        assertParseSuccess(parser, NAME_DESC_LAB05 + DATE_DESC_LAB05 + TAG_DESC_LAB05,
                new AddTaskCommand(expectedTask));

        // no date
        expectedTask = new TaskBuilder(LAB_05).withDate(null).build();
        assertParseSuccess(parser, NAME_DESC_LAB05 + PRIORITY_DESC_LAB05 + TAG_DESC_LAB05,
                new AddTaskCommand(expectedTask));

        // no date and priority
        expectedTask = new TaskBuilder(LAB_05).withDate(null).withPriority(null).build();
        assertParseSuccess(parser, NAME_DESC_LAB05 + TAG_DESC_LAB05,
                new AddTaskCommand(expectedTask));

        // only name
        expectedTask = new TaskBuilder(LAB_05).withDate(null).withPriority(null).withTags().build();
        assertParseSuccess(parser, NAME_DESC_LAB05,
                new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_LAB05 + TAG_DESC_CS2100 + PRIORITY_DESC_LAB05 + DATE_DESC_LAB05,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_LAB05 + VALID_TAG_LAB05 + VALID_PRIORITY_LAB05
                + VALID_DATE_LAB05, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_TASK_NAME_DESC + TAG_DESC_CS2100 + PRIORITY_DESC_LAB05
                + DATE_DESC_LAB05 + TAG_DESC_LAB, TaskName.MESSAGE_CONSTRAINTS);

        // invalid name with whitespaces only
        assertParseFailure(parser, "n/  " + TAG_DESC_CS2100 + PRIORITY_DESC_LAB05
            + DATE_DESC_LAB05 + TAG_DESC_LAB, TaskName.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_LAB05 + INVALID_TAG_DESC + PRIORITY_DESC_LAB05
                + DATE_DESC_LAB05 + TAG_DESC_LAB, Tag.MESSAGE_CONSTRAINTS);

        // invalid priority
        assertParseFailure(parser, NAME_DESC_LAB05 + TAG_DESC_CS2100 + INVALID_TASK_PRIORITY_DESC
                + DATE_DESC_LAB05 + TAG_DESC_LAB, Priority.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NAME_DESC_LAB05 + TAG_DESC_CS2100 + PRIORITY_DESC_LAB05
                + INVALID_TASK_DATE_DESC + TAG_DESC_LAB, Date.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TASK_NAME_DESC + TAG_DESC_CS2100 + PRIORITY_DESC_LAB05
                + INVALID_TASK_DATE_DESC, TaskName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_LAB05 + TAG_DESC_CS2100 + PRIORITY_DESC_LAB05
                + DATE_DESC_LAB05 + TAG_DESC_LAB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
    }
}
