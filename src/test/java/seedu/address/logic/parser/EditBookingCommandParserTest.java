package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DATE;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_ROOM_ID;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditBookingCommand;
import seedu.address.testutil.EditBookingDescriptorBuilder;


public class EditBookingCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBookingCommand.MESSAGE_USAGE);

    private EditBookingCommandParser parser = new EditBookingCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, "2020-02-02", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, " bid/1", EditBookingCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative booing id
        assertParseFailure(parser, "bid/-5" + ROOM_ID_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "bid/1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "bid/1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, " bid/1" + INVALID_ROOM_ID_DESC2, MESSAGE_INVALID_ROOM_ID); // invalid room id
        assertParseFailure(parser, " bid/1" + INVALID_START_DATE_DESC, MESSAGE_INVALID_DATE); // invalid start date
        assertParseFailure(parser, " bid/1" + INVALID_END_DATE_DESC, MESSAGE_INVALID_DATE); // invalid end date

        // invalid room id followed by valid start date
        assertParseFailure(parser, " bid/1" + INVALID_ROOM_ID_DESC2 + START_DATE_DESC_AMY, MESSAGE_INVALID_ROOM_ID);

        // valid room id followed by invalid room id. The test case for invalid room id followed by valid room id
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, " bid/1" + ROOM_ID_DESC_AMY + INVALID_ROOM_ID_DESC2, MESSAGE_INVALID_ROOM_ID);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, " bid/1" + INVALID_ROOM_ID_DESC2 + INVALID_START_DATE_DESC
                        + END_DATE_DESC_AMY, MESSAGE_INVALID_ROOM_ID);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = BOOKING_ID_DESC_AMY + ROOM_ID_DESC_AMY + END_DATE_DESC_AMY + START_DATE_DESC_AMY;

        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder()
                .withRoomId(VALID_ROOM_ID_AMY).withStartDate(VALID_START_DATE_AMY)
                .withEndDate(VALID_END_DATE_AMY).build();
        EditBookingCommand expectedCommand = new EditBookingCommand(VALID_BOOKING_ID_AMY, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = BOOKING_ID_DESC_AMY + ROOM_ID_DESC_AMY + END_DATE_DESC_AMY;

        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder()
                .withRoomId(VALID_ROOM_ID_AMY).withEndDate(VALID_END_DATE_AMY).build();
        EditBookingCommand expectedCommand = new EditBookingCommand(VALID_BOOKING_ID_AMY, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // room id
        String userInput = BOOKING_ID_DESC_AMY + ROOM_ID_DESC_AMY;
        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder()
                .withRoomId(VALID_ROOM_ID_AMY).build();
        EditBookingCommand expectedCommand = new EditBookingCommand(VALID_BOOKING_ID_AMY, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start date
        userInput = BOOKING_ID_DESC_AMY + START_DATE_DESC_AMY;
        descriptor = new EditBookingDescriptorBuilder().withStartDate(VALID_START_DATE_AMY).build();
        expectedCommand = new EditBookingCommand(VALID_BOOKING_ID_AMY, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end date
        userInput = BOOKING_ID_DESC_AMY + END_DATE_DESC_AMY;
        descriptor = new EditBookingDescriptorBuilder().withEndDate(VALID_END_DATE_AMY).build();
        expectedCommand = new EditBookingCommand(VALID_BOOKING_ID_AMY, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = BOOKING_ID_DESC_BOB + ROOM_ID_DESC_BOB + START_DATE_DESC_BOB + END_DATE_DESC_BOB
                + ROOM_ID_DESC_AMY + START_DATE_DESC_AMY + END_DATE_DESC_AMY;

        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder()
                .withRoomId(VALID_ROOM_ID_AMY).withStartDate(VALID_START_DATE_AMY)
                .withEndDate(VALID_END_DATE_AMY).build();
        EditBookingCommand expectedCommand = new EditBookingCommand(VALID_BOOKING_ID_BOB, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = BOOKING_ID_DESC_BOB + INVALID_ROOM_ID_DESC2 + ROOM_ID_DESC_BOB;
        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingDescriptorBuilder()
                .withRoomId(VALID_ROOM_ID_BOB).build();
        EditBookingCommand expectedCommand = new EditBookingCommand(VALID_BOOKING_ID_BOB, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = BOOKING_ID_DESC_BOB + START_DATE_DESC_BOB + INVALID_ROOM_ID_DESC2 + END_DATE_DESC_BOB
                + ROOM_ID_DESC_BOB;
        descriptor = new EditBookingDescriptorBuilder().withRoomId(VALID_ROOM_ID_BOB)
                .withStartDate(VALID_START_DATE_BOB)
                .withEndDate(VALID_END_DATE_BOB).build();
        expectedCommand = new EditBookingCommand(VALID_BOOKING_ID_BOB, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
