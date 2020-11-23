package seedu.tr4cker.logic.parser;

import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DEADLINE_DESC_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DEADLINE_DESC_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DESCRIPTION_DESC_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DESCRIPTION_DESC_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.tr4cker.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.tr4cker.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.tr4cker.logic.commands.CommandTestUtil.NAME_DESC_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DEADLINE_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DEADLINE_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DESCRIPTION_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DESCRIPTION_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_NAME_1;
import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_THIRD_TASK;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.EditCommand;
import seedu.tr4cker.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.tr4cker.logic.commands.EditCommand.EditTodoDescriptor;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.TaskDescription;
import seedu.tr4cker.testutil.EditTaskDescriptorBuilder;
import seedu.tr4cker.testutil.EditTodoDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_1, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-1" + NAME_DESC_1, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_1, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC, Deadline.MESSAGE_CONSTRAINTS); // invalid deadline
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC,
                TaskDescription.MESSAGE_CONSTRAINTS); // invalid tr4cker

        // invalid deadline followed by valid description
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC + DESCRIPTION_DESC_1, Deadline.MESSAGE_CONSTRAINTS);

        // valid deadline followed by invalid deadline. The test case for invalid deadline followed by valid deadline
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + DEADLINE_DESC_2 + INVALID_DEADLINE_DESC, Deadline.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC
                + VALID_DESCRIPTION_1 + VALID_DEADLINE_1, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_TASK;
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_2
                + DESCRIPTION_DESC_1 + NAME_DESC_1;

        EditTaskDescriptor descriptor1 = new EditTaskDescriptorBuilder().withName(VALID_NAME_1)
                .withDeadline(VALID_DEADLINE_2).withTaskDescription(VALID_DESCRIPTION_1).build();
        EditTodoDescriptor descriptor2 = new EditTodoDescriptorBuilder().withName(VALID_NAME_1)
                .withDeadline(VALID_DEADLINE_2).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor1, descriptor2);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_2 + DESCRIPTION_DESC_1;

        EditTaskDescriptor descriptor1 = new EditTaskDescriptorBuilder().withDeadline(VALID_DEADLINE_2)
                .withTaskDescription(VALID_DESCRIPTION_1).build();
        EditTodoDescriptor descriptor2 = new EditTodoDescriptorBuilder().withDeadline(VALID_DEADLINE_2).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor1, descriptor2);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_TASK;
        String userInput = targetIndex.getOneBased() + NAME_DESC_1;
        EditTaskDescriptor descriptor1 = new EditTaskDescriptorBuilder().withName(VALID_NAME_1).build();
        EditTodoDescriptor descriptor2 = new EditTodoDescriptorBuilder().withName(VALID_NAME_1).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor1, descriptor2);
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_1;
        descriptor1 = new EditTaskDescriptorBuilder().withDeadline(VALID_DEADLINE_1).build();
        descriptor2 = new EditTodoDescriptorBuilder().withDeadline(VALID_DEADLINE_1).build();
        expectedCommand = new EditCommand(targetIndex, descriptor1, descriptor2);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_1;
        descriptor1 = new EditTaskDescriptorBuilder().withTaskDescription(VALID_DESCRIPTION_1).build();
        descriptor2 = new EditTodoDescriptorBuilder().build();
        expectedCommand = new EditCommand(targetIndex, descriptor1, descriptor2);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_1 + DESCRIPTION_DESC_1
                + DEADLINE_DESC_1 + DESCRIPTION_DESC_1
                + DEADLINE_DESC_2 + DESCRIPTION_DESC_2;

        EditTaskDescriptor descriptor1 = new EditTaskDescriptorBuilder().withDeadline(VALID_DEADLINE_2)
                .withTaskDescription(VALID_DESCRIPTION_2).build();
        EditTodoDescriptor descriptor2 = new EditTodoDescriptorBuilder().withDeadline(VALID_DEADLINE_2).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor1, descriptor2);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + INVALID_DEADLINE_DESC + DEADLINE_DESC_2;
        EditTaskDescriptor descriptor1 = new EditTaskDescriptorBuilder().withDeadline(VALID_DEADLINE_2).build();
        EditTodoDescriptor descriptor2 = new EditTodoDescriptorBuilder().withDeadline(VALID_DEADLINE_2).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor1, descriptor2);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_DEADLINE_DESC + DESCRIPTION_DESC_2
                + DEADLINE_DESC_2;
        descriptor1 = new EditTaskDescriptorBuilder().withDeadline(VALID_DEADLINE_2)
                .withTaskDescription(VALID_DESCRIPTION_2).build();
        descriptor2 = new EditTodoDescriptorBuilder().withDeadline(VALID_DEADLINE_2).build();
        expectedCommand = new EditCommand(targetIndex, descriptor1, descriptor2);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
