package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.EVENT_DATE_DESC_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_DESC_DESC_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_LOCATION_DESC_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_NAME_DESC_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_DATE_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_DATE_RANGE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_LOCATION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LUNCH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;
import seedu.address.testutil.EventBuilder;

class AddEventCommandParserTest {
    //TODO: Try to improve this further - there might be weirder user inputs.
    private AddEventCommandParser parser = new AddEventCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder().withEventName(VALID_NAME_LUNCH).withDate(VALID_DATE_LUNCH)
                .withLocation(VALID_LOCATION_LUNCH).withDescription(VALID_DESC_LUNCH).build();

        // allow for leading whitespaces
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + EVENT_NAME_DESC_LUNCH
                + EVENT_LOCATION_DESC_LUNCH + EVENT_DATE_DESC_LUNCH + EVENT_DESC_DESC_LUNCH,
                new AddEventCommand(expectedEvent));

        // main success
        assertParseSuccess(parser, EVENT_NAME_DESC_LUNCH
                + EVENT_LOCATION_DESC_LUNCH + EVENT_DATE_DESC_LUNCH + EVENT_DESC_DESC_LUNCH,
                new AddEventCommand(expectedEvent));
    }

    void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        // missing name
        assertParseFailure(parser, EVENT_LOCATION_DESC_LUNCH
                + EVENT_DATE_DESC_LUNCH + EVENT_DESC_DESC_LUNCH, expectedMessage);

        // missing location
        assertParseFailure(parser, EVENT_NAME_DESC_LUNCH
                + EVENT_DATE_DESC_LUNCH + EVENT_DESC_DESC_LUNCH, expectedMessage);

        // missing date
        assertParseFailure(parser, EVENT_NAME_DESC_LUNCH
                + EVENT_LOCATION_DESC_LUNCH + EVENT_DESC_DESC_LUNCH, expectedMessage);

        // missing desc
        assertParseFailure(parser, EVENT_NAME_DESC_LUNCH
                + EVENT_LOCATION_DESC_LUNCH + EVENT_DATE_DESC_LUNCH, expectedMessage);
    }

    void parse_invalidValue_failure() {
        String expectedMessage;

        // invalid name
        expectedMessage = EventName.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser, INVALID_EVENT_NAME
                + EVENT_LOCATION_DESC_LUNCH + EVENT_DATE_DESC_LUNCH + EVENT_DESC_DESC_LUNCH,
                expectedMessage);

        // invalid location
        expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser, EVENT_NAME_DESC_LUNCH
                + INVALID_EVENT_LOCATION + EVENT_DATE_DESC_LUNCH + EVENT_DESC_DESC_LUNCH,
                expectedMessage);

        // invalid description
        expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser, EVENT_NAME_DESC_LUNCH
                + EVENT_LOCATION_DESC_LUNCH + EVENT_DATE_DESC_LUNCH + INVALID_EVENT_DESC,
                expectedMessage);

        // invalid date
        expectedMessage = EventDate.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser, EVENT_NAME_DESC_LUNCH
                + EVENT_LOCATION_DESC_LUNCH + INVALID_EVENT_DATE_FORMAT + EVENT_DESC_DESC_LUNCH,
                expectedMessage);
        assertParseFailure(parser, EVENT_NAME_DESC_LUNCH
                        + EVENT_LOCATION_DESC_LUNCH + INVALID_EVENT_DATE_RANGE + EVENT_DESC_DESC_LUNCH,
                expectedMessage);
        assertParseFailure(parser, EVENT_NAME_DESC_LUNCH
                        + EVENT_LOCATION_DESC_LUNCH + INVALID_EVENT_DATE + EVENT_DESC_DESC_LUNCH,
                expectedMessage);
    }
}
