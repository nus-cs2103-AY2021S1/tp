package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.command.GeneralCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.testutil.command.GeneralCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.testutil.command.RoomCommandTestUtil.INVALID_ROOM_NUMBER;
import static seedu.address.testutil.command.RoomCommandTestUtil.INVALID_ROOM_NUMBER_DESC;
import static seedu.address.testutil.command.RoomCommandTestUtil.ROOM_NUMBER_EIGHT_DESC;
import static seedu.address.testutil.command.RoomCommandTestUtil.ROOM_NUMBER_SEVEN_DESC;
import static seedu.address.testutil.command.RoomCommandTestUtil.VALID_ROOM_INDEX_ONE;
import static seedu.address.testutil.command.RoomCommandTestUtil.VALID_ROOM_NUMBER_SEVEN;
import static seedu.address.testutil.command.TaskCommandTestUtil.DATETIME_DUE_DESC_CLEAR_DATETIME;
import static seedu.address.testutil.command.TaskCommandTestUtil.DATETIME_DUE_DESC_ORDER_BEDSHEETS;
import static seedu.address.testutil.command.TaskCommandTestUtil.DATETIME_DUE_DESC_REMIND_PATIENT;
import static seedu.address.testutil.command.TaskCommandTestUtil.DESCRIPTION_DESC_ORDER_BEDSHEETS;
import static seedu.address.testutil.command.TaskCommandTestUtil.DESCRIPTION_DESC_REMIND_PATIENT;
import static seedu.address.testutil.command.TaskCommandTestUtil.INVALID_DATETIME_DUE_FORMAT_DESC;
import static seedu.address.testutil.command.TaskCommandTestUtil.INVALID_DATETIME_DUE_VALUE_DESC;
import static seedu.address.testutil.command.TaskCommandTestUtil.INVALID_DESCRIPTION_EMPTY_STRING_DESC;
import static seedu.address.testutil.command.TaskCommandTestUtil.INVALID_DESCRIPTION_EXCEED_LIMIT_DESC;
import static seedu.address.testutil.command.TaskCommandTestUtil.INVALID_TASK_NUMBER;
import static seedu.address.testutil.command.TaskCommandTestUtil.INVALID_TASK_NUMBER_DESC;
import static seedu.address.testutil.command.TaskCommandTestUtil.TASK_NUMBER_DESC_ONE;
import static seedu.address.testutil.command.TaskCommandTestUtil.TASK_NUMBER_DESC_TWO;
import static seedu.address.testutil.command.TaskCommandTestUtil.VALID_DATETIME_DUE_REMIND_PATIENT;
import static seedu.address.testutil.command.TaskCommandTestUtil.VALID_DESCRIPTION_REMIND_PATIENT;
import static seedu.address.testutil.command.TaskCommandTestUtil.VALID_TASK_INDEX_ONE;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.task.EditTaskCommand;
import seedu.address.logic.commands.task.EditTaskCommand.EditTaskDescriptor;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.room.RoomCliSyntax;
import seedu.address.model.task.DateTimeDue;
import seedu.address.model.task.Description;

public class EditTaskCommandParserTest {

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        EditTaskDescriptor descriptor = new EditTaskDescriptor();
        descriptor.setDescription(new Description(VALID_DESCRIPTION_REMIND_PATIENT));
        descriptor.setDateTimeDue(new DateTimeDue(VALID_DATETIME_DUE_REMIND_PATIENT));

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ROOM_NUMBER_SEVEN_DESC + TASK_NUMBER_DESC_ONE
                + DESCRIPTION_DESC_REMIND_PATIENT + DATETIME_DUE_DESC_REMIND_PATIENT,
                new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE, descriptor));

        // multiple room numbers - last room number accepted
        assertParseSuccess(parser, ROOM_NUMBER_EIGHT_DESC + ROOM_NUMBER_SEVEN_DESC + TASK_NUMBER_DESC_ONE
                + DESCRIPTION_DESC_REMIND_PATIENT + DATETIME_DUE_DESC_REMIND_PATIENT,
                new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE, descriptor));

        // multiple task indexes - last task number accepted
        assertParseSuccess(parser, ROOM_NUMBER_SEVEN_DESC + TASK_NUMBER_DESC_TWO + TASK_NUMBER_DESC_ONE
                + DESCRIPTION_DESC_REMIND_PATIENT + DATETIME_DUE_DESC_REMIND_PATIENT,
                new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE, descriptor));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, ROOM_NUMBER_SEVEN_DESC + TASK_NUMBER_DESC_ONE + DESCRIPTION_DESC_ORDER_BEDSHEETS
                + DESCRIPTION_DESC_REMIND_PATIENT + DATETIME_DUE_DESC_REMIND_PATIENT,
                new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE, descriptor));

        // multiple due dates - last due date accepted
        assertParseSuccess(parser, ROOM_NUMBER_SEVEN_DESC + TASK_NUMBER_DESC_ONE + DESCRIPTION_DESC_REMIND_PATIENT
                + DATETIME_DUE_DESC_ORDER_BEDSHEETS + DATETIME_DUE_DESC_REMIND_PATIENT,
                new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE, descriptor));

        // due date is set to INPUT_REMOVE_DUE_DATE
        descriptor.setDateTimeDue(new DateTimeDue(Optional.empty()));
        assertParseSuccess(parser, ROOM_NUMBER_SEVEN_DESC + TASK_NUMBER_DESC_ONE
                        + DESCRIPTION_DESC_REMIND_PATIENT + DATETIME_DUE_DESC_CLEAR_DATETIME,
                new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_TASK_INDEX_ONE, descriptor));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no description
        EditTaskDescriptor descriptor = new EditTaskDescriptor();
        descriptor.setDateTimeDue(new DateTimeDue(VALID_DATETIME_DUE_REMIND_PATIENT));
        assertParseSuccess(parser, ROOM_NUMBER_SEVEN_DESC + TASK_NUMBER_DESC_ONE + DATETIME_DUE_DESC_REMIND_PATIENT,
                new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_ROOM_INDEX_ONE, descriptor));

        // no due date
        descriptor = new EditTaskDescriptor();
        descriptor.setDescription(new Description(VALID_DESCRIPTION_REMIND_PATIENT));
        assertParseSuccess(parser, ROOM_NUMBER_SEVEN_DESC + TASK_NUMBER_DESC_ONE + DESCRIPTION_DESC_REMIND_PATIENT,
                new EditTaskCommand(VALID_ROOM_NUMBER_SEVEN, VALID_ROOM_INDEX_ONE, descriptor));
    }

    @Test
    public void parse_allOptionalFieldsMissing_failure() {
        // no description or due date
        assertParseFailure(parser, ROOM_NUMBER_SEVEN_DESC + TASK_NUMBER_DESC_ONE, Messages.MESSAGE_TASK_NOT_EDITED);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

        // missing room number prefix
        assertParseFailure(parser, TASK_NUMBER_DESC_ONE + DESCRIPTION_DESC_REMIND_PATIENT
                + DATETIME_DUE_DESC_REMIND_PATIENT, expectedMessage);

        // missing task index prefix
        assertParseFailure(parser, ROOM_NUMBER_SEVEN_DESC + DESCRIPTION_DESC_REMIND_PATIENT
                + DATETIME_DUE_DESC_REMIND_PATIENT, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid description - empty description
        assertParseFailure(parser, ROOM_NUMBER_SEVEN_DESC + TASK_NUMBER_DESC_ONE
                + INVALID_DESCRIPTION_EMPTY_STRING_DESC + DATETIME_DUE_DESC_REMIND_PATIENT,
                Description.MESSAGE_CONSTRAINTS);

        // invalid description - exceed character limit
        assertParseFailure(parser, ROOM_NUMBER_SEVEN_DESC + TASK_NUMBER_DESC_ONE
                + INVALID_DESCRIPTION_EXCEED_LIMIT_DESC + DATETIME_DUE_DESC_REMIND_PATIENT,
                Description.MESSAGE_CONSTRAINTS);

        // invalid due date value
        assertParseFailure(parser, ROOM_NUMBER_SEVEN_DESC + TASK_NUMBER_DESC_ONE + DESCRIPTION_DESC_REMIND_PATIENT
                + INVALID_DATETIME_DUE_VALUE_DESC, DateTimeDue.MESSAGE_CONSTRAINTS);

        // invalid due date format
        assertParseFailure(parser, ROOM_NUMBER_SEVEN_DESC + TASK_NUMBER_DESC_ONE + DESCRIPTION_DESC_REMIND_PATIENT
                + INVALID_DATETIME_DUE_FORMAT_DESC, DateTimeDue.MESSAGE_CONSTRAINTS);

        // invalid room number
        assertParseFailure(parser, INVALID_ROOM_NUMBER_DESC + TASK_NUMBER_DESC_ONE,
                String.format(ParserUtil.MESSAGE_INVALID_UNSIGNED_INT, RoomCliSyntax.PREFIX_ROOM_NUMBER,
                INVALID_ROOM_NUMBER));

        // invalid task number
        assertParseFailure(parser, ROOM_NUMBER_SEVEN_DESC + INVALID_TASK_NUMBER_DESC,
                String.format(ParserUtil.MESSAGE_INVALID_UNSIGNED_INT, TaskCliSyntax.PREFIX_TASK_NUMBER,
                INVALID_TASK_NUMBER));

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, ROOM_NUMBER_SEVEN_DESC + INVALID_TASK_NUMBER_DESC + DESCRIPTION_DESC_REMIND_PATIENT
                + INVALID_DATETIME_DUE_VALUE_DESC, String.format(ParserUtil.MESSAGE_INVALID_UNSIGNED_INT,
                TaskCliSyntax.PREFIX_TASK_NUMBER, INVALID_TASK_NUMBER));

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + ROOM_NUMBER_SEVEN_DESC + TASK_NUMBER_DESC_ONE
                + DESCRIPTION_DESC_REMIND_PATIENT + DATETIME_DUE_DESC_REMIND_PATIENT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE));
    }
}
