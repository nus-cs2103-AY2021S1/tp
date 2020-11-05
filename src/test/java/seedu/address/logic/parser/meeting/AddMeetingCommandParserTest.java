package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.MeetingCommandTestUtil.BIDDER_ID_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.END_TIME_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.INVALID_BIDDER_ID_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.INVALID_END_TIME_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.INVALID_MEETING_DATE_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.INVALID_PROPERTY_ID_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.INVALID_START_TIME_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.MEETING_DATE_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.MEETING_TYPE_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.MEETING_VENUE_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.PROPERTY_ID_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.START_TIME_DESC_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_BIDDER_ID_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_DATE_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_END_TIME_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_MEETING_TYPE_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_PROPERTY_ID_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_START_TIME_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_VENUE_A;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meetingcommands.AddMeetingCommand;
import seedu.address.logic.parser.meetingparser.AddMeetingCommandParser;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.StartTime;

public class AddMeetingCommandParserTest {
    private AddMeetingCommandParser parser = new AddMeetingCommandParser();


    //    @Test
    //    public void parse_allFieldsPresent_success() {
    //        Meeting expectedMeeting = new MeetingBuilder(ADMINMEETING01).buildAdmin();;
    //
    //        // whitespace only preamble
    //        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MEETING_TYPE_A + BIDDER_ID_DESC_A
    //                + PROPERTY_ID_DESC_A + MEETING_VENUE_DESC_A + MEETING_DATE_DESC_A
    //                + START_TIME_DESC_A + END_TIME_DESC_A, new AddMeetingCommand(expectedMeeting));
    //
    //        // multiple property ids - last propertyId accepted
    //        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MEETING_TYPE_A + BIDDER_ID_DESC_A
    //                + PROPERTY_ID_DESC_B + PROPERTY_ID_DESC_A + MEETING_VENUE_DESC_A + MEETING_DATE_DESC_A
    //                + START_TIME_DESC_A + END_TIME_DESC_A, new AddMeetingCommand(expectedMeeting));
    //
    //        // multiple bidder ids - last bidder id accepted
    //        assertParseSuccess(parser, PREAMBLE_WHITESPACE + MEETING_TYPE_A + BIDDER_ID_DESC_B + BIDDER_ID_DESC_A
    //                + PROPERTY_ID_DESC_A + MEETING_VENUE_DESC_A + MEETING_DATE_DESC_A
    //                + START_TIME_DESC_A + END_TIME_DESC_A, new AddMeetingCommand(expectedMeeting));
    //
    //}


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE);

        // missing propertyId prefix
        assertParseFailure(parser, VALID_PROPERTY_ID_A + BIDDER_ID_DESC_A,
                expectedMessage);

        // missing bidderId prefix
        assertParseFailure(parser, PROPERTY_ID_DESC_A + VALID_BIDDER_ID_A,
                expectedMessage);


        // all prefixes missing
        assertParseFailure(parser, VALID_PROPERTY_ID_A + VALID_BIDDER_ID_A
                        + VALID_MEETING_TYPE_A + VALID_DATE_A
                        + VALID_VENUE_A + VALID_START_TIME_A + VALID_END_TIME_A,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid propertyId
        assertParseFailure(parser, MEETING_TYPE_A + BIDDER_ID_DESC_A + INVALID_PROPERTY_ID_DESC_A
                        + MEETING_VENUE_DESC_A + MEETING_DATE_DESC_A
                        + START_TIME_DESC_A + END_TIME_DESC_A,
                PropertyId.MESSAGE_CONSTRAINTS);

        // invalid bidderId
        assertParseFailure(parser, MEETING_TYPE_A + INVALID_BIDDER_ID_DESC_A + PROPERTY_ID_DESC_A
                        + MEETING_VENUE_DESC_A + MEETING_DATE_DESC_A
                        + START_TIME_DESC_A + END_TIME_DESC_A,
                BidderId.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, MEETING_TYPE_A + BIDDER_ID_DESC_A + PROPERTY_ID_DESC_A
                + MEETING_VENUE_DESC_A + INVALID_MEETING_DATE_DESC_A
                + START_TIME_DESC_A + END_TIME_DESC_A, MeetingDate.MESSAGE_CONSTRAINTS_PAST_DATE);


        // invalid start time
        assertParseFailure(parser, MEETING_TYPE_A + BIDDER_ID_DESC_A + PROPERTY_ID_DESC_A
                + MEETING_VENUE_DESC_A + MEETING_DATE_DESC_A
                + INVALID_START_TIME_DESC_A + END_TIME_DESC_A, StartTime.MESSAGE_CONSTRAINTS);

        // invalid end time
        assertParseFailure(parser, MEETING_TYPE_A + BIDDER_ID_DESC_A + PROPERTY_ID_DESC_A
                + MEETING_VENUE_DESC_A + MEETING_DATE_DESC_A
                + START_TIME_DESC_A + INVALID_END_TIME_DESC_A, EndTime.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, MEETING_TYPE_A + BIDDER_ID_DESC_A + INVALID_PROPERTY_ID_DESC_A
                        + MEETING_VENUE_DESC_A + MEETING_DATE_DESC_A
                        + START_TIME_DESC_A + INVALID_END_TIME_DESC_A,
                PropertyId.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + MEETING_TYPE_A + BIDDER_ID_DESC_A
                        + PROPERTY_ID_DESC_A + MEETING_VENUE_DESC_A + MEETING_DATE_DESC_A
                        + START_TIME_DESC_A + END_TIME_DESC_A,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));


    }
}
