package seedu.address.logic.parser.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.patient.Name.MESSAGE_CONSTRAINTS;
import static seedu.address.testutil.command.PatientCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.testutil.command.PatientCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.testutil.command.PatientCommandTestUtil.NAME_DESC_JAMES;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_NAME_JAMES;
import static seedu.address.testutil.command.RoomCommandTestUtil.INVALID_NON_NUMBER_ROOM_NUMBER;
import static seedu.address.testutil.command.RoomCommandTestUtil.INVALID_ROOM_NUMBER;
import static seedu.address.testutil.command.RoomCommandTestUtil.REMOVE_PATIENT_DESC;
import static seedu.address.testutil.command.RoomCommandTestUtil.VALID_ROOM_NUMBER_ONE;
import static seedu.address.testutil.command.RoomCommandTestUtil.VALID_ROOM_NUMBER_TWO;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.room.AllocateRoomCommand;
import seedu.address.model.patient.Name;
import seedu.address.testutil.AllocateRoomDescriptorBuilder;

public class AllocateRoomCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AllocateRoomCommand.MESSAGE_USAGE);

    private AllocateRoomCommandParser parser = new AllocateRoomCommandParser();

    @Test
    public void parse_invalidInput_failure() {
        //EP: No field specified
        assertParseFailure(parser, VALID_ROOM_NUMBER_ONE, AllocateRoomCommand.MESSAGE_USAGE);

        //EP: Negative integer input
        assertParseFailure(parser, INVALID_ROOM_NUMBER, MESSAGE_INVALID_FORMAT);

        //EP: Non-integer input
        assertParseFailure(parser, INVALID_NON_NUMBER_ROOM_NUMBER, MESSAGE_INVALID_FORMAT);

        //EP: Non-number input
        assertParseFailure(parser, INVALID_NON_NUMBER_ROOM_NUMBER, MESSAGE_INVALID_FORMAT);

        //EP: No index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPatientName_failure() {
        assertParseFailure(parser, VALID_ROOM_NUMBER_ONE + INVALID_NAME_DESC,
                MESSAGE_CONSTRAINTS); // invalid patient name
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        //EP: Valid patient name and room number
        String userInput = VALID_ROOM_NUMBER_ONE + NAME_DESC_JAMES;

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new AllocateRoomDescriptorBuilder()
                .withRoomNumber(Integer.valueOf(VALID_ROOM_NUMBER_TWO))
                .withPatient(new Name(VALID_NAME_JAMES))
                .build();
        AllocateRoomCommand expectedCommand = new AllocateRoomCommand(Integer.valueOf(VALID_ROOM_NUMBER_ONE),
            descriptor, false);
        assertParseSuccess(parser, userInput, expectedCommand);

        //EP: valid room number and patient name as "-"
        userInput = VALID_ROOM_NUMBER_ONE + REMOVE_PATIENT_DESC;

        descriptor = new AllocateRoomDescriptorBuilder()
            .withRoomNumber(Integer.valueOf(VALID_ROOM_NUMBER_TWO))
            .withOccupancy(false)
            .build();
        expectedCommand = new AllocateRoomCommand(Integer.valueOf(VALID_ROOM_NUMBER_ONE),
            descriptor, true);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedValue_acceptsLast() {
        //EP: valid name followed by another valid name
        String userInput = VALID_ROOM_NUMBER_ONE + NAME_DESC_JAMES
            + NAME_DESC_AMY;

        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new AllocateRoomDescriptorBuilder()
            .withRoomNumber(Integer.valueOf(VALID_ROOM_NUMBER_ONE))
            .withPatient(new Name(VALID_NAME_AMY))
            .build();
        AllocateRoomCommand expectedCommand = new AllocateRoomCommand(Integer.valueOf(VALID_ROOM_NUMBER_ONE),
            descriptor, false);
        assertParseSuccess(parser, userInput, expectedCommand);

        //EP: valid name followed by patient name as "-"
        userInput = VALID_ROOM_NUMBER_ONE + NAME_DESC_JAMES
            + REMOVE_PATIENT_DESC;

        descriptor = new AllocateRoomDescriptorBuilder()
            .withRoomNumber(Integer.valueOf(VALID_ROOM_NUMBER_ONE))
            .withOccupancy(false)
            .build();
        expectedCommand = new AllocateRoomCommand(Integer.valueOf(VALID_ROOM_NUMBER_ONE),
            descriptor, true);
        assertParseSuccess(parser, userInput, expectedCommand);

        //EP: patient name as "-" followed by valid name
        userInput = VALID_ROOM_NUMBER_ONE + REMOVE_PATIENT_DESC
            + NAME_DESC_JAMES;

        descriptor = new AllocateRoomDescriptorBuilder()
            .withRoomNumber(Integer.valueOf(VALID_ROOM_NUMBER_ONE))
            .withPatient(new Name(VALID_NAME_JAMES))
            .build();
        expectedCommand = new AllocateRoomCommand(Integer.valueOf(VALID_ROOM_NUMBER_ONE),
            descriptor, false);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        //EP: invalid name followed by valid patient name
        String userInput = VALID_ROOM_NUMBER_TWO + INVALID_NAME_DESC + NAME_DESC_JAMES;
        AllocateRoomCommand.AllocateRoomDescriptor descriptor = new AllocateRoomDescriptorBuilder()
            .withRoomNumber(Integer.valueOf(VALID_ROOM_NUMBER_ONE))
            .withPatient(new Name(VALID_NAME_JAMES)).build();
        AllocateRoomCommand expectedCommand = new AllocateRoomCommand(Integer.valueOf(VALID_ROOM_NUMBER_TWO),
            descriptor, false);
        assertParseSuccess(parser, userInput, expectedCommand);

        //EP: invalid name followed by patient name as "-"
        userInput = VALID_ROOM_NUMBER_TWO + INVALID_NAME_DESC + REMOVE_PATIENT_DESC;
        descriptor = new AllocateRoomDescriptorBuilder()
            .withRoomNumber(Integer.valueOf(VALID_ROOM_NUMBER_ONE))
            .withOccupancy(false)
            .build();
        expectedCommand = new AllocateRoomCommand(Integer.valueOf(VALID_ROOM_NUMBER_TWO),
            descriptor, true);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void equals() {
        AllocateRoomCommand.AllocateRoomDescriptor descriptorOne = new AllocateRoomDescriptorBuilder()
            .withRoomNumber(Integer.valueOf(VALID_ROOM_NUMBER_ONE))
            .withPatient(new Name(VALID_NAME_JAMES)).build();

        AllocateRoomCommand.AllocateRoomDescriptor descriptorTwo = new AllocateRoomDescriptorBuilder()
            .withRoomNumber(Integer.valueOf(VALID_ROOM_NUMBER_TWO))
            .withPatient(new Name(VALID_NAME_JAMES)).build();

        AllocateRoomCommand allocateFirstRoomCommand = new AllocateRoomCommand(
            Integer.valueOf(VALID_ROOM_NUMBER_ONE), descriptorOne, false);
        AllocateRoomCommand allocateSecondRoomCommand = new AllocateRoomCommand(
            Integer.valueOf(VALID_ROOM_NUMBER_TWO), descriptorTwo, false);

        // same object -> returns true
        assertTrue(allocateFirstRoomCommand.equals(allocateFirstRoomCommand));

        // different types -> returns false
        assertFalse(allocateFirstRoomCommand.equals(1));

        // null -> returns false
        assertFalse(allocateFirstRoomCommand.equals(null));

        // different patient -> returns false
        assertFalse(allocateFirstRoomCommand.equals(allocateSecondRoomCommand));
    }
}
