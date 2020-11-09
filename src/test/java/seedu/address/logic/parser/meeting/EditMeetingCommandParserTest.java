package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.MeetingCommandTestUtil.BIDDER_ID_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.BIDDER_ID_DESC_B;
import static seedu.address.logic.commands.MeetingCommandTestUtil.INVALID_BIDDER_ID_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.INVALID_BIDDER_ID_DESC_B;
import static seedu.address.logic.commands.MeetingCommandTestUtil.INVALID_MEETING_DATE_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.INVALID_MEETING_DATE_DESC_B;
import static seedu.address.logic.commands.MeetingCommandTestUtil.INVALID_PROPERTY_ID_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.INVALID_PROPERTY_ID_DESC_B;
import static seedu.address.logic.commands.MeetingCommandTestUtil.MEETING_DATE_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.MEETING_DATE_DESC_B;
import static seedu.address.logic.commands.MeetingCommandTestUtil.PROPERTY_ID_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.PROPERTY_ID_DESC_B;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_BIDDER_ID_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_BIDDER_ID_B;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_DATE_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_DATE_B;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_PROPERTY_ID_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_PROPERTY_ID_B;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meetingcommands.EditMeetingCommand;
import seedu.address.logic.parser.meetingparser.EditMeetingCommandParser;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.testutil.meeting.EditMeetingDescriptorBuilder;

public class EditMeetingCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMeetingCommand.MESSAGE_USAGE);

    private EditMeetingCommandParser parser = new EditMeetingCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_PROPERTY_ID_A, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditMeetingCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_PROPERTY_ID_A, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_PROPERTY_ID_A, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_PROPERTY_ID_DESC_B,
                PropertyId.MESSAGE_CONSTRAINTS); // invalid propertyId
        assertParseFailure(parser, "1" + INVALID_BIDDER_ID_DESC_B,
                BidderId.MESSAGE_CONSTRAINTS); // invalid bidderId
        assertParseFailure(parser, "1" + INVALID_MEETING_DATE_DESC_B,
                MeetingDate.MESSAGE_CONSTRAINTS); // invalid Date

        // invalid propertyId followed by valid bidderId
        assertParseFailure(parser, "1" + INVALID_PROPERTY_ID_DESC_B + BIDDER_ID_DESC_B,
                PropertyId.MESSAGE_CONSTRAINTS);

        // valid bidderId followed by invalid bidderId. The test case for invalid bidderId followed by valid bidderId
        assertParseFailure(parser, "1" + BIDDER_ID_DESC_B + INVALID_BIDDER_ID_DESC_B, BidderId.MESSAGE_CONSTRAINTS);

        // valid date followed by invalid date. The date case for invalid date then valid date
        assertParseFailure(parser, "1" + MEETING_DATE_DESC_A
                + INVALID_MEETING_DATE_DESC_A, MeetingDate.MESSAGE_CONSTRAINTS_PAST_DATE);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_PROPERTY_ID_DESC_B
                        + INVALID_BIDDER_ID_DESC_B + INVALID_MEETING_DATE_DESC_A,
                PropertyId.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_MEETING;
        String userInput = targetIndex.getOneBased() + PROPERTY_ID_DESC_B + BIDDER_ID_DESC_A
                + MEETING_DATE_DESC_A;

        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
                .withPropertyId(VALID_PROPERTY_ID_B)
                .withBidderId(VALID_BIDDER_ID_A).withDate(VALID_DATE_A).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_MEETING;
        String userInput = targetIndex.getOneBased() + BIDDER_ID_DESC_B;

        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
                .withBidderId(VALID_BIDDER_ID_B)
                .build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // propertyId
        Index targetIndex = INDEX_THIRD_MEETING;
        String userInput = targetIndex.getOneBased() + PROPERTY_ID_DESC_A;
        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
                .withPropertyId(VALID_PROPERTY_ID_A).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // bidderId
        userInput = targetIndex.getOneBased() + BIDDER_ID_DESC_A;
        descriptor = new EditMeetingDescriptorBuilder().withBidderId(VALID_BIDDER_ID_A).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Date
        userInput = targetIndex.getOneBased() + MEETING_DATE_DESC_B;
        descriptor = new EditMeetingDescriptorBuilder().withDate(VALID_DATE_B).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_MEETING;
        String userInput = targetIndex.getOneBased() + PROPERTY_ID_DESC_A + BIDDER_ID_DESC_A
                + PROPERTY_ID_DESC_B + BIDDER_ID_DESC_B;

        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
                .withPropertyId(VALID_PROPERTY_ID_B).withBidderId(VALID_BIDDER_ID_B).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_MEETING;
        String userInput = targetIndex.getOneBased() + INVALID_BIDDER_ID_DESC_A + BIDDER_ID_DESC_B;
        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
                .withBidderId(VALID_BIDDER_ID_B).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_PROPERTY_ID_DESC_A + PROPERTY_ID_DESC_A;
        descriptor = new EditMeetingDescriptorBuilder().withPropertyId(VALID_PROPERTY_ID_A).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
