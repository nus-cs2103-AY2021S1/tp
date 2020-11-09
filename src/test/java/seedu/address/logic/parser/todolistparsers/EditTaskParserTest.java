package seedu.address.logic.parser.todolistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_LAB07;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_LAB07;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_LAB07;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DAILY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LAB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LAB07;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_LAB07;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LAB07;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_LAB07;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DAILY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LAB07;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.todolistcommands.EditTaskCommand;
import seedu.address.logic.commands.todolistcommands.EditTaskDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.Priority;
import seedu.address.model.task.TaskName;
import seedu.address.testutil.todolist.EditTaskDescriptorBuilder;

public class EditTaskParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String PRIORITY_EMPTY = " " + PREFIX_PRIORITY;
    private static final String DATE_EMPTY = " " + PREFIX_DATE;

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private EditTaskParser parser = new EditTaskParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_LAB05, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTaskCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_LAB05, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_LAB05, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TASK_NAME_DESC, TaskName.MESSAGE_CONSTRAINTS); // invalid task name
        assertParseFailure(parser, "1" + INVALID_TASK_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, "1" + INVALID_TASK_PRIORITY_DESC, Priority.MESSAGE_CONSTRAINTS); // invalid priority
        assertParseFailure(parser, "1" + INVALID_TASK_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid date

        // invalid tag followed by valid priority
        assertParseFailure(parser, "1" + INVALID_TASK_TAG_DESC + PRIORITY_DESC_LAB05, Tag.MESSAGE_CONSTRAINTS);

        // valid tag followed by invalid tag (has been tested in add test)
        assertParseFailure(parser, "1" + TAG_DESC_LAB05 + INVALID_TASK_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // prefix with empty description will overwrite the whole tags
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_LAB + TAG_DESC_DAILY + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_LAB + TAG_EMPTY + TAG_DESC_DAILY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_LAB + TAG_DESC_DAILY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_DAILY + TAG_EMPTY + TAG_DESC_LAB, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TASK_NAME_DESC + INVALID_TASK_PRIORITY_DESC + VALID_DATE_LAB05
                + VALID_TAG_LAB05, TaskName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_TASK;
        String userInput = targetIndex.getOneBased() + TAG_DESC_LAB05 + TAG_DESC_DAILY
                + PRIORITY_DESC_LAB07 + DATE_DESC_LAB07 + NAME_DESC_LAB05 + TAG_DESC_LAB;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_LAB05)
                .withPriority(VALID_PRIORITY_LAB07).withDate(VALID_DATE_LAB07)
                .withTags(VALID_TAG_DAILY, VALID_TAG_LAB, VALID_TAG_LAB05).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + TAG_DESC_LAB05 + PRIORITY_DESC_LAB07;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTags(VALID_TAG_LAB05)
                .withPriority(VALID_PRIORITY_LAB07).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // task name
        Index targetIndex = INDEX_THIRD_TASK;
        String userInput = targetIndex.getOneBased() + NAME_DESC_LAB07;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_LAB07).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tag
        userInput = targetIndex.getOneBased() + TAG_DESC_LAB07;
        descriptor = new EditTaskDescriptorBuilder().withTags(VALID_TAG_LAB07).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // priority
        userInput = targetIndex.getOneBased() + PRIORITY_DESC_LAB07;
        descriptor = new EditTaskDescriptorBuilder().withPriority(VALID_PRIORITY_LAB07).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_LAB07;
        descriptor = new EditTaskDescriptorBuilder().withDate(VALID_DATE_LAB07).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + TAG_DESC_LAB07 + DATE_DESC_LAB07 + PRIORITY_DESC_LAB07
                + TAG_DESC_LAB + TAG_DESC_LAB07 + DATE_DESC_LAB07 + PRIORITY_DESC_LAB07 + TAG_DESC_LAB
                + TAG_DESC_LAB05 + DATE_DESC_LAB05 + PRIORITY_DESC_LAB05 + TAG_DESC_DAILY;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withPriority(VALID_PRIORITY_LAB05)
                .withDate(VALID_DATE_LAB05).withTags(VALID_TAG_LAB05, VALID_TAG_LAB07, VALID_TAG_LAB, VALID_TAG_DAILY)
                .build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + INVALID_TASK_PRIORITY_DESC + PRIORITY_DESC_LAB05;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withPriority(VALID_PRIORITY_LAB05).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + PRIORITY_DESC_LAB05 + INVALID_TASK_DATE_DESC + DATE_DESC_LAB05;
        descriptor = new EditTaskDescriptorBuilder().withPriority(VALID_PRIORITY_LAB05)
                .withDate(VALID_DATE_LAB05).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_TASK;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTags().build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetPriority_success() {
        Index targetIndex = INDEX_THIRD_TASK;
        String userInput = targetIndex.getOneBased() + PRIORITY_EMPTY;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withPriority(null).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetDate_success() {
        Index targetIndex = INDEX_THIRD_TASK;
        String userInput = targetIndex.getOneBased() + DATE_EMPTY;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withDate(null).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
