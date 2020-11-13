package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.meeting.CommandTestUtil.CONTACTS_DESC_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.CONTACTS_DESC_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.DESCRIPTION_DESC_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.DESCRIPTION_DESC_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.FROM_DESC_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.FROM_DESC_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.INVALID_CONTACTS_DESC;
import static seedu.address.logic.commands.meeting.CommandTestUtil.INVALID_FROM_DESC;
import static seedu.address.logic.commands.meeting.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.meeting.CommandTestUtil.INVALID_TO_DESC;
import static seedu.address.logic.commands.meeting.CommandTestUtil.LOCATION_DESC_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.LOCATION_DESC_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.meeting.CommandTestUtil.TITLE_DESC_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.TITLE_DESC_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.TO_DESC_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.TO_DESC_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_FROM_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_TITLE_B;
import static seedu.address.logic.parser.meeting.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.meeting.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalMeetings.MEETING_A;
import static seedu.address.testutil.TypicalMeetings.MEETING_B;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meeting.AddCommand;
import seedu.address.model.meeting.meeting.From;
import seedu.address.model.meeting.meeting.Meeting;
import seedu.address.model.meeting.meeting.To;
import seedu.address.model.util.Contacts;
import seedu.address.model.util.Title;
import seedu.address.testutil.MeetingBuilder;

class AddCommandParserTest {

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Meeting expectedMeeting = new MeetingBuilder(MEETING_B).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_B + DESCRIPTION_DESC_B + FROM_DESC_B
                + TO_DESC_B + CONTACTS_DESC_B + LOCATION_DESC_B, new AddCommand(expectedMeeting));


        // multiple TITLES - last TITLE accepted
        assertParseSuccess(parser, TITLE_DESC_A + TITLE_DESC_B + DESCRIPTION_DESC_B + FROM_DESC_B
                + TO_DESC_B + CONTACTS_DESC_B + LOCATION_DESC_B, new AddCommand(expectedMeeting));

        // multiple DESCRIPTIONS - last DESCRIPTION accepted
        assertParseSuccess(parser, TITLE_DESC_B + DESCRIPTION_DESC_A + DESCRIPTION_DESC_B + FROM_DESC_B
                + TO_DESC_B + CONTACTS_DESC_B + LOCATION_DESC_B, new AddCommand(expectedMeeting));

        // multiple FROMS - last FROM accepted
        assertParseSuccess(parser, TITLE_DESC_B + DESCRIPTION_DESC_B + FROM_DESC_A + FROM_DESC_B
                + TO_DESC_B + CONTACTS_DESC_B + LOCATION_DESC_B, new AddCommand(expectedMeeting));

        // multiple TOS - last TO accepted
        assertParseSuccess(parser, TITLE_DESC_B + DESCRIPTION_DESC_B + FROM_DESC_B + TO_DESC_A
                + TO_DESC_B + CONTACTS_DESC_B + LOCATION_DESC_B, new AddCommand(expectedMeeting));

        // multiple CONTACTS - last CONTACTS accepted
        assertParseSuccess(parser, TITLE_DESC_B + DESCRIPTION_DESC_B + FROM_DESC_B + TO_DESC_A
                + TO_DESC_B + CONTACTS_DESC_A + CONTACTS_DESC_B + LOCATION_DESC_B, new AddCommand(expectedMeeting));

        // multiple LOCATIONS - last LOCATION accepted
        assertParseSuccess(parser, TITLE_DESC_B + DESCRIPTION_DESC_B + FROM_DESC_B + TO_DESC_A
                + TO_DESC_B + CONTACTS_DESC_B + LOCATION_DESC_A + LOCATION_DESC_B, new AddCommand(expectedMeeting));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Meeting expectedMeetingWithoutLocation = new MeetingBuilder(MEETING_A).withLocation().build();
        assertParseSuccess(parser, TITLE_DESC_A + DESCRIPTION_DESC_A + FROM_DESC_A + TO_DESC_A + CONTACTS_DESC_A,
                new AddCommand(expectedMeetingWithoutLocation));

        Meeting expectedMeetingWithoutDescription = new MeetingBuilder(MEETING_A).withDescription().build();
        assertParseSuccess(parser, TITLE_DESC_A + FROM_DESC_A + TO_DESC_A + CONTACTS_DESC_A + LOCATION_DESC_A,
                new AddCommand(expectedMeetingWithoutDescription));

        Meeting expectedMeetingWithoutContacts = new MeetingBuilder(MEETING_A).withContacts().build();
        assertParseSuccess(parser, TITLE_DESC_A + DESCRIPTION_DESC_A + FROM_DESC_A + TO_DESC_A + LOCATION_DESC_A,
                new AddCommand(expectedMeetingWithoutContacts));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing TITLE prefix
        assertParseFailure(parser, VALID_TITLE_B + FROM_DESC_B,
                expectedMessage);

        // missing FROM prefix
        assertParseFailure(parser, TITLE_DESC_B + VALID_FROM_B,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE_B + VALID_FROM_B,
                expectedMessage);
    }

    @Test
    //TODO: Modify INVALID types
    public void parse_invalidValue_failure() {
        // invalid TITLE
        assertParseFailure(parser, INVALID_TITLE_DESC + DESCRIPTION_DESC_B + FROM_DESC_B
                + TO_DESC_B + CONTACTS_DESC_B + LOCATION_DESC_B, Title.MESSAGE_CONSTRAINTS);

        // invalid FROM
        assertParseFailure(parser, TITLE_DESC_B + DESCRIPTION_DESC_B + INVALID_FROM_DESC
                + TO_DESC_B + CONTACTS_DESC_B + LOCATION_DESC_B, From.MESSAGE_CONSTRAINTS);

        // invalid TO
        assertParseFailure(parser, TITLE_DESC_B + DESCRIPTION_DESC_B + FROM_DESC_B
                + INVALID_TO_DESC + CONTACTS_DESC_B + LOCATION_DESC_B, To.MESSAGE_CONSTRAINTS);

        // invalid CONTACTS
        assertParseFailure(parser, TITLE_DESC_B + DESCRIPTION_DESC_B + FROM_DESC_B
                + TO_DESC_B + INVALID_CONTACTS_DESC + LOCATION_DESC_A, Contacts.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, TITLE_DESC_B + DESCRIPTION_DESC_B + FROM_DESC_B
                + INVALID_TO_DESC + INVALID_CONTACTS_DESC + LOCATION_DESC_A, To.MESSAGE_CONSTRAINTS);

        // empty preamble
        assertParseFailure(parser, DESCRIPTION_DESC_B + FROM_DESC_B
                        + TO_DESC_B + CONTACTS_DESC_B + LOCATION_DESC_B,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

}
