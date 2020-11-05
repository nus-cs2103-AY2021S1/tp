package seedu.address.logic.parser.meeting;

import static seedu.address.logic.commands.MeetingCommandTestUtil.BIDDER_ID_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.BIDDER_ID_DESC_B;
import static seedu.address.logic.commands.MeetingCommandTestUtil.END_TIME_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.INVALID_END_TIME_DESC_B;
import static seedu.address.logic.commands.MeetingCommandTestUtil.INVALID_MEETING_DATE_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.INVALID_MEETING_DATE_DESC_B;
import static seedu.address.logic.commands.MeetingCommandTestUtil.INVALID_START_TIME_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.INVALID_START_TIME_DESC_B;
import static seedu.address.logic.commands.MeetingCommandTestUtil.MEETING_DATE_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.MEETING_VENUE_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.MEETING_VENUE_DESC_B;
import static seedu.address.logic.commands.MeetingCommandTestUtil.PROPERTY_ID_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.PROPERTY_ID_DESC_B;
import static seedu.address.logic.commands.MeetingCommandTestUtil.START_TIME_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_BIDDER_ID_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_BIDDER_ID_B;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_DATE_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_END_TIME_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_PROPERTY_ID_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_PROPERTY_ID_B;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_START_TIME_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_VENUE_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_VENUE_B;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meetingcommands.FindMeetingCommand;
import seedu.address.logic.commands.meetingcommands.FindMeetingCommand.FindMeetingDescriptor;
import seedu.address.logic.parser.meetingparser.FindMeetingCommandParser;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.StartTime;
import seedu.address.testutil.meeting.FindMeetingDescriptorBuilder;

public class FindMeetingCommandParserTest {
    private FindMeetingCommandParser parser = new FindMeetingCommandParser();

    @Test
    public void parse_missingParts_failure() {

        // no field specified
        assertParseFailure(parser, "", FindMeetingCommand.MESSAGE_USAGE);

    }

    @Test
    public void parse_invalidPreamble_failure() {

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", FindMeetingCommand.MESSAGE_USAGE);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", FindMeetingCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid Date
        assertParseFailure(parser, INVALID_MEETING_DATE_DESC_B, MeetingDate.MESSAGE_CONSTRAINTS);

        // invalid Start Time
        assertParseFailure(parser, INVALID_START_TIME_DESC_B,
                StartTime.MESSAGE_CONSTRAINTS);

        // invalid End Time
        assertParseFailure(parser, INVALID_END_TIME_DESC_B,
                EndTime.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_MEETING_DATE_DESC_A + INVALID_START_TIME_DESC_A,
                MeetingDate.MESSAGE_CONSTRAINTS_PAST_DATE);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = PROPERTY_ID_DESC_A
                + BIDDER_ID_DESC_A
                + MEETING_VENUE_DESC_A
                + MEETING_DATE_DESC_A
                + START_TIME_DESC_A
                + END_TIME_DESC_A;

        FindMeetingDescriptor descriptor = new FindMeetingDescriptorBuilder()
                .withMeetingPropertyIdPredicate(VALID_PROPERTY_ID_A)
                .withMeetingBidderIdPredicate(VALID_BIDDER_ID_A)
                .withMeetingVenuePredicate(VALID_VENUE_A)
                .withMeetingDatePredicate(VALID_DATE_A)
                .withMeetingStartTimePredicate(VALID_START_TIME_A)
                .withMeetingEndTimePredicate(VALID_END_TIME_A)
                .build();
        FindMeetingCommand expectedCommand = new FindMeetingCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = MEETING_DATE_DESC_A + MEETING_VENUE_DESC_B;
        FindMeetingDescriptor descriptor = new FindMeetingDescriptorBuilder()
                .withMeetingDatePredicate(VALID_DATE_A)
                .withMeetingVenuePredicate(VALID_VENUE_B)
                .build();
        FindMeetingCommand expectedCommand = new FindMeetingCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {

        // property Id
        String userInput = PROPERTY_ID_DESC_A;
        FindMeetingDescriptor descriptor = new FindMeetingDescriptorBuilder()
                .withMeetingPropertyIdPredicate(VALID_PROPERTY_ID_A)
                .build();
        FindMeetingCommand expectedCommand = new FindMeetingCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // bidder Id
        userInput = BIDDER_ID_DESC_A;
        descriptor = new FindMeetingDescriptorBuilder()
                .withMeetingBidderIdPredicate(VALID_BIDDER_ID_A)
                .build();
        expectedCommand = new FindMeetingCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Venue
        userInput = MEETING_VENUE_DESC_A;
        descriptor = new FindMeetingDescriptorBuilder()
                .withMeetingVenuePredicate(VALID_VENUE_A)
                .build();
        expectedCommand = new FindMeetingCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Date
        userInput = MEETING_DATE_DESC_A;
        descriptor = new FindMeetingDescriptorBuilder()
                .withMeetingDatePredicate(VALID_DATE_A)
                .build();
        expectedCommand = new FindMeetingCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Start Time
        userInput = START_TIME_DESC_A;
        descriptor = new FindMeetingDescriptorBuilder()
                .withMeetingStartTimePredicate(VALID_START_TIME_A)
                .build();
        expectedCommand = new FindMeetingCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // End Time
        userInput = END_TIME_DESC_A;
        descriptor = new FindMeetingDescriptorBuilder()
                .withMeetingEndTimePredicate(VALID_END_TIME_A).build();
        expectedCommand = new FindMeetingCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = PROPERTY_ID_DESC_A
                + BIDDER_ID_DESC_A + PROPERTY_ID_DESC_A
                + BIDDER_ID_DESC_A + PROPERTY_ID_DESC_B + BIDDER_ID_DESC_B;

        FindMeetingDescriptor descriptor = new FindMeetingDescriptorBuilder()
                .withMeetingPropertyIdPredicate(VALID_PROPERTY_ID_B)
                .withMeetingBidderIdPredicate(VALID_BIDDER_ID_B).build();
        FindMeetingCommand expectedCommand = new FindMeetingCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_fail() {

        String userInput = INVALID_START_TIME_DESC_B + VALID_START_TIME_A;
        assertParseFailure(parser, userInput, StartTime.MESSAGE_CONSTRAINTS);

    }

}
