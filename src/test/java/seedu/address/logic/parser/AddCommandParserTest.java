package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_HW;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_LAB;
import static seedu.address.logic.commands.CommandTestUtil.DESC_HW;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REMIND_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_HW;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_LAB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_HW;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_LAB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.REMIND_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_LAB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAssignments.HW;
import static seedu.address.testutil.TypicalAssignments.LAB;
import static seedu.address.testutil.TypicalAssignments.LAB_PRIORITY;
import static seedu.address.testutil.TypicalAssignments.LAB_PRIORITY_REMIND;
import static seedu.address.testutil.TypicalAssignments.LAB_REMIND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Priority;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.ModuleCode;
import seedu.address.model.task.Name;
import seedu.address.testutil.AssignmentBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_compulsoryFieldsPresent_success() {
        Assignment expectedAssignment = new AssignmentBuilder(LAB).build();
        Assignment expectedAssignmentWithPriority = new AssignmentBuilder(LAB_PRIORITY).build();
        Assignment expectedAssignmentWithRemind = new AssignmentBuilder(LAB_REMIND).build();
        Assignment expectedAssignmentWithPriorityAndRemind = new AssignmentBuilder(LAB_PRIORITY_REMIND).build();


        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_LAB + DEADLINE_DESC_LAB
                + MODULE_CODE_DESC_LAB, new AddCommand(expectedAssignment));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_HW + NAME_DESC_LAB + DEADLINE_DESC_LAB
                + MODULE_CODE_DESC_LAB, new AddCommand(expectedAssignment));

        // multiple names with remind - last name accepted
        assertParseSuccess(parser, NAME_DESC_HW + NAME_DESC_LAB + DEADLINE_DESC_LAB
                + MODULE_CODE_DESC_LAB + REMIND_DESC, new AddCommand(expectedAssignmentWithRemind));

        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser, NAME_DESC_LAB + DEADLINE_DESC_HW + DEADLINE_DESC_LAB + MODULE_CODE_DESC_LAB,
                new AddCommand(expectedAssignment));

        // multiple deadlines with remind - last deadline accepted
        assertParseSuccess(parser, NAME_DESC_LAB + DEADLINE_DESC_HW + DEADLINE_DESC_LAB + MODULE_CODE_DESC_LAB
                + REMIND_DESC, new AddCommand(expectedAssignmentWithRemind));

        // multiple module codes - last module code accepted
        assertParseSuccess(parser, NAME_DESC_LAB + DEADLINE_DESC_LAB + MODULE_CODE_DESC_HW
                + MODULE_CODE_DESC_LAB, new AddCommand(expectedAssignment));

        // multiple module codes with remind - last module code accepted
        assertParseSuccess(parser, NAME_DESC_LAB + DEADLINE_DESC_LAB + MODULE_CODE_DESC_HW
                + MODULE_CODE_DESC_LAB + REMIND_DESC, new AddCommand(expectedAssignmentWithRemind));

        // TODO JERRY: Find out why an Add Command without priority passes the test case as well eg. expectedAssignment
        // all compulsory fields with priority
        assertParseSuccess(parser, NAME_DESC_LAB + DEADLINE_DESC_LAB + MODULE_CODE_DESC_LAB + PRIORITY_DESC,
                new AddCommand(expectedAssignmentWithPriority));

        // all compulsory fields with remind
        assertParseSuccess(parser, NAME_DESC_LAB + DEADLINE_DESC_LAB + MODULE_CODE_DESC_LAB + REMIND_DESC,
                new AddCommand(expectedAssignmentWithRemind));

        // all compulsory fields with remind and priority
        assertParseSuccess(parser, NAME_DESC_LAB + DEADLINE_DESC_LAB + MODULE_CODE_DESC_LAB + PRIORITY_DESC
                + REMIND_DESC, new AddCommand(expectedAssignmentWithPriorityAndRemind));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no remind and priority
        Assignment expectedAssignment = new AssignmentBuilder(HW).build();
        assertParseSuccess(parser, NAME_DESC_HW + DEADLINE_DESC_HW + MODULE_CODE_DESC_HW,
                new AddCommand(expectedAssignment));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_LAB + DEADLINE_DESC_LAB + MODULE_CODE_DESC_LAB,
                expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, NAME_DESC_LAB + VALID_DEADLINE_LAB + MODULE_CODE_DESC_LAB,
                expectedMessage);

        // missing module code prefix
        assertParseFailure(parser, NAME_DESC_LAB + DEADLINE_DESC_LAB + DESC_HW,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_LAB + VALID_DEADLINE_LAB + DESC_HW,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + DEADLINE_DESC_LAB + MODULE_CODE_DESC_LAB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, NAME_DESC_LAB + INVALID_DEADLINE_DESC + MODULE_CODE_DESC_LAB,
                Deadline.MESSAGE_CONSTRAINTS);

        // invalid module code
        assertParseFailure(parser, NAME_DESC_LAB + DEADLINE_DESC_LAB + INVALID_MODULE_CODE_DESC,
                ModuleCode.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + DEADLINE_DESC_LAB + INVALID_MODULE_CODE_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // TODO JERRY: Double check with team on error message for invalid remind case
        // invalid remind
        assertParseFailure(parser, NAME_DESC_HW + NAME_DESC_LAB + DEADLINE_DESC_LAB
                + MODULE_CODE_DESC_LAB + INVALID_REMIND_DESC, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_USAGE));

        // invalid priority
        assertParseFailure(parser, NAME_DESC_HW + NAME_DESC_LAB + DEADLINE_DESC_LAB
                + MODULE_CODE_DESC_LAB + INVALID_PRIORITY_DESC, Priority.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_LAB + DEADLINE_DESC_LAB
                + MODULE_CODE_DESC_LAB, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
