package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MULTIPLE_PREFIXES_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_HW;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_LAB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_HW;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_LAB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_HW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_HW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_HW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_HW;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ASSIGNMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditAssignmentDescriptor;
import seedu.address.model.task.ModuleCode;
import seedu.address.model.task.Name;
import seedu.address.model.task.Time;
import seedu.address.testutil.EditAssignmentDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_HW, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_HW, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_HW, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC, Time.MESSAGE_CONSTRAINTS); // invalid deadline
        assertParseFailure(parser, "1" + INVALID_MODULE_CODE_DESC,
                ModuleCode.MESSAGE_CONSTRAINTS); // invalid module code

        // multiple invalid values - first invalid value caught
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_DEADLINE_DESC
                + INVALID_MODULE_CODE_DESC, Name.MESSAGE_CONSTRAINTS);

        // valid deadline followed by invalid deadline.
        assertParseFailure(parser, "1" + DEADLINE_DESC_LAB + INVALID_DEADLINE_DESC, MESSAGE_MULTIPLE_PREFIXES_FOUND);

        // invalid deadline followed by valid deadline
        Index targetIndex = INDEX_FIRST_ASSIGNMENT;
        String userInput = targetIndex.getOneBased() + INVALID_DEADLINE_DESC + DEADLINE_DESC_LAB;
        assertParseFailure(parser, userInput, MESSAGE_MULTIPLE_PREFIXES_FOUND);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_DEADLINE_DESC + MODULE_CODE_DESC_LAB
                + DEADLINE_DESC_LAB;
        assertParseFailure(parser, userInput, MESSAGE_MULTIPLE_PREFIXES_FOUND);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ASSIGNMENT;
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_LAB + MODULE_CODE_DESC_HW
                + NAME_DESC_HW;
        EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder()
                .withName(VALID_NAME_HW)
                .withDeadline(VALID_DEADLINE_LAB).withModuleCode(VALID_MODULE_CODE_HW).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ASSIGNMENT;
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_LAB;
        EditCommand.EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder()
                .withDeadline(VALID_DEADLINE_LAB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_ASSIGNMENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_HW;
        EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder().withName(VALID_NAME_HW).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_HW;
        descriptor = new EditAssignmentDescriptorBuilder().withDeadline(VALID_DEADLINE_HW).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // module code
        userInput = targetIndex.getOneBased() + MODULE_CODE_DESC_HW;
        descriptor = new EditAssignmentDescriptorBuilder().withModuleCode(VALID_MODULE_CODE_HW).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_throwsParseException() {
        Index targetIndex = INDEX_FIRST_ASSIGNMENT;
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_HW + MODULE_CODE_DESC_HW
                 + DEADLINE_DESC_HW + MODULE_CODE_DESC_HW + DEADLINE_DESC_LAB + MODULE_CODE_DESC_LAB;

        assertParseFailure(parser, userInput, MESSAGE_MULTIPLE_PREFIXES_FOUND);
    }
}
