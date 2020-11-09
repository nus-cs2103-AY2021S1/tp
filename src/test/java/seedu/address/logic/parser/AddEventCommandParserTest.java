package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEvents.PARTY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.add.AddEventCommand;
import seedu.address.model.task.Event;
import seedu.address.testutil.EventBuilder;

public class AddEventCommandParserTest {
    private static final String MEETING_DESC = "desc/meeting ";
    private static final String MEETING_START_DATE = "startdate/12-12-2020 ";
    private static final String MEETING_START_TIME = "starttime/1000 ";
    private static final String MEETING_END_DATE = "enddate/12-12-2020 ";
    private static final String MEETING_END_TIME = "endtime/1130";

    private static final String PARTY_DESC = "desc/party ";
    private static final String PARTY_START_DATE = "startdate/01-01-2020 ";
    private static final String PARTY_START_TIME = "starttime/1800 ";
    private static final String PARTY_END_DATE = "enddate/02-01-2020 ";
    private static final String PARTY_END_TIME = "endtime/0600 ";
    private static final String PARTY_RECURRENCE = "recurring/1 year";

    private static final String INVALID_START_DATE = "startdate/1-15-2020 ";
    private static final String INVALID_START_TIME = "starttime/2500 ";
    private static final String INVALID_END_DATE = "enddate/1-15-2020 ";
    private static final String INVALID_END_TIME = "endtime/2500";
    private static final String INVALID_RECURRENCE_NUMBER = "recurring/0 day";
    private static final String INVALID_RECURRENCE_VALUE = "recurring/xxx";
    private static final String INVALID_RECURRENCE_UNIT = "recurring/1 sleep";

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder().withDescription("meeting")
                .withStartDateTime("12-12-2020 1000").withEndDateTime("12-12-2020 1130")
                .build();
        assertParseSuccess(parser, "event " + MEETING_DESC + MEETING_START_DATE + MEETING_START_TIME
                + MEETING_END_DATE + MEETING_END_TIME, new AddEventCommand(expectedEvent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        // missing description
        assertParseFailure(parser, "event " + MEETING_START_DATE + MEETING_START_TIME
                + MEETING_END_DATE + MEETING_END_TIME, expectedMessage);

        // missing date
        assertParseFailure(parser, "event " + MEETING_DESC + MEETING_START_TIME
                + MEETING_END_TIME, expectedMessage);

        // missing time
        assertParseFailure(parser, "event " + MEETING_DESC + MEETING_START_TIME
                + MEETING_END_TIME, expectedMessage);

        // missing start date and time
        assertParseFailure(parser, "event " + MEETING_DESC + MEETING_END_DATE
                + MEETING_END_TIME, expectedMessage);

        // missing end date and time
        assertParseFailure(parser, "event " + MEETING_DESC + MEETING_START_DATE
                + MEETING_START_TIME, expectedMessage);

        // no command details
        assertParseFailure(parser, "event ", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedDateMessage = Messages.MESSAGE_INVALID_DATE_FORMAT;
        String expectedTimeMessage = Messages.MESSAGE_INVALID_TIME_FORMAT;

        // invalid startDate
        assertParseFailure(parser, "event " + MEETING_DESC + INVALID_START_DATE + MEETING_START_TIME
                + MEETING_END_DATE + MEETING_END_TIME, expectedDateMessage);

        // invalid endDate
        assertParseFailure(parser, "event " + MEETING_DESC + MEETING_START_DATE + MEETING_START_TIME
                + INVALID_END_DATE + MEETING_END_TIME, expectedDateMessage);

        // invalid startTime
        assertParseFailure(parser, "event " + MEETING_DESC + MEETING_START_DATE + INVALID_START_TIME
                + MEETING_END_DATE + MEETING_END_TIME, expectedTimeMessage);

        // invalid endTime
        assertParseFailure(parser, "event " + MEETING_DESC + MEETING_START_DATE + MEETING_START_TIME
                + MEETING_END_DATE + INVALID_END_TIME, expectedTimeMessage);
    }

    @Test
    public void parse_allFieldsPresentWithRecurrence() {
        Event expectedEvent = new EventBuilder(PARTY).build();

        assertParseSuccess(parser, "event " + PARTY_DESC + PARTY_START_DATE + PARTY_START_TIME
                + PARTY_END_DATE + PARTY_END_TIME + PARTY_RECURRENCE, new AddEventCommand(expectedEvent));
    }

    @Test
    public void parse_allFieldsPresentWithWrongRecurrenceInput() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        // invalid recurrence number
        assertParseFailure(parser, "event " + PARTY_DESC + PARTY_START_DATE + PARTY_START_TIME
                + PARTY_END_DATE + PARTY_END_TIME + INVALID_RECURRENCE_NUMBER, expectedMessage);

        // invalid recurrence unit
        assertParseFailure(parser, "event " + PARTY_DESC + PARTY_START_DATE + PARTY_START_TIME
                + PARTY_END_DATE + PARTY_END_TIME + INVALID_RECURRENCE_UNIT, expectedMessage);

        // invalid recurrence value
        assertParseFailure(parser, "event " + PARTY_DESC + PARTY_START_DATE + PARTY_START_TIME
            + PARTY_END_DATE + PARTY_END_TIME + INVALID_RECURRENCE_VALUE, expectedMessage);
    }

}
