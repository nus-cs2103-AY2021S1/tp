package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_CM1111_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_CM1112_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEETING_NAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_NAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PARTICIPANT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_NAME_DESC_CM1111_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_NAME_DESC_CM1112_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_CM1111_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_CM1112_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.PARTICIPANT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PARTICIPANT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_CM1111_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_CM1112_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CM1111_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_NAME_CM1111_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_CM1111_MEETING;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalMeetings.CM1111_MEETING;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.model.meeting.Date;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Time;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Name;
import seedu.address.testutil.MeetingBuilder;

public class AddMeetingCommandParserTest {
    private AddMeetingCommandParser parser = new AddMeetingCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Meeting expectedMeeting = new MeetingBuilder(CM1111_MEETING).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                + MODULE_DESC_CM1111_MEETING
                + MEETING_NAME_DESC_CM1111_MEETING
                + DATE_DESC_CM1111_MEETING
                + TIME_DESC_CM1111_MEETING
                + PARTICIPANT_DESC_AMY, new AddMeetingCommand(expectedMeeting));

        // multiple modules - last module accepted
        assertParseSuccess(parser, MODULE_DESC_CM1112_MEETING
                + MODULE_DESC_CM1111_MEETING
                + MEETING_NAME_DESC_CM1111_MEETING
                + DATE_DESC_CM1111_MEETING
                + TIME_DESC_CM1111_MEETING
                + PARTICIPANT_DESC_AMY, new AddMeetingCommand(expectedMeeting));

        // multiple meeting names - last name accepted
        assertParseSuccess(parser, MODULE_DESC_CM1111_MEETING
                + MEETING_NAME_DESC_CM1112_MEETING
                + MEETING_NAME_DESC_CM1111_MEETING
                + DATE_DESC_CM1111_MEETING
                + TIME_DESC_CM1111_MEETING
                + PARTICIPANT_DESC_AMY, new AddMeetingCommand(expectedMeeting));

        // multiple dates - last date accepted
        assertParseSuccess(parser, MODULE_DESC_CM1111_MEETING
                + MEETING_NAME_DESC_CM1111_MEETING
                + DATE_DESC_CM1112_MEETING
                + DATE_DESC_CM1111_MEETING
                + TIME_DESC_CM1111_MEETING
                + PARTICIPANT_DESC_AMY, new AddMeetingCommand(expectedMeeting));

        // multiple times - last time accepted
        assertParseSuccess(parser, MODULE_DESC_CM1111_MEETING
                + MEETING_NAME_DESC_CM1111_MEETING
                + DATE_DESC_CM1111_MEETING
                + TIME_DESC_CM1112_MEETING
                + TIME_DESC_CM1111_MEETING
                + PARTICIPANT_DESC_AMY, new AddMeetingCommand(expectedMeeting));

        // multiple participants - all accepted
        Meeting expectedMeetingMultipleParticipants = new MeetingBuilder(CM1111_MEETING)
                .withMembers(new HashSet<>(Arrays.asList(AMY, BOB)))
                .build();
        assertParseSuccess(parser, MODULE_DESC_CM1111_MEETING
                + MEETING_NAME_DESC_CM1111_MEETING
                + DATE_DESC_CM1111_MEETING
                + TIME_DESC_CM1111_MEETING
                + PARTICIPANT_DESC_AMY
                + PARTICIPANT_DESC_BOB, new AddMeetingCommand(expectedMeetingMultipleParticipants));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE);

        // missing module prefix
        assertParseFailure(parser, MEETING_NAME_DESC_CM1111_MEETING
                        + DATE_DESC_CM1111_MEETING
                        + TIME_DESC_CM1111_MEETING
                        + PARTICIPANT_DESC_AMY,
                expectedMessage);

        // missing name prefix
        assertParseFailure(parser, MODULE_DESC_CM1111_MEETING
                        + DATE_DESC_CM1111_MEETING
                        + TIME_DESC_CM1111_MEETING
                        + PARTICIPANT_DESC_AMY,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser, MODULE_DESC_CM1111_MEETING
                        + MEETING_NAME_DESC_CM1111_MEETING
                        + TIME_DESC_CM1111_MEETING
                        + PARTICIPANT_DESC_AMY,
                expectedMessage);

        // missing time prefix
        assertParseFailure(parser, MODULE_DESC_CM1111_MEETING
                        + MEETING_NAME_DESC_CM1111_MEETING
                        + DATE_DESC_CM1111_MEETING
                        + PARTICIPANT_DESC_AMY,
                expectedMessage);

        // missing participant prefix
        assertParseFailure(parser, MODULE_DESC_CM1111_MEETING
                        + MEETING_NAME_DESC_CM1111_MEETING
                        + DATE_DESC_CM1111_MEETING
                        + TIME_DESC_CM1111_MEETING,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_MODULE_NAME_CM1111_MEETING
                        + VALID_MEETING_NAME_CM1111_MEETING
                        + VALID_DATE_CM1111_MEETING,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid module
        assertParseFailure(parser, INVALID_MODULE_NAME
                + MEETING_NAME_DESC_CM1111_MEETING
                + DATE_DESC_CM1111_MEETING
                + TIME_DESC_CM1111_MEETING
                + PARTICIPANT_DESC_AMY, ModuleName.MESSAGE_CONSTRAINTS);

        // invalid meeting name
        assertParseFailure(parser, MODULE_DESC_CM1111_MEETING
                + INVALID_MEETING_NAME
                + DATE_DESC_CM1111_MEETING
                + TIME_DESC_CM1111_MEETING
                + PARTICIPANT_DESC_AMY, MeetingName.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, MODULE_DESC_CM1111_MEETING
                + MEETING_NAME_DESC_CM1111_MEETING
                + INVALID_DATE_DESC
                + TIME_DESC_CM1111_MEETING
                + PARTICIPANT_DESC_AMY, Date.MESSAGE_CONSTRAINTS);

        // invalid time
        assertParseFailure(parser, MODULE_DESC_CM1111_MEETING
                + MEETING_NAME_DESC_CM1111_MEETING
                + DATE_DESC_CM1111_MEETING
                + INVALID_TIME_DESC
                + PARTICIPANT_DESC_AMY, Time.MESSAGE_CONSTRAINTS);

        // invalid participant
        assertParseFailure(parser, MODULE_DESC_CM1111_MEETING
                + MEETING_NAME_DESC_CM1111_MEETING
                + DATE_DESC_CM1111_MEETING
                + TIME_DESC_CM1111_MEETING
                + INVALID_PARTICIPANT_DESC, Name.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_MODULE_NAME
                        + MEETING_NAME_DESC_CM1111_MEETING
                        + INVALID_DATE_DESC
                        + TIME_DESC_CM1111_MEETING
                        + PARTICIPANT_DESC_AMY,
                ModuleName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY
                        + MODULE_DESC_CM1111_MEETING
                        + MEETING_NAME_DESC_CM1111_MEETING
                        + DATE_DESC_CM1111_MEETING
                        + TIME_DESC_CM1111_MEETING
                        + PARTICIPANT_DESC_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
    }

}
