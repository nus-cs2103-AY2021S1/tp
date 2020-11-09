package seedu.address.logic.parser.gradetrackerparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ASSIGNMENT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ASSIGNMENT_PERCENTAGE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ASSIGNMENT_RESULT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULENAME_CSA200;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_NAME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_NAME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_PERCENTAGE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_PERCENTAGE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_RESULT_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_RESULT_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_CS2103T;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_PERCENTAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_RESULT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.gradetrackercommands.AddAssignmentCommand;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.Assignment;
import seedu.address.model.module.grade.AssignmentName;
import seedu.address.model.module.grade.AssignmentPercentage;
import seedu.address.model.module.grade.AssignmentResult;
import seedu.address.testutil.gradetracker.AssignmentBuilder;


public class AddAssignmentParserTest {

    private static final String VALID_INPUT = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String MULTIPLE_MODULE_NAMES = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_NAME + VALID_MODULENAME_CS2030
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String MULTIPLE_ASSIGNMENT_NAMES = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_2
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String MULTIPLE_ASSIGNMENT_PERCENTAGES = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_2
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String MULTIPLE_ASSIGNMENT_RESULTS = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_2;

    private static final String INVALID_MODULE_NAME = " " + PREFIX_NAME + INVALID_MODULENAME_CSA200
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String INVALID_ASSIGNMENT_NAME_FIELD = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + INVALID_ASSIGNMENT_NAME
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String INVALID_ASSIGNMENT_PERCENTAGE_FIELD = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + INVALID_ASSIGNMENT_PERCENTAGE
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String INVALID_ASSIGNMENT_RESULT_FIELD = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_RESULT + INVALID_ASSIGNMENT_RESULT;

    private static final String MISSING_MODULE_NAME = " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String MISSING_ASSIGNMENT_NAME = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String MISSING_ASSIGNMENT_PERCENTAGE = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String MISSING_ASSIGNMENT_RESULT = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1;

    private AddAssignmentParser parser = new AddAssignmentParser();

    @Test
    public void parse_allFieldsPresent_success() {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2103T);
        Assignment expectedAssignment = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_1)
                .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();
        assertParseSuccess(parser, VALID_INPUT, new AddAssignmentCommand(expectedModuleName, expectedAssignment));
    }

    @Test
    public void parse_preambleWhiteSpace_success() {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2103T);
        Assignment expectedAssignment = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_1)
                .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_INPUT,
                new AddAssignmentCommand(expectedModuleName, expectedAssignment));
    }

    @Test
    public void parse_multipleModuleNames_success() {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2030);
        Assignment expectedAssignment = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_1)
                .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();

        //takes in second module name
        assertParseSuccess(parser, MULTIPLE_MODULE_NAMES, new AddAssignmentCommand(expectedModuleName,
                expectedAssignment));
    }

    @Test
    public void parse_multipleAssignmentNames_success() {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2103T);
        Assignment expectedAssignment = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_2)
                .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();

        //takes in second assignment name
        assertParseSuccess(parser, MULTIPLE_ASSIGNMENT_NAMES, new AddAssignmentCommand(expectedModuleName,
                expectedAssignment));
    }

    @Test
    public void parse_multipleAssignmentPercentages_success() {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2103T);
        Assignment expectedAssignment = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_1)
                .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_2)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();

        //takes in second assignment percentage
        assertParseSuccess(parser, MULTIPLE_ASSIGNMENT_PERCENTAGES, new AddAssignmentCommand(expectedModuleName,
                expectedAssignment));
    }

    @Test
    public void parse_multipleAssignmentResults_success() {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2103T);
        Assignment expectedAssignment = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_1)
                .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_2).build();

        //takes in second assignment percentage
        assertParseSuccess(parser, MULTIPLE_ASSIGNMENT_RESULTS, new AddAssignmentCommand(expectedModuleName,
                expectedAssignment));
    }

    @Test
    public void parse_compulsoryModuleNameFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, MISSING_MODULE_NAME, expectedMessage);
    }

    @Test
    public void parse_compulsoryAssignmentNameMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, MISSING_ASSIGNMENT_NAME, expectedMessage);
    }

    @Test
    public void parse_compulsoryAssignmentPercentageMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, MISSING_ASSIGNMENT_PERCENTAGE, expectedMessage);
    }

    @Test
    public void parse_compulsoryAssignmentResultMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, MISSING_ASSIGNMENT_RESULT, expectedMessage);
    }

    @Test
    public void parse_invalidModuleName_failure() {
        assertParseFailure(parser, INVALID_MODULE_NAME, ModuleName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidAssignmentName_failure() {
        assertParseFailure(parser, INVALID_ASSIGNMENT_NAME_FIELD, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AssignmentName.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_invalidModulePercentage_failure() {
        assertParseFailure(parser, INVALID_ASSIGNMENT_PERCENTAGE_FIELD, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AssignmentPercentage.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_invalidModuleResult_failure() {
        assertParseFailure(parser, INVALID_ASSIGNMENT_RESULT_FIELD, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AssignmentResult.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE);
        String nonEmptyPreamble = "add " + VALID_INPUT;
        assertParseFailure(parser, nonEmptyPreamble, expectedMessage);
    }
}
