package seedu.address.logic.parser.gradetrackerparsers;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.gradetrackercommands.AddAssignmentCommand;
import seedu.address.logic.commands.schedulercommands.AddEventCommand;
import seedu.address.logic.parser.schedulerparsers.AddEventParser;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.Assignment;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.testutil.event.EventBuilder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATE_2;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class AddAssignmentParserTest {

    private static final String validInput = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String multipleModuleNames = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_NAME + VALID_MODULENAME_CS2030
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String multipleAssignmentNames = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_2
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String multipleAssignmentPercentages = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_2
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String multipleAssignmentResults = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_2;

    private static final String invalidModuleName = " " + PREFIX_NAME + INVALID_MODULENAME_CSA200
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String invalidAssignmentName = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + INVALID_ASSIGNMENT_NAME
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String invalidAssignmentPercentage = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + INVALID_ASSIGNMENT_PERCENTAGE
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String invalidAssignmentResult = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_RESULT + INVALID_ASSIGNMENT_RESULT;

    private static final String missingModuleName = " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String missingAssignmentName = " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String missingAssignmentPercentage =  " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_RESULT + VALID_ASSIGNMENT_RESULT_1;

    private static final String missingAssignmentResult =  " " + PREFIX_NAME + VALID_MODULENAME_CS2103T
            + " " + PREFIX_ASSIGNMENT_NAME + VALID_ASSIGNMENT_NAME_1
            + " " + PREFIX_ASSIGNMENT_PERCENTAGE + VALID_ASSIGNMENT_PERCENTAGE_1;

    private AddAssignmentParser parser = new AddAssignmentParser();

    @Test
    public void parse_allFieldsPresent_success() {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2103T);
        Assignment expectedAssignment = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_1)
                .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();
        assertParseSuccess(parser, validInput, new AddAssignmentCommand(expectedModuleName, expectedAssignment));
    }

    @Test
    public void parse_preambleWhiteSpace_success() {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2103T);
        Assignment expectedAssignment = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_1)
                .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + validInput,
                new AddAssignmentCommand(expectedModuleName, expectedAssignment));
    }

    @Test
    public void parse_multipleModuleNames_success() {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2030);
        Assignment expectedAssignment = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_1)
                .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();

        //takes in second module name
        assertParseSuccess(parser, multipleModuleNames, new AddAssignmentCommand(expectedModuleName, expectedAssignment));
    }

    @Test
    public void parse_multipleAssignmentNames_success() {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2103T);
        Assignment expectedAssignment = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_2)
                .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();

        //takes in second assignment name
        assertParseSuccess(parser, multipleAssignmentNames, new AddAssignmentCommand(expectedModuleName, expectedAssignment));
    }

    @Test
    public void parse_multipleAssignmentPercentages_success() {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2103T);
        Assignment expectedAssignment = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_1)
                .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_2)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_1).build();

        //takes in second assignment percentage
        assertParseSuccess(parser, multipleAssignmentPercentages, new AddAssignmentCommand(expectedModuleName, expectedAssignment));
    }

    @Test
    public void parse_multipleAssignmentResults_success() {
        ModuleName expectedModuleName = new ModuleName(VALID_MODULENAME_CS2103T);
        Assignment expectedAssignment = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_1)
                .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1)
                .withAssignmentResult(VALID_ASSIGNMENT_RESULT_2).build();

        //takes in second assignment percentage
        assertParseSuccess(parser, multipleAssignmentResults, new AddAssignmentCommand(expectedModuleName, expectedAssignment));
    }

    @Test
    public void parse_compulsoryModuleNameFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, missingAssignmentName, expectedMessage);
    }
/*
    @Test
    public void parse_compulsoryAssignmentNameMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);
        assertParseFailure(parser, missingAssignmentPercentage, expectedMessage);
    }

    @Test
    public void parse_invalidName_failure() {
        assertParseFailure(parser, InvalidNameInput, EventName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDate_failure() {
        assertParseFailure(parser, InvalidDateInput, EventTime.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);
        String nonEmptyPreamble = "add " + validInput;
        assertParseFailure(parser, nonEmptyPreamble, expectedMessage);
    }*/
}
