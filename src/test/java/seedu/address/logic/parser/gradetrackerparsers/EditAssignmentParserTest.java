package seedu.address.logic.parser.gradetrackerparsers;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.gradetrackercommands.EditAssignmentCommand;
import seedu.address.logic.commands.gradetrackercommands.EditAssignmentDescriptor;
import seedu.address.logic.commands.schedulercommands.EditEventCommand;
import seedu.address.logic.parser.schedulerparsers.EditEventParser;
import seedu.address.model.event.EventName;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.AssignmentName;
import seedu.address.model.module.grade.AssignmentPercentage;
import seedu.address.model.module.grade.AssignmentResult;
import seedu.address.testutil.event.EditEventDescriptorBuilder;
import seedu.address.testutil.gradetracker.EditAssignmentDescriptorBuilder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_1;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

public class EditAssignmentParserTest {

    private static final Index INDEX = Index.fromOneBased(1);

    private static final String validInputAllFields = " " + INDEX.getOneBased()
            + " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String validInputAssignmentName = " " + INDEX.getOneBased()
            + " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1;

    private static final String validInputAssignmentPercentage = " " + INDEX.getOneBased()
            + " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1;

    private static final String validInputAssignmentResult = " " + INDEX.getOneBased()
            + " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String invalidIndex = " a "
            + " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1;

    private static final String invalidModuleName = " " + INDEX.getOneBased()
            + " " + PREFIX_NAME + INVALID_MODULENAME_CSA200
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1;

    private static final String invalidAssignmentName = " " + INDEX.getOneBased()
            + " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + INVALID_ASSIGNMENT_NAME;

    private static final String invalidAssignmentPercentage = " " + INDEX.getOneBased()
            + " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + INVALID_ASSIGNMENT_PERCENTAGE;

    private static final String invalidAssignmentResult = " " + INDEX.getOneBased()
            + " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_RESULT + INVALID_ASSIGNMENT_RESULT;

    private static final String invalidMissingEditFields = " " + INDEX.getOneBased()
            + " " + PREFIX_NAME + VALID_MODULENAME_CS2103T;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAssignmentCommand.MESSAGE_USAGE);

    private EditAssignmentParser parser = new EditAssignmentParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        EditAssignmentDescriptor expectedDescriptor = new EditAssignmentDescriptorBuilder()
                .withAssignmentName(VALID_ASSIGNMENT_NAME_1).withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2103T);
        EditAssignmentCommand expectedCommand = new EditAssignmentCommand(INDEX, expectedModuleName, expectedDescriptor);
        assertParseSuccess(parser, validInputAllFields, expectedCommand);
    }

    @Test
    public void parse_onlyAssignmentNameSpecified_success() {
        EditAssignmentDescriptor expectedDescriptor = new EditAssignmentDescriptorBuilder()
                .withAssignmentName(VALID_ASSIGNMENT_NAME_1).build();
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2103T);
        EditAssignmentCommand expectedCommand = new EditAssignmentCommand(INDEX, expectedModuleName, expectedDescriptor);
        assertParseSuccess(parser, validInputAssignmentName, expectedCommand);
    }

    @Test
    public void parse_onlyAssignmentPercentageSpecified_success() {
        EditAssignmentDescriptor expectedDescriptor = new EditAssignmentDescriptorBuilder()
                .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1).build();
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2103T);
        EditAssignmentCommand expectedCommand = new EditAssignmentCommand(INDEX, expectedModuleName, expectedDescriptor);
        assertParseSuccess(parser, validInputAssignmentPercentage, expectedCommand);
    }

    @Test
    public void parse_onlyAssignmentResultSpecified_success() {
        EditAssignmentDescriptor expectedDescriptor = new EditAssignmentDescriptorBuilder()
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2103T);
        EditAssignmentCommand expectedCommand = new EditAssignmentCommand(INDEX, expectedModuleName, expectedDescriptor);
        assertParseSuccess(parser, validInputAssignmentResult, expectedCommand);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, invalidIndex, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidModuleName_failure() {
        assertParseFailure(parser, invalidModuleName, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidAssignmentName_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignmentName.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, invalidAssignmentName, expectedMessage);
    }

    @Test
    public void parse_invalidAssignmentPercentage_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignmentPercentage.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, invalidAssignmentPercentage, expectedMessage);
    }

    @Test
    public void parse_invalidAssignmentResult_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignmentResult.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, invalidAssignmentResult, expectedMessage);
    }

    @Test
    public void parse_missingEditFields_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAssignmentCommand.MESSAGE_NOT_EDITED);
        assertParseFailure(parser, invalidMissingEditFields, expectedMessage);
    }
}
